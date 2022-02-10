package com.cn.execpion;

import com.cn.entity.ResultEntity;
import com.cn.entity.businessExcpion;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常
//@ControllerAdvice
public class GlodeExceptionHandler {

    //处理系统异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultEntity systemEx(Exception e) {
        System.out.println("系统异常："+e.getMessage());
        return ResultEntity.error(e.getMessage());
    }
    //处理业务异常
    @ExceptionHandler(value = businessExcpion.class)
    @ResponseBody
    public ResultEntity systembusinessExcpion(businessExcpion e) {
        System.out.println("业务异常："+e.getMessage());
        return ResultEntity.error(e.getMessage());
    }



}
