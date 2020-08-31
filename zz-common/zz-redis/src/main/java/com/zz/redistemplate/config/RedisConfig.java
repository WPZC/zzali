package com.zz.redistemplate.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis基础配置类，解决乱码等问题
 * @author wqy
 */
@Configuration
public class RedisConfig {

    /**
     * redis的yml配置
     * spring:
     *   #redis配置
     *   redis:
     *     password:  #密码（默认为空）
     *     timeout: 6000ms #链接超时时常（毫秒）
     *     cluster:
     *       nodes:
     *         - localhost:6380
     *         - localhost:6381
     *         - localhost:6382
     *         - localhost:6383
     *         - localhost:6384
     *         - localhost:6385
     *     jedis:
     *       pool:
     *         max-active: 1000 #连接池最大链接数（使用负值表示无限制）
     *         max-wait: -1ms #连接池最大阻塞等待时间（使用负值表示无限制）
     *         max-idle: 10 #连接池中最大空闲连接
     *         min-idle: 5 #连接池中最小空闲连接
     */

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisConfig(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        //RedisSerializer<Object> jsonString = new GenericToStringSerializer<>(Object.class);
        RedisSerializer<Object> jsonString = new FastJsonRedisSerializer<>(Object.class);
        // String 的 key 和 hash 的 key 都采用 String 的序列化方式
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        // value 都采用 fastjson 的序列化方式
        redisTemplate.setValueSerializer(jsonString);
        redisTemplate.setHashValueSerializer(jsonString);

        return redisTemplate;
    }

}
