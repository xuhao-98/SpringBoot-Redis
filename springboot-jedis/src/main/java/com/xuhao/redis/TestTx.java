package com.xuhao.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 描述：
 *
 * @author XuHao
 * @date 2020/9/2  15:01
 **/
public class TestTx {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        System.out.println(jedis.ping());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "xuhao");
        String s = jsonObject.toString();
        //开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("user1",s);
            multi.set("user2",s);
            multi.exec();
        } catch (Exception e) {
            multi.discard();
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }
    }
}
