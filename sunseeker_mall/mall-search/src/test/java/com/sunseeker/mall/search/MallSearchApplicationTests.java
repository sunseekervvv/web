package com.sunseeker.mall.search;

import com.alibaba.fastjson.JSON;
import com.sunseeker.mall.search.config.MallElasticSearchConfiguration;
import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
class MallSearchApplicationTests {
    @Test
    public void indexData() throws IOException {
        String attrs = "1_5存:8存|2_16G:8G";
        String[] split = attrs.split("|");
        System.out.println(split);

    }

}
