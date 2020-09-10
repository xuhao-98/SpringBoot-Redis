

## Nosql自述

#### 为什么要用Nosql

>1.单机MySQL的年代!

90年代,一个基本的网站访问量一般不会太大,单个数据库完全足够!

那个时候,更多的去使用Html-服务器根本没有太大压力!

1.数据量太大,一个机器放不下

2.数据的索引,一个机器内存不够

3,访问量过多,一个服务器承受不了;

> 2.Memcached(缓存)+MySQL+直接拆分

网站80%的情况都是在读,每次都要去查询数据库的就十分麻烦.为了减轻压力,我们可以使用缓存.

> 3.分库分表
>
> NoSQL特点

1.方便扩展(数据之间没有关系,很好扩展)

2.大数据量高性能(Redis一秒写8w,读11w)

3.数据库类型多样

4.传统RDBMS和NOSQL

> -传统的RDBMS
>
> -结构化组织
>
> -SQL
>
> -数据和关系都存在单独的表中
>
> -操作简单,数据定义语言
>
> -基础的事务操作

> NOSQL
>
> -不仅仅是数据
>
> -没有固定的查询语言
>
> -键值对存储,列存储,图形数据库
>
> -最终一致性
>
> CAP定理 和BASE
>
> -高性能,高可扩展



## Redis入门

#### 概述

> Redis是什么 
>
> *Redis*（Remote Dictionary Server )，即远程字典服务.  K-V键值对.
>
> 当下最热门的Nosql技术,被人们称为	结构化数据库
>
> 

#### Redis能够干嘛?

1.内存存储、持久化、内存中是断电即失、所以持久化很重要（RDB、AOF）

2、效率高，高速缓存

3.发布信息分析

4.地图信息分析

5.计时器、计数器（浏览量）

#### Redis特性：

1.多样性的数据类型

2、持久化

3、集群、

4、事务

### 如果在安装LInux下的Redis

最新的redis6.0 需要gcc 5以上，先升级gcc
sudo yum install centos-release-scl
sudo yum install devtoolset-7-gcc*
scl enable devtoolset-7 bash
然后再make 就好了



### 测试性能

redis-benchmark是一个压力测试的工具!

官方自带的性能测试工具. 使用方式:

redis-benchmark命令参数:

-h    指定服务器主机名                 127.0.0.1

-p     指定服务器端口                    6379

-c     指定默认并发数                    50

-n    指定默认请求书                     10000

....



### 基础知识

1.dis默认有16 个数据库

2.使用第0 个,可以使用select进行切换

3.DBSIZE 查看数据库大小

```yaml
127.0.0.1:6379> select 3
OK
127.0.0.1:6379[3]> DBSIZE
(integer) 0
127.0.0.1:6379[3]> 
```

4.keys * 查看所有的key

5. 清空全部:flushall,清空当前库flushdb



> Redis是单线程的

Redis的读写速度是非常快,基于内存操作,CPU不是Redis的性能瓶颈,是根据机器内存和网络带宽.

Rdis是c语言编写,官方提供的数据为100000+的QPS,完全不比同样的使用Memecache的差

>Redis单线程为什么还这么快!

误区:
1.高性能的服务器不一定是多线程

2.多线程一定比单线程效率高(CPU上下文会切换)

核心:

Redis是将所有的数据全部都放到内存,所以说使用单线程去操作效率就是最高,多线程会有CPU上下文切换,会消耗时间,对于内存系统来说,如果没有上下文切换效率就是最高的!多次读写都是在一个cpu上,就内存情况下,这个就是最佳方案.



Redis是一个开源（BSD许可），内存存储的数据结构服务器，可用作数据库，高速缓存和消息队列代理。它支持[字符串](https://www.redis.net.cn/tutorial/3508.html)、[哈希表](https://www.redis.net.cn/tutorial/3509.html)、[列表](https://www.redis.net.cn/tutorial/3510.html)、[集合](https://www.redis.net.cn/tutorial/3511.html)、[有序集合](https://www.redis.net.cn/tutorial/3512.html)，[位图](https://www.redis.net.cn/tutorial/3508.html)，[hyperloglogs](https://www.redis.net.cn/tutorial/3513.html)等数据类型。内置复制、[Lua脚本](https://www.redis.net.cn/tutorial/3516.html)、LRU收回、[事务](https://www.redis.net.cn/tutorial/3515.html)以及不同级别磁盘持久化功能，同时通过Redis Sentinel提供高可用，通过Redis Cluster提供自动[分区](https://www.redis.net.cn/tutorial/3524.html)

### Redis-key

#### 1.exists

127.0.0.1:6379> keys *
1) "name"
2) "age"
127.0.0.1:6379> exists age  //判断时候存在此键 
(integer) 1
127.0.0.1:6379> 

#### 2.keys *

查看所有key值

#### 3.move  

move key name 1 

#### 4.EXPIRE(设置过期时间)

EXPIRE name 10  //name这个键10 秒过期

### 5.查看当前key的剩余时间

ttl key

### 6.查看key的类型

type key



### 五大数据类型

> String、List、Set、Hash、Zset

