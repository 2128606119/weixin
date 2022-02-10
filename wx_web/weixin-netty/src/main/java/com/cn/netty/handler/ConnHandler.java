package com.cn.netty.handler;

import com.cn.enttry.netty.ConnMsg;
import com.cn.enttry.netty.ShutDownMsg;
import com.cn.netty.channel.ChannelGroup;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.data.redis.core.StringRedisTemplate;

public class ConnHandler extends SimpleChannelInboundHandler<ConnMsg> {

    private StringRedisTemplate redisTemplate;

    public ConnHandler(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ConnMsg connMsg) throws Exception {

        System.out.println("新连接" + connMsg);

        //链接对象
        Channel channel = channelHandlerContext.channel();

        //保存新链接
        ChannelGroup.addChannel(connMsg.getDid(), channel);
        System.out.println("保存新链接完成");

        //新设备ID
        String did = connMsg.getDid();
        //旧设备ID
        String did1 = redisTemplate.opsForValue().get(connMsg.getUid());
        System.out.println("新设备ID:"+did+",旧设备ID:"+did1);


        if (did1 != null && !did.equals(did1)) {

            System.out.println("挤下线");


            ShutDownMsg msg = new ShutDownMsg();

            msg.setType(6);
            System.out.println("发送数据"+msg);


            /*发送数据*/
            TextWebSocketFrame frame = new TextWebSocketFrame(new Gson().toJson(msg));
            channel.writeAndFlush(frame);
        }

    }
}
