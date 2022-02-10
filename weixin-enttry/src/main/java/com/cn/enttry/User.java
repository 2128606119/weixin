package com.cn.enttry;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//指定当前类对应的是那张表
@TableName("t_user")
public class User implements Serializable {
    //指定表主键与属性对应 类型为自增  mybatis会自动识别pojo类中名为id的属性将它识别为主键
    //如果主键没有赋值则会帮你使用ID_WORKER(全局唯一ID,自动生成长整形数字)生成策略，，因为表中设置了自增，所以会报错
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String wechatid;
    private String phone;
    private String password;
    private Integer isbarkmode;
    private String about;
    @TableField(fill = FieldFill.INSERT_UPDATE,value = "landing_time")
    private Date landingTime;
    @TableField(fill = FieldFill.INSERT_UPDATE,value = "create_time")
    private Date createTime;




    //指定表字段与属性对应
    @TableField(value = "max_heand_png")
    private String maxhead;
    @TableField(value = "min_heand_png")
    private String minhead;



    @TableField(value = "grade")
    private Integer grade;
    @TableField(value = "consecutive")
    private Integer consecutive;
}