package com.cn.controller;

import com.cn.entity.ResultEntity;
import com.cn.enttry.FriendApply;
import com.cn.enttry.User;
import com.cn.service.IFriendApply;
import com.cn.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/FriendApply")
public class FriendApplyController {

    @Autowired
    private IFriendApply iFriendApply;
    @Resource
    private IUserService userService;

    //模糊查询用户 调用用户服务
    @RequestMapping(value = "/getbyuser")
    public ResultEntity getbyuser(@RequestParam("wechatid") String wechatid, @RequestParam("t") int t, @RequestParam("f") int f) {
        System.out.println(wechatid + t + f);
        return userService.getbyuser(wechatid, t, f);
    }


    //查询添加好友请求 1待处理,2同意,3拒绝
    @RequestMapping(value = "/getUserById")
    public ResultEntity getUserById(Integer id) {
        List<FriendApply> friendApplyList = iFriendApply.getMyFriendApplyList(id);
        //遍历请求，根据请求id查询请求用户信息
        for (FriendApply i : friendApplyList) {
            System.out.println(i);
            Integer fid = i.getFid();
            User user = userService.getUserById(fid);
            System.out.println(user);
            i.setFriend(user);
        }
        if (friendApplyList != null) {
            return ResultEntity.success(friendApplyList);
        }

        return ResultEntity.error("数据为空");
    }

    //添加好友请求 1待处理,2同意,3拒绝
    @RequestMapping("/addFriendApply")
    public ResultEntity addFriendApply(@RequestBody FriendApply friendApply) {
        System.out.println(friendApply);
        int i = iFriendApply.insert(friendApply);
        if (i == 1) {
            return ResultEntity.success(friendApply);
        }
        return ResultEntity.error("请求失败");
    }

    //查询该好友是否请求过
    @RequestMapping("/querybeen")
    public ResultEntity querybeen(@RequestBody FriendApply friendApply) {
        return iFriendApply.querybeen(friendApply) == 1 ? ResultEntity.error("没有请求") : ResultEntity.success();
    }

    //处理同意添加好友请求  1待处理,2同意,3拒绝
    @RequestMapping(value = "/RupdateFriendApplyStatus")
    public ResultEntity RupdateFriendApplyStatus(Integer status, Integer id) {
        iFriendApply.updatestatus(status, id);
        return ResultEntity.success();
    }
}
