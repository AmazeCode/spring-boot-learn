package com.ac.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Redis控制层
 * @author: zhangyadong
 * @Date: 2020/8/4 0004 下午 4:33
 * @version: V1.0
 */
@RestController
public class RedisController {

    // 配置 StringRedisTemplate,交给spring管理，SpringRedisTemplate 实现了RedisTemplate，RedisTemplate 默认是采用 JDK 的序列化方式来转换对象的
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        return stringRedisTemplate;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        // 向 Redis 中添加一个 key 为 hello，Value 为 world 的记录
        stringRedisTemplate.opsForValue().set("hello","world");
        // 获取 Redis 中 key 为 hello 的值
        return stringRedisTemplate.opsForValue().get("hello");
    }

    @GetMapping("/say")
    public String  say(){
        //对同一个key进行多次操作增加效率
        BoundValueOperations<String, String> boundValueOps = stringRedisTemplate.boundValueOps("hello");
        boundValueOps.set("world");
        return boundValueOps.get();
    }

    @GetMapping("/serialize")
    public Long setSerializeKey(){
        redisTemplate.opsForValue().set("key","value");
        return redisTemplate.getExpire("key");
    }
}
