package com.cn.enttry.netty;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
* 链接对象
* */
@Data
@AllArgsConstructor
public class ConnMsg extends NettyMsg {
    /*
     * 用户ID
     * */
    private String uid;
}