#### String（字符串）

​	1.append 值后添加字符串

​	2strlen 获取字符串长度

```yaml
127.0.0.1:6379> set name xuhao
OK
127.0.0.1:6379> app
(error) ERR unknown command `app`, with args beginning with: 
127.0.0.1:6379> append name xiaowang
(integer) 13
127.0.0.1:6379> get name
"xuhaoxiaowang"
127.0.0.1:6379> strlen name
(integer) 13
```

3.设置一个初始量进行加减-类似点赞、播放量

```yaml
127.0.0.1:6379> set m 0 //设置一个初始值
OK
127.0.0.1:6379> incr m //incr 每次加1
(integer) 1
127.0.0.1:6379> get m
"1"
127.0.0.1:6379> decr m //decr 每次减1
(integer) 0
127.0.0.1:6379> get m
"0"
```

4.一次增加自定义数值

```yaml
127.0.0.1:6379> incrby m 10 //incrby + key + 数字 增长几个
(integer) 10
127.0.0.1:6379> get m
"10"
127.0.0.1:6379> decrby m 10//decrby +key + 数字 减少几个
(integer) 0
127.0.0.1:6379> get m
"0"
```

####################################################

5.获取字符串 根据范围

```yaml
127.0.0.1:6379> getrange name 0 4 // getrange 键值 开始位置 结束位置
"hello"
```

### List(基本的数据类型)

在redis里面可以将list玩成栈、队列

所有的list命令都是 l 开头

```yaml
127.0.0.1:6379> lpush list 1 //将一个值或者多个值插入到列表头部 lpush左
(integer) 1
127.0.0.1:6379> lpush list 2
(integer) 2
127.0.0.1:6379> lpush list 3
(integer) 3
127.0.0.1:6379> lrange list 0 -1 // 获取list中的值
1) "3"
2) "2"
3) "1"
127.0.0.1:6379> rpush list 0 //将一个值或者多个值插入到列表头部 rpush右
(integer) 4
127.0.0.1:6379> lrange list 0 -1
1) "3"
2) "2"
3) "1"
4) "0"
127.0.0.1:6379> 

```

移除元素

```yaml
127.0.0.1:6379> rpop list// 从右边 移除第一个
"0"
127.0.0.1:6379> lpop list //从左边移除第一个
"3"
127.0.0.1:6379> 
```

获取list中的某个值 :lindex

```yaml
127.0.0.1:6379> lindex list 0
"2"
127.0.0.1:6379> 
```

llen :返回list长度

```yaml
127.0.0.1:6379> lrange list 0 -1
1) "5"
2) "4"
3) "2"
4) "1"
5) "0"
127.0.0.1:6379> llen list
(integer) 5
```

移除指定的值:

```yaml
127.0.0.1:6379> lrem list 1 5 //移除指定的值
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "4"
2) "2"
3) "1"
4) "0"
```



......

### set(set中的值不能重复)

```yaml
127.0.0.1:6379> sadd myset hello //set 值
(integer) 1
127.0.0.1:6379> sadd myset xuhao
(integer) 1
127.0.0.1:6379> smembers myset //查看set中的值
1) "xuhao"
2) "hello"
127.0.0.1:6379> sismember myset hell //判断是否有这个值
(integer) 0

```

```yaml
127.0.0.1:6379> scard myset //查看set中元素个数
(integer) 2
```

```yaml
127.0.0.1:6379> sadd myset xxxx
(integer) 1
127.0.0.1:6379> srem myset xxxx //移除set中某个元素
(integer) 1
127.0.0.1:6379> smembers myset
1) "xuhao"
2) "hello"

```

随机(无需不重复)

```yaml
127.0.0.1:6379> srandmember myset //随机获取元素
"hello"
127.0.0.1:6379> srandmember myset
"hello"
127.0.0.1:6379> srandmember myset
"hello"
127.0.0.1:6379> srandmember myset
"xuhao"
```

set中的交集和并集

```yaml
127.0.0.1:6379> sadd set a
(integer) 1
127.0.0.1:6379> sadd set b
(integer) 1
127.0.0.1:6379> sadd set c
(integer) 1
127.0.0.1:6379> sadd set1 b
(integer) 1
127.0.0.1:6379> sadd set1 f
(integer) 1
127.0.0.1:6379> sadd set1 c
(integer) 1
127.0.0.1:6379> SDIFF set set1 //差集
1) "a"
127.0.0.1:6379> SINTER set set1 //交集元素
1) "c"
2) "b"
127.0.0.1:6379> SUNION set set1 //并集元素
1) "a"
2) "b"
3) "c"
4) "f"
```

### hash(哈希-map集合)

Map集合---key-<k,v>值是一个map集合

存取值

```yaml
127.0.0.1:6379> HSET hash name xuhao //存值 key-<k,v>
(integer) 1
127.0.0.1:6379> HGET hash name //取值
"xuhao"
```

### zset(有序集合)

在set的基础上,增加了一个值,set k1 score1 v1

