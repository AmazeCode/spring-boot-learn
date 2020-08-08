package com.ac.redis.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis锁
 * @author: zhangyadong
 * @Date: 2020/8/4 0004 下午 4:57
 * @version: V1.0
 */
@Component
public class RedisLock {


    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @Description 初级锁
     * @params [key, value]
     * @return boolean
     * @author zhangyadong
     * @date 2020/8/8 12:36
     */
    public boolean lock(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void unLock(String key) {
        redisTemplate.delete(key);
    }



    /******************************** 版本2锁 开始
     * Redis 从 2.6.12 版本开始，让 SETNX 操作的同时可以设置一个过期时间。
     * 不过不是直接通过 SETNX 命令实现的，而是通过 SET 命令加上 NX 参数来实现的。
     * 会存在的问题：假如有两个线程 A 和 B，在 A 执行完 do something 之后，恰好 key 到了过期时间，又恰好这时 B 获得了锁，
     * 那么接下来会发生什么？接下来 A 执行 unLock() 会将 B 获得的锁删掉！
     * ***************************************/
    public boolean lockV2(String key, String value,Long timeOut) {
        //进行设置redis的过期时间
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeOut, TimeUnit.MILLISECONDS);
    }
    /********************************V2锁 结束***************************************/

    /******************************** 版本3锁 开始(改进版本2)***********************************/
    public void unLockV3(String key, String value) {
        String oldValue = redisTemplate.opsForValue().get(key);
        if (Objects.nonNull(oldValue) && oldValue.equals(value)) {//判断是否是自己的锁
            redisTemplate.delete(key);
        }
    }
    /******************************** 版本3锁 结束(改进版本2)***********************************/
}
