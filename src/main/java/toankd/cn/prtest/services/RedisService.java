package toankd.cn.prtest.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Service
@Slf4j
public class RedisService {
    private static JedisPool jedisPool;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    /**
     * Initialize Jedis Pool with Default Timeout
     */
    public void initializeJedisPool() {
        if (jedisPool == null) {
            jedisPool = new JedisPool(new JedisPoolConfig(),
                    host,
                    Integer.parseInt(port),
                    Protocol.DEFAULT_TIMEOUT);
            log.info("Jedis Pool is Initialized");
        }
    }

    /**
     * Method to close and detroy Jedis Pool
     */
    public void destroyJedisPool() {
        if (jedisPool != null && !jedisPool.isClosed()) {
            jedisPool.close();
            jedisPool.destroy();
            log.info("Closed and Destroyed Jedis Pool");
        }
    }

}
