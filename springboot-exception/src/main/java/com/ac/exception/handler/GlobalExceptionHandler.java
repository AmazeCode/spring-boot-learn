package com.ac.exception.handler;

import com.ac.exception.common.MessageEnum;
import com.ac.exception.common.Result;
import com.ac.exception.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description: 全局异常处理
 *  @author: zhangyadong
 *  @Date: 2020/8/8 13:57
 *  @version: V1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handle(Exception e) {
        Result result = new Result();
        result.setCode(MessageEnum.UNKNOW_ERROR.getCode());
        result.setMessage(e.getMessage() == null ? MessageEnum.UNKNOW_ERROR.getMessage() : e.getMessage());
        log.error("默认异常类型:{}",e.getMessage(),e);
        return result;
    }

    /**
     * @Description 配置自定义异常类型
     * @params [e]
     * @return com.ac.exception.common.Result
     * @author zhangyadong
     * @date 2020/8/8 14:06
     */
    @ExceptionHandler(ApiException.class)
    public Result handle(ApiException e) {
        Result result = new Result();
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
        log.error("自定义异常类型:{}",e.getMessage(),e);
        return result;
    }
}
