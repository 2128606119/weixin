package com.cn.enttry.netty;

import lombok.Data;

@Data
public class ShutDownMsg extends NettyMsg {
    {
        setType(6);
    }
}
