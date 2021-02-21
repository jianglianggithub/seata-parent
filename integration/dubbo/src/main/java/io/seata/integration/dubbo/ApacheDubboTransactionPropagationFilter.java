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
package io.seata.integration.dubbo;

import io.seata.common.util.StringUtils;
import io.seata.core.constants.DubboConstants;
import io.seata.core.context.RootContext;
import io.seata.core.model.BranchType;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * The type Transaction propagation filter.
 *  当对dubbo 服务发起调用 和 被调用都会回调这个filter 注入 xid信息
 * @author sharajava
 */
@Activate(group = {DubboConstants.PROVIDER, DubboConstants.CONSUMER}, order = 100)
public class ApacheDubboTransactionPropagationFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheDubboTransactionPropagationFilter.class);

    public static void main(String[] args) {
        delete(new File("/Users/keyi/Downloads/dubbo-master"),false);
    }


    public static void delete(File file,boolean isDelete) {
        boolean directory = file.isDirectory();
        if (directory) {


                for (File listFile : file.listFiles()) {
                    boolean target = listFile.getName().equals("target");
                    delete(listFile,target || isDelete);
                      if ((target || isDelete) && listFile.isDirectory() && listFile.listFiles().length == 0) {
                          boolean delete = listFile.delete();
                          System.out.println(listFile.getPath() +"删除" + delete);
                    }

                }



        } else {
            String name = file.getName();
            String sufix = name.substring(name.lastIndexOf(".") + 1);
            if ("iml".equals(sufix) || isDelete ||"iws".equals(sufix) ||"ipr".equals(sufix)) {
                System.out.println("删除的文件 " + file.getPath());
                file.delete();
            }
        }
    }
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String xid = RootContext.getXID();
        BranchType branchType = RootContext.getBranchType();

        String rpcXid = getRpcXid();
        String rpcBranchType = RpcContext.getContext().getAttachment(RootContext.KEY_BRANCH_TYPE);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("xid in RootContext[{}] xid in RpcContext[{}]", xid, rpcXid);
        }
        boolean bind = false;
        if (xid != null) { // 调用者
            RpcContext.getContext().setAttachment(RootContext.KEY_XID, xid);
            RpcContext.getContext().setAttachment(RootContext.KEY_BRANCH_TYPE, branchType.name());
        } else {
            // 被调用者 获取到rpcXid 放入到本地RootContextId中
            if (rpcXid != null) {
                RootContext.bind(rpcXid);
                if (StringUtils.equals(BranchType.TCC.name(), rpcBranchType)) {
                    RootContext.bindBranchType(BranchType.TCC);
                }
                bind = true;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("bind xid [{}] branchType [{}] to RootContext", rpcXid, rpcBranchType);
                }
            }
        }
        try {
            return invoker.invoke(invocation);
        } finally {
            // 如果当前是被调用者 在传播完下游后 清理 xid
            if (bind) {
                BranchType previousBranchType = RootContext.getBranchType();
                String unbindXid = RootContext.unbind();
                if (BranchType.TCC == previousBranchType) {
                    RootContext.unbindBranchType();
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("unbind xid [{}] branchType [{}] from RootContext", unbindXid, previousBranchType);
                }
                // 这种情况不知何时会出现 过
                if (!rpcXid.equalsIgnoreCase(unbindXid)) {
                    LOGGER.warn("xid in change during RPC from {} to {},branchType from {} to {}", rpcXid, unbindXid,
                            rpcBranchType != null ? rpcBranchType : "AT", previousBranchType);
                    if (unbindXid != null) {
                        RootContext.bind(unbindXid);
                        LOGGER.warn("bind xid [{}] back to RootContext", unbindXid);
                        if (BranchType.TCC == previousBranchType) {
                            RootContext.bindBranchType(BranchType.TCC);
                            LOGGER.warn("bind branchType [{}] back to RootContext", previousBranchType);
                        }
                    }
                }
            }
        }
    }

    /**
     * get rpc xid
     * @return
     */
    private String getRpcXid() {
        String rpcXid = RpcContext.getContext().getAttachment(RootContext.KEY_XID);
        if (rpcXid == null) {
            rpcXid = RpcContext.getContext().getAttachment(RootContext.KEY_XID.toLowerCase());
        }
        return rpcXid;
    }

}
