package com.cn.weixinuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.enttry.User;


public interface IUserMapper extends BaseMapper<User> {
    Integer update(User user);
}
