package com.cn.weixinuser.controller;

import com.cn.entity.ResultEntity;
import com.cn.enttry.User;
import com.cn.service.IUserService;
import com.cn.weixinuser.util.FastDFSClient;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/res")
@DefaultProperties(defaultFallback = "ReuploadFile")
public class ResConntroller {

    @Autowired
    private FastDFSClient fastDFSClient;

    @RequestMapping(value = "/uploadFile")
    public ResultEntity uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            //上传图像并返回路径
            String file1 = fastDFSClient.uploadFile(file);

            //获取文件全路径
            String s = file1.substring(0, file1.lastIndexOf("."));
            //获取文件后缀
            String s1 = file1.substring(file1.lastIndexOf("."));

            //小头像
            String minHead = s + s1 + "_100x100" + s1;
            System.out.println(minHead);

            //大头像
            String maxHead = file1;

//            //修改用户头像
//            if (id != null) {
//
//                User user = userService.selectById(id);
//
//                user.setMinhead(minHead);
//
//                user.setMaxhead(maxHead);
//
//                userService.update(user);
//
//            }

            Map<String, String> map = new HashMap<>();
            map.put("maxHead", maxHead);
            map.put("minHead", minHead);
            return ResultEntity.success(map);

        } catch (IOException e) {
            return ResultEntity.success("上传失败");
        }
    }

    //全局服务降级方法
    public ResultEntity ReuploadFile() {
        return new ResultEntity().error("业务出错");
    }
}
