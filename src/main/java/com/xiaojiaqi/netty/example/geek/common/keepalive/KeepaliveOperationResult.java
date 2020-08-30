package com.xiaojiaqi.netty.example.geek.common.keepalive;

import com.xiaojiaqi.netty.example.geek.common.OperationResult;
import lombok.Data;

@Data
public class KeepaliveOperationResult extends OperationResult {

    private final long time;

}
