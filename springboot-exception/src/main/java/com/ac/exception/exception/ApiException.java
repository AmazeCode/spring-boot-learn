package com.ac.exception.exception;

import com.ac.exception.common.MessageEnum;
import lombok.Data;

/**
 *  @Description: 自定义异常
 *  @author: zhangyadong
 *  @Date: 2020/8/8 14:03
 *  @version: V1.0
 */
@Data
public class ApiException extends RuntimeException{

    private Integer code;

    /**
     * 使用已有的错误类型
     * @param msg 枚举类中的错误类型
     */
    public ApiException(MessageEnum msg){
        super(msg.getMessage());
        this.code = msg.getCode();
    }

    /**
     * 自定义错误类型
     * @param code 自定义的错误码
     * @param msg 自定义的错误提示
     */
    public ApiException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
