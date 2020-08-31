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

```java
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

> String



