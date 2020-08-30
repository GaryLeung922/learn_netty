package com.xiaojiaqi.netty.example.geek.common.auth;

import com.xiaojiaqi.netty.example.geek.common.OperationResult;
import lombok.Data;

@Data
public class AuthOperationResult extends OperationResult {

    private final boolean passAuth;

}
