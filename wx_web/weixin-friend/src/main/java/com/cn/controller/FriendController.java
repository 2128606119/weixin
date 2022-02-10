package com.cn.controller;

import com.cn.entity.ResultEntity;
import com.cn.enttry.Friend;
import com.cn.enttry.User;
import com.cn.service.IFriendService;
import com.cn.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Friend")
public class FriendController {

    @Autowired
    IFriendService iFriendService;
    @Autowired
    IUserService iUserService;

    @RequestMapping("/getFriendListByid")
    public ResultEntity getFriendListByid(Integer uid) {
        List<Friend> FriendList = iFriendService.getFriendListByid(uid);
        for (Friend friend : FriendList) {
            User user = iUserService.getUserById(friend.getFid());
            System.out.println(user);
            friend.setFriendObj(user);
        }
        System.out.println(FriendList);
        return ResultEntity.success(FriendList);
    }
}
