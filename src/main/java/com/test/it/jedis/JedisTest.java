package com.test.it.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by caizh on 2016/3/14 0014.
 */
public class JedisTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        String xmlFile = "testit-redis-jedisPool.xml";
        String xmlFile2 = "testit-redis-shardedJedisPool.xml";
        context = new ClassPathXmlApplicationContext(xmlFile);
    }

    @After
    public void after() {
        if(context != null) {
            context.destroy();
        }
    }

    @Test
    public void test() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMinIdle(1);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);
        List<Jedis> jedisList = new LinkedList<Jedis>();
        for(int i = 0; i < 50; i++) {
            Jedis jedis = jedisPool.getResource();

            if(!jedis.exists("test")) {
                System.out.println(jedis.set("test", "test..."));
            }
            System.out.println(jedis.get("test"));
            jedis.append("test", "HELLO");
            System.out.println(jedis.get("test"));
            jedisList.add(jedis);
        }

        for (Jedis jedis : jedisList) {
            jedis.close();
        }
    }

    @Test
    public void testCluster() throws IOException {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        hostAndPortSet.add(new HostAndPort("localhost", 6379));
        hostAndPortSet.add(new HostAndPort("localhost", 6380));
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet, jedisPoolConfig);
        jedisCluster.set("test", "aaa");
        jedisCluster.close();
    }

    @Test
    public void testSpringDataRedis() {
        final StringRedisTemplate redisTemplate = (StringRedisTemplate) context.getBean("redisTemplate");
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            System.out.println("key: " + key);
            final byte[] skey = redisTemplate.getStringSerializer().serialize(key);
            String value = redisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    if(connection.exists(skey)) {
                        return redisTemplate.getStringSerializer().deserialize(connection.get(skey));
                    }
                    return null;
                }
            });
            System.out.println("value: " + value);
        }
        context.destroy();
    }

    public void testSpringDataShardedRedis() {
        ShardedJedisPool shardedJedisPool = (ShardedJedisPool) context.getBean("shardedJedisPool");
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        System.out.println(shardedJedis.get("test"));
        ShardedJedis shardedJedis2 = shardedJedisPool.getResource();
        System.out.println(shardedJedis2.get("test"));
        context.destroy();
    }
}
