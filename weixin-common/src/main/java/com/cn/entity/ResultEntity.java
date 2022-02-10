package com.cn.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity<T> {
    private static final String SUCCESS="OK";
    private static final String ERROR="ERROR";

    private static final String NO_MSG=null;

    private String code; //提示错误码
    private String msg; //提示信息
    private Object data; //内容

    /**
     * 成功没有数据
     * @return
     */
    public static ResultEntity success(){
        return new ResultEntity(SUCCESS,NO_MSG,null);
    }

    /**
     * 成功有数据
     * @return
     */
    public static ResultEntity success(Object data){
        return new ResultEntity(SUCCESS,NO_MSG,data);
    }

    /**
     * 错误有信息
     * @return
     */
    public static ResultEntity error(String msg){
        return new ResultEntity(ERROR,msg,null);
    }

}
