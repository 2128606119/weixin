package com.cn.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.enttry.FriendApply;

public interface IFriendmapper extends BaseMapper<FriendApply> {
    FriendApply getOne(QueryWrapper<FriendApply> wrapper);
}
