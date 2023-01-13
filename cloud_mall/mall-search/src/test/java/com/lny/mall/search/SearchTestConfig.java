package com.lny.mall.search;

import com.alibaba.fastjson.JSON;
import com.lny.mall.search.config.ElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTestConfig {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 添加/修改 测试es数据
     */
    @Test
    public void indexDataTest() throws IOException {
        //新建一个索引
        IndexRequest indexRequest = new IndexRequest("users");
        //数据id
        indexRequest.id("1");
        //通过对象转json方式存储到es
        User user = new User();
        user.setUserName("zhangsan");
        user.setGender("男");
        user.setAge(22);
        //转json
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);  //要保存的内容

        //执行操作
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        //输入结果
        System.out.println(index);
    }


    @Data
    class User {
            private String userName;
            private String gender;
            private Integer age;
    }

    @Test
    public void contextLoads(){

        System.out.println(restHighLevelClient);
    }
}
