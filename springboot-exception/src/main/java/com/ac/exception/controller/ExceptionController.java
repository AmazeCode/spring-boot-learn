package com.ac.exception.controller;

import com.ac.exception.common.MessageEnum;
import com.ac.exception.common.Result;
import com.ac.exception.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @Description: 异常Controller
 *  @author: zhangyadong
 *  @Date: 2020/8/8 14:09
 *  @version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/apiexception")
    public Result apiException() {
        throw new ApiException(MessageEnum.ERROR);
    }

    @GetMapping("/runtimeexception")
    public Result runtimeException() {
        throw new RuntimeException();
    }
}
