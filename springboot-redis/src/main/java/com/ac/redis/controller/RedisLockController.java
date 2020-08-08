package com.ac.redis.controller;

import com.ac.redis.component.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Redis锁
 * @author: zhangyadong
 * @Date: 2020/8/4 0004 下午 4:58
 * @version: V1.0
 */
@RestController
@RequestMapping("/redisLock")
@Slf4j
public class RedisLockController {
    private final long TIME_OUT = 50 * 1000;

    private final String REDIS_LOCK = "REDIS_LOCK";

    @Autowired
    private RedisLock redisLock;

    /**
     * @Description 测试锁
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2020/8/8 12:34
     */
    @GetMapping("/lock")
    public void lock() {

        try{
            // 加锁
            long currentTime = System.currentTimeMillis();
            //boolean isLock = redisLock.lock(REDIS_LOCK, String.valueOf(currentTime + TIME_OUT));
            boolean isLock = redisLock.lockV2(REDIS_LOCK, String.valueOf(currentTime + TIME_OUT),6000l);
            if (!isLock) {
                throw new RuntimeException("资源已被抢占，换个姿势再试试吧！");
            }

            // do something 若执行其他的过程中，出现异常，则有可能会造成私锁的情况
            int i = 1/0;
            // 解锁
            redisLock.unLock(REDIS_LOCK);
            log.info("redis lock {}","执行了");
        }catch (Exception e){
            log.error("Redis锁错误：{}", e.getMessage(), e);
        }
    }
}
