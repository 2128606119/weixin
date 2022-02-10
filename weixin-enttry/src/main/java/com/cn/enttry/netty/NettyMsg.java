package com.cn.enttry.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NettyMsg implements Serializable {
    /*
    * 链接状态
    *   1：新连接
    *   2：心跳
    *   3：聊天消息
    *   4：正在输入
    *   5：结束输入
    *   6：挤下线
    * */
    private Integer type;

    /*
    * 客户端设备ID
    * */
    private String did;
}
