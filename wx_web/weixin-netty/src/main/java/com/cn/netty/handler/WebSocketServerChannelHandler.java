package com.cn.netty.handler;

import com.cn.enttry.netty.ConnMsg;
import com.cn.enttry.netty.HerdMsg;
import com.cn.enttry.netty.NettyMsg;
import com.cn.netty.channel.ChannelGroup;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WebSocketServerChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String text = textWebSocketFrame.text();
        System.out.println("客户端发送数据" + text);
        Gson gson = new Gson();
        NettyMsg nettyMessage = gson.fromJson(text, NettyMsg.class);

        if (nettyMessage.getType()==1) {
            //新客户端链接  channelHandlerContext.fireChannelRead()向下传递
            channelHandlerContext.fireChannelRead(gson.fromJson(text, ConnMsg.class));
        } else if (nettyMessage.getType()==2) {
            //心跳链接
            channelHandlerContext.fireChannelRead(gson.fromJson(text, HerdMsg.class));
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端链接");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开");
        //删除链接
        ChannelGroup.removechannel(ctx.channel());
        super.channelUnregistered(ctx);
    }
}
