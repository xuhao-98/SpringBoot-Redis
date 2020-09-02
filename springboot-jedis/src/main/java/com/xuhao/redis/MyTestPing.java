package com.xuhao.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 描述：
 *
 * @author XuHao
 * @date 2020/9/2  14:09
 **/
public class MyTestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        System.out.println(jedis.ping());

        System.out.println("清空数据: "+jedis.flushDB());
        System.out.println("判断某个键值是否存在: " + jedis.exists("username"));
        System.out.println("新增<username,xuhao>键值对: " + jedis.set("username", "xuhao"));
        System.out.println("新增<pwd,123456>键值对: " + jedis.set("pwd", "123456"));
        System.out.println("系统中所有的键值如下");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        System.out.println("删除键pwd: " + jedis.del("pwd"));
        System.out.println("判断pwd是否存在: " + jedis.exists("pwd"));
        System.out.println("查看键username所存储值的类型: " + jedis.type("username"));
        System.out.println("重新命名key: " + jedis.rename("username", "name"));
        System.out.println("取出name:" + jedis.get("name"));
        System.out.println("清除当前数据库: " + jedis.flushDB());
        System.out.println("返回数据库中key的数目: " + jedis.dbSize());
        System.out.println("清除所有的数据库: " + jedis.flushAll());
    }
}
