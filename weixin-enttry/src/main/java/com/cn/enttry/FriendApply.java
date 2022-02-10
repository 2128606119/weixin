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
@TableName("t_friend_apply")
public class FriendApply implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer fid;
    private Integer tid;
    private String msg;
    private Integer status;
    @TableField(fill = FieldFill.INSERT_UPDATE,value = "create_time")
    private Date createTime;
    @TableField(exist = false)
    private User friend;
}
