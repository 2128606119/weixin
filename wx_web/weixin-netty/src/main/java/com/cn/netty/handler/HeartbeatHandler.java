package com.cn.netty.handler;

import com.cn.enttry.netty.HerdMsg;
import com.cn.enttry.netty.NettyMsg;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class HeartbeatHandler extends SimpleChannelInboundHandler<HerdMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HerdMsg herdMsg) throws Exception {
        System.out.println("处理心跳"+(NettyMsg)herdMsg);
        //响应心跳
        TextWebSocketFrame heartbeat = new TextWebSocketFrame(new Gson().toJson(herdMsg));
        channelHandlerContext.writeAndFlush(heartbeat);
    }
}
