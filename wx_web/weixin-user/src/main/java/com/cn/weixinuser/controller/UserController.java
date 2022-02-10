package com.cn.weixinuser.controller;

import com.cn.entity.ResultEntity;
import com.cn.enttry.User;
import com.cn.enttry.netty.ShutDownMsg;
import com.cn.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
@DefaultProperties(defaultFallback = "abnormal")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //注册
    @RequestMapping(value = "/register")
    @HystrixCommand
    public ResultEntity register(@RequestBody User user) {
        //显示头像
        String imagepath = "https://images.unsplash.com/photo-1554151228-14d9def656e4?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=333&q=80";
        user.setMaxhead(imagepath);
        user.setMinhead(imagepath);
        System.out.println("/user/register" + user);
        user.setWechatid(user.getPhone());
        Integer num = userService.register(user);
        if (num == 1) {
            return ResultEntity.error("用户名已存在");
        }
        if (num == 2) {
            return ResultEntity.error("手机号已存在");
        }
        return ResultEntity.success(user);
    }

    //登陆
    @RequestMapping(value = "/login")
    @HystrixCommand
    public ResultEntity login(@RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("did") String did) {
        User user = userService.getuserphone(phone);
        System.out.println("进入登陆API");

        if (user.getPassword() != null && user.getPassword().equals(password)) {
            System.out.println("登陆成功" + user);


            //获取旧设备ID
            String redisdid = redisTemplate.opsForValue().get(user.getId().toString());
            System.out.println("旧设备ID" + redisdid + "新设备ID" + did);


            if (redisdid != null && !did.equals(redisdid)) {
                System.out.println("挤下其他用户");
                //挤下其他用户
                ShutDownMsg downMsg = new ShutDownMsg();
                downMsg.setType(6);
                downMsg.setDid(redisdid);
                System.out.println("将数据发送给交换机" + downMsg);
                //将数据发送给交换机
                rabbitTemplate.convertAndSend("ws_exchange", "", downMsg);
            }

            //保存设备ID
            System.out.println("登陆成功保存设备ID");
            redisTemplate.opsForValue().set(user.getId().toString(), did);
            return ResultEntity.success(user);
        }
        return ResultEntity.error("手机号或密码错误");
    }

    //修改用户信息
    @RequestMapping(value = "/update")
    @HystrixCommand
    public ResultEntity upadteuser(@RequestBody User user) {
        System.out.println(user);
        Integer integer = userService.upadteuser(user);
        if (integer == 1) {
            return ResultEntity.success();
        }
        if (integer == 0) {
            return ResultEntity.error("无此用户");
        }
        if (integer == 2) {
            return ResultEntity.error("用户名已占用");
        }
        if (integer == 3) {
            return ResultEntity.error("id已占用");
        }
        return ResultEntity.error("修改失败");
    }

    //根据微信号查询用户
//    @HystrixCommand
    @RequestMapping(value = "/getbyuser")
    public ResultEntity getbyuser(String wechatid, int t, int f) {
        System.out.println(wechatid + t + f);
        List<User> users = userService.getbyUser(wechatid, t, f);

        if (users != null) {
            System.out.println(users);
            return ResultEntity.success(users);
        }
        return ResultEntity.error("没有此人");
    }

    //根据id查询用户
    @RequestMapping(value = "/getUserById")
    public User getUserById(Integer id) {
        return userService.selectById(id);
    }


    //全局服务降级方法
    public ResultEntity abnormal() {
        return ResultEntity.error("业务出错");
    }
}
