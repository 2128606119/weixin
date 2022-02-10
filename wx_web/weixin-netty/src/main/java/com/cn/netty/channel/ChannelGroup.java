package com.cn.netty.channel;


import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * 保存所有客户端链接
 * */
public class ChannelGroup {
    /*
     * key:设备ID
     * Channel:设备连接对象
     * */
    public static Map<String, Channel> channelMap = new HashMap<>();

    /*
     * 将设备消息添加到容器中
     * 客户端建立链接时调用
     * */
    public static void addChannel(String did, Channel chh) {
        channelMap.put(did, chh);
    }

    /*
     * 根据用户设备id
     * 获取客户端链接
     * */
    public static Channel getchannel(String did) {
        return channelMap.get(did);
    }

    /*
     * 根据用户设备id
     * 删除客户端连接
     * */
    public static void removechannel(String did) {
        channelMap.remove(did);
    }

    public static void removechannel(Channel channel) {
        if (channelMap.containsValue(channel)) {
            Set<Map.Entry<String, Channel>> entries = channelMap.entrySet();
            for (Map.Entry<String, Channel> entry : entries) {
                if (entry.getValue() == channel) {
                    channelMap.remove(entry.getKey());
                    break;
                }
            }
        }
    }

}
