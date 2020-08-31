package com.zz.redistemplate.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 推荐使用@Import方式注入该类，在启动类或者是@Configuration
 * @author wqy
 * @version 1.0
 * @date 2020/8/19 10:48
 */
@Slf4j
public class RedisTemplateUtils {

    //锁前缀
    //public static final String LOCK_PREFIX = "redis_lock";
    //锁时间
    public static final int LOCK_EXPIRE = 30000; // ms

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * set值
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * get值
     * @param key
     * @return
     */
    public Object get(Object key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key,Integer expire){
        if(null==expire||expire<0){
            expire = LOCK_EXPIRE;
        }
        return redisTemplate.opsForValue().setIfAbsent(key,key.hashCode(),expire, TimeUnit.SECONDS);
    }
    /**
     * 删除锁
     *
     * @param key
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

}
