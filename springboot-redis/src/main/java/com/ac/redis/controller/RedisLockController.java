package com.ac.redis.controller;

import com.ac.redis.component.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: zhangyadong
 * @Date: 2020/8/4 0004 下午 4:58
 * @version: V1.0
 */
@RestController
@RequestMapping("/redisLock")
public class RedisLockController {
    private final long TIME_OUT = 50 * 1000;

    private final String REDIS_LOCK = "REDIS_LOCK";

    @Autowired
    private RedisLock redisLock;

    @GetMapping("/lock")
    public void lock() {

        // 加锁
        long currentTime = System.currentTimeMillis();
        boolean isLock = redisLock.lock(REDIS_LOCK, String.valueOf(currentTime + TIME_OUT));
        if (!isLock) {
            throw new RuntimeException("资源已被抢占，换个姿势再试试吧！");
        }

        // do something 若执行其他的过程中，出现异常，则有可能会造成私锁的情况

        // 解锁
        redisLock.unLock(REDIS_LOCK);

    }
}
