package com.cn.netty.server;


import com.cn.netty.handler.ConnHandler;
import com.cn.netty.handler.HeartbeatHandler;
import com.cn.netty.handler.WebSocketServerChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

// CommandLineRunner SprintBoot生命周期
@Component
public class WebSocketServer implements CommandLineRunner {
    @Value("${netty.port}")
    private Integer port;

    @Autowired
    private StringRedisTemplate redisTemplate;

    //当 Spring Boot 初始化成功后调用该方法
    @Override
    public void run(String... args) throws Exception {
        //主从线程模型
        EventLoopGroup master = new NioEventLoopGroup(); //主线程池 用来处理链接
        EventLoopGroup salve = new NioEventLoopGroup(); //从线程池 用来处理读写

        //创建引导类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //设置主从线程模型
        bootstrap.group(master, salve);
        //设置nio类型的通道
        bootstrap.channel(NioServerSocketChannel.class);

        //装配流水线
        bootstrap.childHandler(new ChannelInitializer<Channel>() {

            //当有客户端链接时会创建Channel(通道) 并调用initChannel方法
            @Override
            protected void initChannel(Channel channel) throws Exception {

                //Channel(通道)
                ChannelPipeline pipeline = channel.pipeline();

                //http解编码器
                pipeline.addLast(new HttpServerCodec()); //http request 解码
                pipeline.addLast(new HttpObjectAggregator(1024 * 10)); //full http request 解码

                //WebSocket解编码
                pipeline.addLast(new WebSocketServerProtocolHandler("/"));

                //客户端不发送消息自动断开
                pipeline.addLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS));

                //自定解编码器 消息控制
                pipeline.addLast(new WebSocketServerChannelHandler());
                //自定解编码器 新建链接处理
                pipeline.addLast(new ConnHandler(redisTemplate));
                //自定解编码器 心跳处理
                pipeline.addLast(new HeartbeatHandler());

            }
        });
        //绑定端口
        ChannelFuture channelFuture = bootstrap.bind(port);
        //通过调用sync同步方法阻塞直到绑定成功
        channelFuture.sync();

        System.out.println("启动成功");

    }

}
