package com.sunseeker.mall.product.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;


@Configuration
@Data
@ConfigurationProperties(prefix = "spring.cache.redis")
public class MyRedisCacheConfig {
    private Duration timeToLive;
    private boolean cacheNullValues = true;
    private String keyPrefix;
    private boolean useKeyPrefix = true;
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        if (timeToLive != null) {
            config = config.entryTtl(timeToLive);
        }
        if (keyPrefix != null) {
            config = config.prefixCacheNameWith(keyPrefix);
        }
        if(!useKeyPrefix){
            config = config.disableKeyPrefix();
        }
        if(!cacheNullValues){
            config = config.disableCachingNullValues();
        }
        return RedisCacheManager.builder(factory).cacheDefaults(config).build();
    }


}
