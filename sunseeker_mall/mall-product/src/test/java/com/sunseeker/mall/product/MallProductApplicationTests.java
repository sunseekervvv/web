package com.sunseeker.mall.product;

import com.aliyun.oss.OSSClient;
import com.sunseeker.mall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void contextLoads() throws InterruptedException {
        System.out.println(redissonClient);
        System.out.println(redissonClient.getConfig());
        RLock aaa = redissonClient.getLock("aaa");
        aaa.lock();
        Thread.sleep(10000);
        System.out.println("aaa");
        aaa.unlock();
    }

}
