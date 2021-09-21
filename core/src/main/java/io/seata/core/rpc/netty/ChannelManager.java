/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.core.rpc.netty;

import io.netty.channel.Channel;
import io.seata.common.Constants;
import io.seata.common.exception.FrameworkException;
import io.seata.common.util.CollectionUtils;
import io.seata.common.util.StringUtils;
import io.seata.core.protocol.IncompatibleVersionException;
import io.seata.core.protocol.RegisterRMRequest;
import io.seata.core.protocol.RegisterTMRequest;
import io.seata.core.protocol.Version;
import io.seata.core.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The type channel manager.
 *
 * @author slievrly
 */
public class ChannelManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelManager.class);
    // 缓存 client  和 客户端角色 版本 等元信息
    // 一个客户端 会将自己注册城 TM 和RM 也就是 一个 jvm seata 进程将 与 seataServer 注册2个channel
    private static final ConcurrentMap<Channel, RpcContext> IDENTIFIED_CHANNELS = new ConcurrentHashMap<>();

    /**
     * resourceId -> applicationId -> ip -> port -> RpcContext
     */
    private static final ConcurrentMap<String,
                                                ConcurrentMap<String,
                                                        ConcurrentMap<String,
                                                                ConcurrentMap<Integer, RpcContext>
                                                                >
                                                        >

                                        > RM_CHANNELS = new ConcurrentHashMap<>();

    /**
     * appname:ip,port
     *
     *  第一个key 是 Spring.application.name:ip 第二个key 是port
     *
     */
    private static final ConcurrentMap<String, ConcurrentMap<Integer, RpcContext>> TM_CHANNELS = new ConcurrentHashMap<>();

    /**
     * Is registered boolean.
     *
     * @param channel the channel
     * @return the boolean
     */
    public static boolean isRegistered(Channel channel) {
        return IDENTIFIED_CHANNELS.containsKey(channel);
    }

    /**
     * Gets get role from channel.
     *
     * @param channel the channel
     * @return the get role from channel
     */
    public static NettyPoolKey.TransactionRole getRoleFromChannel(Channel channel) {
        RpcContext context = IDENTIFIED_CHANNELS.get(channel);
        if (context != null) {
            return context.getClientRole();
        }
        return null;
    }

    /**
     * Gets get context from identified.
     *
     * @param channel the channel
     * @return the get context from identified
     */
    public static RpcContext getContextFromIdentified(Channel channel) {
        return IDENTIFIED_CHANNELS.get(channel);
    }

    private static String buildClientId(String applicationId, Channel channel) {
        return applicationId + Constants.CLIENT_ID_SPLIT_CHAR + ChannelUtil.getAddressFromChannel(channel);
    }

    private static String[] readClientId(String clientId) {
        return clientId.split(Constants.CLIENT_ID_SPLIT_CHAR);
    }

    private static RpcContext buildChannelHolder(NettyPoolKey.TransactionRole clientRole, String version, String applicationId,
                                                 String txServiceGroup, String dbkeys, Channel channel) {
        RpcContext holder = new RpcContext();
        holder.setClientRole(clientRole);
        holder.setVersion(version);
        holder.setClientId(buildClientId(applicationId, channel));
        holder.setApplicationId(applicationId);
        holder.setTransactionServiceGroup(txServiceGroup);
        holder.addResources(dbKeytoSet(dbkeys));
        holder.setChannel(channel);
        return holder;
    }

    /**
     * Register tm channel.
     *
     *
     * @param request the request
     * @param channel the channel
     * @throws IncompatibleVersionException the incompatible version exception
     */
    public static void registerTMChannel(RegisterTMRequest request, Channel channel)
        throws IncompatibleVersionException {
        // 检测客户端版本是否匹配
        Version.checkVersion(request.getVersion());

        RpcContext rpcContext = buildChannelHolder(
                NettyPoolKey.TransactionRole.TMROLE,
                request.getVersion(),
                request.getApplicationId(),
                request.getTransactionServiceGroup(),
                null,
                channel
        );
        // 缓存其他client 信息 并且 register 自己
        rpcContext.holdInIdentifiedChannels(IDENTIFIED_CHANNELS);

        // 这里的 appid = spring.application.name:ip 是服务名 可能存在多个节点 采用同一服务名
        String clientIdentified =
                        rpcContext.getApplicationId() +
                        Constants.CLIENT_ID_SPLIT_CHAR +
                        ChannelUtil.getClientIpFromChannel(channel);


        System.out.println("tm client 注册了 clientIdentified = " + clientIdentified);
        ConcurrentMap<Integer, RpcContext> clientIdentifiedMap = CollectionUtils.computeIfAbsent(TM_CHANNELS,
            clientIdentified, key -> new ConcurrentHashMap<>());
        rpcContext.holdInClientChannels(clientIdentifiedMap);
    }

    /**
     * Register rm channel.
     * 注册rm的时候如果没有resourceId 会直接注册全部注册到 全局缓存中
     *
     * @param resourceManagerRequest the resource manager request
     * @param channel                the channel
     * @throws IncompatibleVersionException the incompatible  version exception
     */
    public static void registerRMChannel(RegisterRMRequest resourceManagerRequest, Channel channel)
        throws IncompatibleVersionException {
        Version.checkVersion(resourceManagerRequest.getVersion());
        Set<String> dbkeySet = dbKeytoSet(resourceManagerRequest.getResourceIds());
        RpcContext rpcContext;
        if (!IDENTIFIED_CHANNELS.containsKey(channel)) {
            rpcContext = buildChannelHolder(
                    NettyPoolKey.TransactionRole.RMROLE,
                    resourceManagerRequest.getVersion(),
                    resourceManagerRequest.getApplicationId(),
                    resourceManagerRequest.getTransactionServiceGroup(),
                    resourceManagerRequest.getResourceIds(),
                    channel
            );
            rpcContext.holdInIdentifiedChannels(IDENTIFIED_CHANNELS);
        } else {
            rpcContext = IDENTIFIED_CHANNELS.get(channel);
            rpcContext.addResources(dbkeySet);
        }
        if (dbkeySet == null || dbkeySet.isEmpty()) { return; }

        for (String resourceId : dbkeySet) {
            String clientIp;

            //  resourceId -> ApplicationId -> clientIp -> port -> channel 主要是保证获取resource对应通道的唯一性
            ConcurrentMap<Integer, RpcContext> portMap =
                    CollectionUtils.computeIfAbsent(RM_CHANNELS, resourceId, key -> new ConcurrentHashMap<>())
                    .computeIfAbsent(resourceManagerRequest.getApplicationId(), key -> new ConcurrentHashMap<>())
                    .computeIfAbsent(clientIp = ChannelUtil.getClientIpFromChannel(channel), key -> new ConcurrentHashMap<>());

            rpcContext.holdInResourceManagerChannels(resourceId, portMap);
            updateChannelsResource(resourceId, clientIp, resourceManagerRequest.getApplicationId());
        }
    }

    /**
     * 找到resourceId 不同 但是 applicationId clientIp 相同 的 rm-client 端口不一致 会纳入到 新的 resourceId 对应的 map中去
     *
     *
     */
    private static void updateChannelsResource(String resourceId, String clientIp, String applicationId) {
        ConcurrentMap<Integer, RpcContext> sourcePortMap = RM_CHANNELS.get(resourceId).get(applicationId).get(clientIp);
        for (
                ConcurrentMap.Entry<String, ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<Integer, RpcContext>>>> rmChannelEntry
                :
                RM_CHANNELS.entrySet()
        ) {
            if (rmChannelEntry.getKey().equals(resourceId)) { continue; }
            ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<Integer, RpcContext>>> applicationIdMap = rmChannelEntry.getValue();

            if (!applicationIdMap.containsKey(applicationId)) { continue; }
            ConcurrentMap<String, ConcurrentMap<Integer, RpcContext>> clientIpMap = applicationIdMap.get(applicationId);

            if (!clientIpMap.containsKey(clientIp)) { continue; }
            ConcurrentMap<Integer, RpcContext> portMap = clientIpMap.get(clientIp);
            // 这里之所以会遍历找到 不同resourceId 但是 applicationId clientIp 是相同的情况下 可能就是 同一个客户端
            for (ConcurrentMap.Entry<Integer, RpcContext> portMapEntry : portMap.entrySet()) {
                Integer port = portMapEntry.getKey();
                if (!sourcePortMap.containsKey(port)) {
                    RpcContext rpcContext = portMapEntry.getValue();
                    sourcePortMap.put(port, rpcContext);
                    rpcContext.holdInResourceManagerChannels(resourceId, port);
                }
            }

        }
    }

    private static Set<String> dbKeytoSet(String dbkey) {
        if (StringUtils.isNullOrEmpty(dbkey)) {
            return null;
        }
        return new HashSet<String>(Arrays.asList(dbkey.split(Constants.DBKEYS_SPLIT_CHAR)));
    }

    /**
     * Release rpc context.
     *
     * @param channel the channel
     */
    public static void releaseRpcContext(Channel channel) {
        RpcContext rpcContext = getContextFromIdentified(channel);
        if (rpcContext != null) {
            rpcContext.release();
        }
    }

    /**
     * Gets get same income client channel.
     *
     *
     *  这个操作我大概是知道为啥了。。。 因为 Tm 会不断重连
     *  可能之前的channel 中断了。 那么就通过获取 rpcContext中的信息 重新拿到channel 发送回去。。
     *
     *  如果这个channel 不活跃了 那么 删除 tmMap 中的缓存  和 资源id - appname - 对应这个融到port 中的 缓存
     *  如果是rm 那么返回相同appname + ip 不同 port 下的实例 给这个实例发送消息？？？？ wtf？？？？？？
     *
     * @param channel the channel
     * @return the get same income client channel
     */
    public static Channel getSameClientChannel(Channel channel) {
        if (channel.isActive()) {
            return channel;
        }
        RpcContext rpcContext = getContextFromIdentified(channel);
        if (rpcContext == null) {
            LOGGER.error("rpcContext is null,channel:{},active:{}", channel, channel.isActive());
            return null;
        }
        if (rpcContext.getChannel().isActive()) {
            // recheck
            return rpcContext.getChannel();
        }


        Integer clientPort = ChannelUtil.getClientPortFromChannel(channel);
        NettyPoolKey.TransactionRole clientRole = rpcContext.getClientRole();
        if (clientRole == NettyPoolKey.TransactionRole.TMROLE) {
            String clientIdentified = rpcContext.getApplicationId() + Constants.CLIENT_ID_SPLIT_CHAR
                + ChannelUtil.getClientIpFromChannel(channel);
            if (!TM_CHANNELS.containsKey(clientIdentified)) {
                return null;
            }
            ConcurrentMap<Integer, RpcContext> clientRpcMap = TM_CHANNELS.get(clientIdentified);
            return getChannelFromSameClientMap(clientRpcMap, clientPort);
        } else if (clientRole == NettyPoolKey.TransactionRole.RMROLE) {
            for (Map<Integer, RpcContext> clientRmMap : rpcContext.getClientRMHolderMap().values()) {
                Channel sameClientChannel = getChannelFromSameClientMap(clientRmMap, clientPort);
                if (sameClientChannel != null) {
                    return sameClientChannel;
                }
            }
        }
        return null;

    }
    //  能解释这个fun 的 理由应该是 如果 对应tm 通道不活跃 换一个 同样appname ip 不同端口 可能重连了 。
    private static Channel getChannelFromSameClientMap(Map<Integer, RpcContext> clientChannelMap, int exclusivePort) {
        if (clientChannelMap != null && !clientChannelMap.isEmpty()) {
            for (ConcurrentMap.Entry<Integer, RpcContext> entry : clientChannelMap.entrySet()) {
                if (entry.getKey() == exclusivePort) {
                    clientChannelMap.remove(entry.getKey());
                    continue;
                }
                Channel channel = entry.getValue().getChannel();
                if (channel.isActive()) { return channel; }
                clientChannelMap.remove(entry.getKey());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("1",new Object());
        stringObjectHashMap.put("2",new Object());
        stringObjectHashMap.put("3",new Object());
        stringObjectHashMap.put("4",new Object());
        stringObjectHashMap.put("5",new Object());
        stringObjectHashMap.put("6",new Object());


        for (Map.Entry<String, Object> entry : stringObjectHashMap.entrySet()) {
            if (entry.getKey().equals("4")) {
                stringObjectHashMap.remove("4");
            }
        }

    }

    /**
     * Gets get channel.
     *
     * @param resourceId Resource ID
     * @param clientId   Client ID - ApplicationId:IP:Port
     * @return Corresponding channel, NULL if not found.
     */
    public static Channel getChannel(String resourceId, String clientId) {
        Channel resultChannel = null;

        String[] clientIdInfo = readClientId(clientId);

        if (clientIdInfo == null || clientIdInfo.length != 3) {
            throw new FrameworkException("Invalid Client ID: " + clientId);
        }

        String targetApplicationId = clientIdInfo[0];
        String targetIP = clientIdInfo[1];
        int targetPort = Integer.parseInt(clientIdInfo[2]);

        ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<Integer,
            RpcContext>>> applicationIdMap = RM_CHANNELS.get(resourceId);

        if (targetApplicationId == null || applicationIdMap == null ||  applicationIdMap.isEmpty()) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("No channel is available for resource[{}]", resourceId);
            }
            return null;
        }

        ConcurrentMap<String, ConcurrentMap<Integer, RpcContext>> ipMap = applicationIdMap.get(targetApplicationId);

        if (ipMap != null && !ipMap.isEmpty()) {
            // Firstly, try to find the original channel through which the branch was registered.
            ConcurrentMap<Integer, RpcContext> portMapOnTargetIP = ipMap.get(targetIP);
            if (portMapOnTargetIP != null && !portMapOnTargetIP.isEmpty()) {
                RpcContext exactRpcContext = portMapOnTargetIP.get(targetPort);
                if (exactRpcContext != null) {
                    Channel channel = exactRpcContext.getChannel();
                    if (channel.isActive()) {
                        resultChannel = channel;
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("Just got exactly the one {} for {}", channel, clientId);
                        }
                    } else {
                        if (portMapOnTargetIP.remove(targetPort, exactRpcContext)) {
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info("Removed inactive {}", channel);
                            }
                        }
                    }
                }

                // The original channel was broken, try another one.
                if (resultChannel == null) {
                    for (ConcurrentMap.Entry<Integer, RpcContext> portMapOnTargetIPEntry : portMapOnTargetIP
                        .entrySet()) {
                        Channel channel = portMapOnTargetIPEntry.getValue().getChannel();

                        if (channel.isActive()) {
                            resultChannel = channel;
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info(
                                    "Choose {} on the same IP[{}] as alternative of {}", channel, targetIP, clientId);
                            }
                            break;
                        } else {
                            if (portMapOnTargetIP.remove(portMapOnTargetIPEntry.getKey(),
                                portMapOnTargetIPEntry.getValue())) {
                                if (LOGGER.isInfoEnabled()) {
                                    LOGGER.info("Removed inactive {}", channel);
                                }
                            }
                        }
                    }
                }
            }

            // No channel on the this app node, try another one.
            if (resultChannel == null) {
                for (ConcurrentMap.Entry<String, ConcurrentMap<Integer, RpcContext>> ipMapEntry : ipMap
                    .entrySet()) {
                    if (ipMapEntry.getKey().equals(targetIP)) { continue; }

                    ConcurrentMap<Integer, RpcContext> portMapOnOtherIP = ipMapEntry.getValue();
                    if (portMapOnOtherIP == null || portMapOnOtherIP.isEmpty()) {
                        continue;
                    }

                    for (ConcurrentMap.Entry<Integer, RpcContext> portMapOnOtherIPEntry : portMapOnOtherIP.entrySet()) {
                        Channel channel = portMapOnOtherIPEntry.getValue().getChannel();

                        if (channel.isActive()) {
                            resultChannel = channel;
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info("Choose {} on the same application[{}] as alternative of {}", channel, targetApplicationId, clientId);
                            }
                            break;
                        } else {
                            if (portMapOnOtherIP.remove(portMapOnOtherIPEntry.getKey(),
                                portMapOnOtherIPEntry.getValue())) {
                                if (LOGGER.isInfoEnabled()) {
                                    LOGGER.info("Removed inactive {}", channel);
                                }
                            }
                        }
                    }
                    if (resultChannel != null) { break; }
                }
            }
        }

        if (resultChannel == null) {
            resultChannel = tryOtherApp(applicationIdMap, targetApplicationId);

            if (resultChannel == null) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("No channel is available for resource[{}] as alternative of {}", resourceId, clientId);
                }
            } else {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Choose {} on the same resource[{}] as alternative of {}", resultChannel, resourceId, clientId);
                }
            }
        }

        return resultChannel;

    }

    private static Channel tryOtherApp(ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<Integer,
        RpcContext>>> applicationIdMap, String myApplicationId) {
        Channel chosenChannel = null;
        for (ConcurrentMap.Entry<String, ConcurrentMap<String, ConcurrentMap<Integer, RpcContext>>> applicationIdMapEntry : applicationIdMap
            .entrySet()) {
            if (!StringUtils.isNullOrEmpty(myApplicationId) && applicationIdMapEntry.getKey().equals(myApplicationId)) {
                continue;
            }

            ConcurrentMap<String, ConcurrentMap<Integer, RpcContext>> targetIPMap = applicationIdMapEntry.getValue();
            if (targetIPMap == null || targetIPMap.isEmpty()) {
                continue;
            }

            for (ConcurrentMap.Entry<String, ConcurrentMap<Integer, RpcContext>> targetIPMapEntry : targetIPMap
                .entrySet()) {
                ConcurrentMap<Integer, RpcContext> portMap = targetIPMapEntry.getValue();
                if (portMap == null || portMap.isEmpty()) {
                    continue;
                }

                for (ConcurrentMap.Entry<Integer, RpcContext> portMapEntry : portMap.entrySet()) {
                    Channel channel = portMapEntry.getValue().getChannel();
                    if (channel.isActive()) {
                        chosenChannel = channel;
                        break;
                    } else {
                        if (portMap.remove(portMapEntry.getKey(), portMapEntry.getValue())) {
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info("Removed inactive {}", channel);
                            }
                        }
                    }
                }
                if (chosenChannel != null) { break; }
            }
            if (chosenChannel != null) { break; }
        }
        return chosenChannel;

    }

    /**
     * get rm channels
     *
     * @return
     */
    public static Map<String,Channel> getRmChannels() {
        if (RM_CHANNELS.isEmpty()) {
            return null;
        }
        Map<String, Channel> channels = new HashMap<>(RM_CHANNELS.size());
        RM_CHANNELS.forEach((resourceId, value) -> {
            Channel channel = tryOtherApp(value, null);
            if (channel == null) {
                return;
            }
            channels.put(resourceId, channel);
        });
        return channels;
    }
}
