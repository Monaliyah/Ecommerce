package com.June.Goods.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis序列化
 * June
 */
@Configuration
public class RedisConfig {

    /**
     * Redis序列化
     * June
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        /**
         * key序列化
         */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        /**
         * value序列化
         */
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        /**
         * hash-key序列化
         */
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        /**
         * hash-value序列化
         */
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setDefaultSerializer(redisKeySerializer());
        /***
         * 注入连接工厂
         */
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public RedisSerializer<?> redisKeySerializer( ) {
        return new StringRedisSerializer( );
    }


}
