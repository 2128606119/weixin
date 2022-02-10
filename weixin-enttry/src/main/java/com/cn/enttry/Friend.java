package com.cn.enttry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_friend")
public class Friend {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //自己id
    private Integer uid;
    //好友id
    private Integer fid;
    //备注
    private String fomarke;
    //状态
    private Integer state;

    @TableField(exist = false)
    private User friendObj;

}
