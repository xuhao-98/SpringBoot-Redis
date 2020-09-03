package com.xuhao.springcloudredis;

import com.xuhao.springcloudredis.pojo.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;

import java.io.ObjectOutput;

@SpringBootTest
class SpringcloudRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        //opsForValue()操作字符串
        redisTemplate.opsForValue().set("name","xuhao");
        System.out.println(redisTemplate.opsForValue().get("name"));

        //通过获取连接操作
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
    }
    @Test
    public void test() throws JSONException {
        User user = new User("xuhao",22);
        redisTemplate.opsForValue().set("name",user);
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}
