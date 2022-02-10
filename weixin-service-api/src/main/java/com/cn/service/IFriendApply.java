package com.cn.service;

import com.cn.enttry.FriendApply;

import java.util.List;

public interface IFriendApply {

    List<FriendApply> getMyFriendApplyList(Integer id);

    int insert(FriendApply friendApply);

    void updatestatus(Integer status, Integer id);

    int querybeen(FriendApply friendApply);
}
