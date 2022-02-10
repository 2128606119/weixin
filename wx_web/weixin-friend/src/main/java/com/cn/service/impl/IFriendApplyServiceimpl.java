package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.enttry.FriendApply;
import com.cn.service.IFriendApply;
import com.cn.mapper.IFriendmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IFriendApplyServiceimpl extends ServiceImpl<IFriendmapper, FriendApply> implements IFriendApply {

    @Autowired
    private IFriendmapper mappper;

    @Autowired
    private IFriendServiceImpl iFriendService;

    @Override
    public List<FriendApply> getMyFriendApplyList(Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        //处理好友请求的用户id
        wrapper.eq("tid", id);
        List list = mappper.selectList(wrapper);
        return list;
    }

    @Override
    public int insert(FriendApply friendApply) {
        return mappper.insert(friendApply);
    }

    //1待处理,2同意,3拒绝
    @Override
    public void updatestatus(Integer status, Integer id) {
        FriendApply friendApply = mappper.selectById(id);
        //添加好友
        if (status == 2) {
            iFriendService.addFriend(friendApply.getTid(), friendApply.getFid(), 1);
        }
        mappper.deleteById(id);
    }

    @Override
    public int querybeen(FriendApply friendApply) {
        QueryWrapper<FriendApply> wrapper = new QueryWrapper<>();
        wrapper.eq("fid", friendApply.getFid());
        wrapper.eq("tid", friendApply.getTid());
        int count = mappper.selectCount(wrapper);
        return count;
    }
}