```bash
127.0.0.1:6379> zadd set 1 one //添加一个元素 ,并带上标识
(integer) 1
127.0.0.1:6379> zadd set 2 two 3 three //添加多元素 
(integer) 2
127.0.0.1:6379> ZRANGE set 0 -1
1) "one"
2) "two"
3) "three"
##################################################################
排序
127.0.0.1:6379> zadd s 2000  xx
(integer) 1
127.0.0.1:6379> zadd s 1000  yy
(integer) 1
127.0.0.1:6379> zadd s 3000  zz
(integer) 1
127.0.0.1:6379> ZRANGE s 0 -1
1) "yy"
2) "xx"
3) "zz"
127.0.0.1:6379> ZRANGEBYSCORE s -inf +inf//默认排序从最小到最大
1) "yy"
2) "xx"
3) "zz"
127.0.0.1:6379> ZRANGEBYSCORE s -inf +inf withscores
1) "yy"
2) "1000"
3) "xx"
4) "2000"
5) "zz"
6) "3000"
##################################################################
127.0.0.1:6379> ZREM s zz //移除元素
(integer) 1
127.0.0.1:6379> ZRANGE s 0 -1
1) "yy"
2) "xx"


```



### redis事务

本质:一组命令的集合,一个事务中的所有命令都会序列化,在事务过程中,会按照顺序执行.

###### Redis事务没有隔离级别的概念:

所有的命令都是在事务中,并没有直接被执行,只有发起执行命令的时候才会被执行!

###### Redis单条保证原子性的,但是事务不保证原子性.

redis的事务:

1.开启事务(multi)

2.命令入队(....)

3.执行事务(exec)

```bash
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k1
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> EXEC
1) OK
2) OK
3) "v1"
4) OK
#############################################################
放弃事务
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set m1 n1
QUEUED
127.0.0.1:6379> set m2 n2
QUEUED
127.0.0.1:6379> set m3 n3
QUEUED
127.0.0.1:6379> DISCARD
OK
127.0.0.1:6379> get m1
(nil)
################################################################
编译型异常,事务不会被执行
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set k1 k2
QUEUED
127.0.0.1:6379> set k2 k2
QUEUED
127.0.0.1:6379> set k3 k3
QUEUED
127.0.0.1:6379> get bc
QUEUED
127.0.0.1:6379> getset b //错误命令
(error) ERR wrong number of arguments for 'getset' command
127.0.0.1:6379> get k3
QUEUED
127.0.0.1:6379> EXEC
(error) EXECABORT Transaction discarded because of previous errors.
127.0.0.1:6379> get k3
(nil)
运行异常(1/0)
127.0.0.1:6379> set k1 "v1"
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> incr k1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> get k3
QUEUED
127.0.0.1:6379> EXEC
1) (error) ERR value is not an integer or out of range
2) OK
3) OK
4) "v3"

```

> 监控 watch

#### 悲观锁:

​	很悲观,认为时候都会出问题,无论做什么都会加锁!

#### 乐观锁:

​	很乐观,认为什么时候都不会出问题,所以不会上锁!更新数据的时候会判断一下,是否有人修改数据

1.获取version

2.更新的时候比较



### Jedis

> 什么是Jedis?是官方推荐的java连接开发工具!使用java操作redis中间件

1.导入依赖

```bash
<dependencies>
    <!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
        <version>3.3.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.73</version>
    </dependency>

</dependencies>
```

2.编码测试

```bash
public static void main(String[] args) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    jedis.auth("123456");
    System.out.println(jedis.ping());
}
```

3.api测试

```bash
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
```

### springboot整合

说明:在springboot2.x之后,原来使用的jedis被lettuce替换?

jedis:才用直连,多线程操作的话,是不安全的,如果想要避免不安全的,使用jedis pool连接池,更像BIO模式.

lettuce:采用netty,实例可以在多个线程中共享,不存在线程不安全的情况,可以减少线程数据,更像NIO.

1.导入依赖

```bash
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

2.配置连接

```yaml
spring:
  redis:
    host: 127.0.0.1
    port: 6379
    database: 4
```

3.测试连接

```bash
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

}
```

### RDB(Redis DataBase)

Redis是内存数据库,如果不将内存中的数据库保存到硬盘,name一旦服务器进程退出,服务器中的数据库状态也会消失,所以Redis提供了持久化功能.

​		在指定的时间间隔内将内存中的数据集快照写入磁盘,也就是行话讲的Snapshot快照,他恢复时是将快照文件直接读到内存里.

​		Redis会单独创建一个子进程来进行持久化,会先将数据写入到一个临时文件中,持久化过程都结束了,再用这个临时文件替换上次持久化好的文件.整个过程中,主进程是不会进行任何IO操作.这就确保了极高的性能.如果需要进行大规模数据的恢复,且对于数据恢复的完整性不是非常敏感,那RDB方式要比AOF方式更加的高校.RDB的缺点是最后一次持久后的数据可能会丢失.我们默认的就是RDB,一般情况下不需要这个配置

RDB保存的文件是  dump.rdb,都是在配置文件中配置

1.只需要将rdb文件存放在我们的redis启动目录就可以,redis启动时会自动监测dump.rdb回复其中的数据

2.只需查看位置,/usr/local/bin

​		

