package com.cn.weixinuser.service.execpion;

import com.cn.entity.ResultEntity;
import com.cn.enttry.User;

public class GlodeException {
    //处理 注册 业务异常
    public Integer registerEx(User user,Throwable t) {
        System.out.println("业务类异常： registerEx，User"+user.toString());
        return 3;
    }
    //处理 登陆 业务异常
    public User getuserphoneEX(String phone,Throwable t){
        System.out.println("业务类异常： getuserphoneEX ，手机号"+phone);
        return new User();
    }
}
