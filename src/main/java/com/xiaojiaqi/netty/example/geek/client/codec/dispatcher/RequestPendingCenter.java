package com.xiaojiaqi.netty.example.geek.client.codec.dispatcher;


import com.xiaojiaqi.netty.example.geek.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/19 8:13 AM
 */
public class RequestPendingCenter {

    public Map<Long, OperationResultFuture> map = new ConcurrentHashMap<>();

    public void add(Long streamId, OperationResultFuture future){
        this.map.put(streamId, future);
    }

    public void set(Long streamId, OperationResult result){
        OperationResultFuture operationResultFuture = this.map.get(streamId);
        if(operationResultFuture!=null){
            operationResultFuture.setSuccess(result);
            //this.map.remove(streamId);
        }
    }

    public OperationResultFuture get(long streamId){
        return this.map.get(streamId);
    }
}
