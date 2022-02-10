package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.enttry.Friend;
import com.cn.mapper.IFriendServicemapper;
import com.cn.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IFriendServiceImpl extends ServiceImpl<IFriendServicemapper, Friend> implements IFriendService {
    @Autowired
    private IFriendServicemapper iFriendServicemapper;

    @Override
    public void addFriend(Integer tid, Integer fid, int i) {

        Friend friend1 = new Friend();
        friend1.setUid(tid);
        friend1.setFid(fid);
        friend1.setState(i);
        iFriendServicemapper.insert(friend1);

        Friend friend2 = new Friend();
        friend2.setUid(fid);
        friend2.setFid(tid);
        friend2.setState(i);
        iFriendServicemapper.insert(friend2);
    }

    @Override
    public List<Friend> getFriendListByid(Integer uid) {
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        return iFriendServicemapper.selectList(wrapper);
    }
}
