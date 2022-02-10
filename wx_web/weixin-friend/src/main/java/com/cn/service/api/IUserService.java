package com.cn.service.api;

import com.cn.entity.ResultEntity;
import com.cn.enttry.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "USER-WEB")
public interface IUserService {

    //根据用户微信号查询用户
    @RequestMapping(value = "/user/getbyuser")
    ResultEntity getbyuser(@RequestParam("wechatid") String wechatid, @RequestParam("t") int t, @RequestParam("f") int f);

    //根据用户id查询申请记录
    @RequestMapping(value = "/user/getUserById")
    User getUserById(@RequestParam("id") Integer id);

}
