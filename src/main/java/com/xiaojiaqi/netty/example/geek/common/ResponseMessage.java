package com.xiaojiaqi.netty.example.geek.common;

public class ResponseMessage extends Message <OperationResult>{
    @Override
    public Class getMessageBodyDecodeClass(int opcode) {
        return OperationType.fromOpCode(opcode).getOperationResultClazz();
    }
}
