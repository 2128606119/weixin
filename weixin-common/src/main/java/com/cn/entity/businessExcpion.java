package com.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class businessExcpion extends RuntimeException {
    //错误码
    private String code;
    //错误信息
    private String msg;
}
