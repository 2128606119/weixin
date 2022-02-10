package openfeigncs.controller;

import com.cn.entity.ResultEntity;
import com.cn.enttry.FriendApply;
import com.cn.enttry.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import openfeigncs.server.FriendFeingService;
import openfeigncs.server.UserFeingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "abnormal")
public class FeingController {

    //业务类对象
    @Resource
    private UserFeingService userService;

    @Resource
    private FriendFeingService friendFeingService;

    //注册
    @HystrixCommand
    @RequestMapping(value = "/register")
    public ResultEntity register(User user) {
        return userService.register(user);
    }

    //登陆
    @HystrixCommand
    @RequestMapping(value = "/login")
    public ResultEntity login(String phone, String password,String did) {
        return userService.login(phone, password,did);
    }

    //头像上传
    @RequestMapping(value = "/uploadFile")
    public ResultEntity uploadFile(MultipartFile file) {
        return userService.uploadFile(file);
    }

    //用户修改
    @HystrixCommand
    @RequestMapping(value = "/update")
    public ResultEntity upadteuser(User user) {
        System.out.println(user);
        return userService.upadteuser(user);
    }

    //添加好友请求
    @HystrixCommand
    @RequestMapping(value = "/addFriendApply")
    public ResultEntity addFriendApply(FriendApply friendApply) {
        System.out.println(friendApply);
        return friendFeingService.addFriendApply(friendApply);
    }

    //模糊查询用户
    @RequestMapping(value = "/getbyuser")
    public ResultEntity getbyuser(String wechatid, int t, int f) {
        System.out.println(wechatid + t + f);
        return friendFeingService.getbyuser(wechatid, t, f);
    }

    //显示请求用户
    @RequestMapping(value = "/getUserById")
    ResultEntity getUserById(Integer id) {
        return friendFeingService.getUserById(id);
    }

    //修改添加好友状态  0请求,1拒绝,2同意
    @RequestMapping(value = "/RupdateFriendApplyStatus")
    public ResultEntity RupdateFriendApplyStatus(Integer status, Integer id) {
        return friendFeingService.RupdateFriendApplyStatus(status, id);
    }

    //查询该好友是否请求过
    @RequestMapping("/querybeen")
    public ResultEntity querybeen(FriendApply friendApply) {
        return friendFeingService.querybeen(friendApply);
    }

    @RequestMapping("/getFriendListByid")
    public ResultEntity getFriendListByid(Integer uid) {
        System.out.println(uid);
        return friendFeingService.getFriendListByid(uid);
    }

    //全局服务降级方法
    public ResultEntity abnormal() {
        return new ResultEntity().error("服务出错");
    }
}
