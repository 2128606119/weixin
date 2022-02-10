package com.cn.service;

import com.cn.enttry.Friend;

import java.util.List;

public interface IFriendService {
    void addFriend(Integer tid, Integer fid, int i);

    List<Friend> getFriendListByid(Integer uid);
}
