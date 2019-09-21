package com.demo.manageruser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
@Configuration
public class MyConfig {

/**
     * 基于SpringBoot2 对 RedisCacheManager 的自定义配置
     * @param redisConnectionFactory
     * @return
     */
@Bean
public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    //初始化一个RedisCacheWriter
    RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
    //设置CacheManager的值序列化方式为json序列化
    RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
    RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer);
    RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
    //设置默认超过时期是1天
    defaultCacheConfig.entryTtl(Duration.ofDays(1));
    //初始化RedisCacheManager
    return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
}


}
