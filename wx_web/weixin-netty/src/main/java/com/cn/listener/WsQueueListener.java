package com.cn.listener;


import com.cn.enttry.netty.ShutDownMsg;
import com.cn.netty.channel.ChannelGroup;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//监听队列，接收消息
@Component
public class WsQueueListener {
    public static final String QUEUE_INFORM_EMAIL = "ws_queue";

    //监听队列
    @RabbitListener(queues = QUEUE_INFORM_EMAIL + "_${netty.port}")
    public void receive_email(ShutDownMsg msg) {
        System.out.println("监听器 msg" + msg);

        Channel getchannel = ChannelGroup.getchannel(msg.getDid());


        if (getchannel != null) {
            msg.setType(6);
            System.out.println("监听器 发送消息给客户端" + msg);
            TextWebSocketFrame frame = new TextWebSocketFrame(new Gson().toJson(msg));
            getchannel.writeAndFlush(frame);
        }
    }
}
