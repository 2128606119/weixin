package com.cn.service;

import com.cn.enttry.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    Integer register(User user);

    User getuserphone(String phone);

    Integer upadteuser(User user);

    List<User> getbyUser(String wechatid,int t, int f);

    User selectById(Integer id);
}
