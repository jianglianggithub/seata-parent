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
package io.seata.serializer.protobuf.convertor;

import io.seata.core.exception.TransactionExceptionCode;
import io.seata.core.model.GlobalStatus;
import io.seata.core.protocol.ResultCode;
import io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto;
import io.seata.serializer.protobuf.generated.AbstractMessageProto;
import io.seata.serializer.protobuf.generated.AbstractResultMessageProto;
import io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto;
import io.seata.serializer.protobuf.generated.GlobalStatusProto;
import io.seata.serializer.protobuf.generated.GlobalStatusResponseProto;
import io.seata.serializer.protobuf.generated.MessageTypeProto;
import io.seata.serializer.protobuf.generated.ResultCodeProto;
import io.seata.serializer.protobuf.generated.TransactionExceptionCodeProto;
import io.seata.core.protocol.transaction.GlobalStatusResponse;

/**
 * @author leizhiyuan
 */
public class GlobalStatusResponseConvertor implements PbConvertor<GlobalStatusResponse, GlobalStatusResponseProto> {
    @Override
    public GlobalStatusResponseProto convert2Proto(GlobalStatusResponse globalStatusResponse) {
        final short typeCode = globalStatusResponse.getTypeCode();

        final AbstractMessageProto abstractMessage = AbstractMessageProto.newBuilder().setMessageType(
            MessageTypeProto.forNumber(typeCode)).build();

        final String msg = globalStatusResponse.getMsg();
        final AbstractResultMessageProto abstractResultMessageProto = AbstractResultMessageProto.newBuilder().setMsg(
            msg == null ? "" : msg).setResultCode(ResultCodeProto.valueOf(globalStatusResponse.getResultCode().name()))
            .setAbstractMessage(abstractMessage).build();

        AbstractTransactionResponseProto abstractTransactionResponseProto = AbstractTransactionResponseProto
            .newBuilder().setAbstractResultMessage(abstractResultMessageProto).setTransactionExceptionCode(
                TransactionExceptionCodeProto.valueOf(globalStatusResponse.getTransactionExceptionCode().name()))
            .build();

        AbstractGlobalEndResponseProto abstractGlobalEndResponseProto = AbstractGlobalEndResponseProto.newBuilder()
            .setAbstractTransactionResponse(abstractTransactionResponseProto).setGlobalStatus(
                GlobalStatusProto.valueOf(globalStatusResponse.getGlobalStatus().name())).build();

        GlobalStatusResponseProto result = GlobalStatusResponseProto.newBuilder().setAbstractGlobalEndResponse(
            abstractGlobalEndResponseProto).build();
        return result;
    }

    @Override
    public GlobalStatusResponse convert2Model(GlobalStatusResponseProto globalStatusResponseProto) {
        GlobalStatusResponse branchRegisterResponse = new GlobalStatusResponse();
        final AbstractGlobalEndResponseProto abstractGlobalEndResponse = globalStatusResponseProto
            .getAbstractGlobalEndResponse();
        AbstractTransactionResponseProto abstractResultMessage = abstractGlobalEndResponse
            .getAbstractTransactionResponse();
        branchRegisterResponse.setMsg(abstractResultMessage.getAbstractResultMessage().getMsg());
        branchRegisterResponse.setResultCode(
            ResultCode.valueOf(abstractResultMessage.getAbstractResultMessage().getResultCode().name()));
        branchRegisterResponse.setTransactionExceptionCode(
            TransactionExceptionCode.valueOf(abstractResultMessage.getTransactionExceptionCode().name()));
        branchRegisterResponse.setGlobalStatus(
            GlobalStatus.valueOf(abstractGlobalEndResponse.getGlobalStatus().name()));

        return branchRegisterResponse;

    }
}