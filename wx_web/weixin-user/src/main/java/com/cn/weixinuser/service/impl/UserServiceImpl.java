package com.cn.weixinuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.enttry.User;
import com.cn.service.IUserService;
import com.cn.weixinuser.mapper.IUserMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @HystrixCommand(fallbackMethod = "registerEx", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率
    })
    @Override
    public Integer register(User user) {
        //判断用户名是否存在 存在返回1
        if (isExits("username", user.getUsername())) {
            return 1;
            //判断手机号是否存在 存在返回2
        } else if (isExits("phone", user.getPhone())) {
            return 2;
        }
        userMapper.insert(user);
        return 3;
    }

    @HystrixCommand(fallbackMethod = "getuserphoneEX", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率
    })
    @Override
    public User getuserphone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //查询名字为 Tom 的一条记录
        queryWrapper.eq("phone", phone);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    //修改用户信息
    @Override
    public Integer upadteuser(User user) {
        User user1 = userMapper.selectById(user.getId());
        System.out.println(user1);
        if (isExits("username", user.getUsername()) && !user.getUsername().equals(user1.getUsername())) {
            return 2;
        }
        if (isExits("wechatid", user.getWechatid()) && !user.getWechatid().equals(user1.getWechatid())) {
            return 3;
        }
        if (user1 != null) {
            return userMapper.updateById(user);
        }
        return 0;
    }

    @HystrixCommand(fallbackMethod = "getbyUserEX", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率
    })
    //模糊查询用户
    @Override
    public List<User> getbyUser(String wechatid, int t, int f) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(t, f);
        if (wechatid == null) {
            Page<User> userPage = userMapper.selectPage(page, null);
            return userPage.getRecords();
        }

        //条件封装
        wrapper.like("wechatid", wechatid);

        Page<User> userPage = userMapper.selectPage(page, wrapper);

        return userPage.getRecords();
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    //判断值是否存在
    public boolean isExits(String column, String value) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(column, value);
        return userMapper.selectCount(wrapper) != 0;
    }

    //处理 注册 业务异常
    public Integer registerEx(User user, Throwable t) {
        System.out.println("业务类异常： registerEx，User" + user.toString());
        return 3;
    }

    //处理 登陆 业务异常
    public User getuserphoneEX(String phone, Throwable t) {
        System.out.println("业务类异常： getuserphoneEX ，手机号" + phone);
        return new User();
    }

    public List<User> getbyUserEX(String wechatid, int t, int f, Throwable s) {
        System.out.println("业务类异常： getbyUserEX  微信号" + wechatid);
        return null;
    }

}
