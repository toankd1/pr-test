package toankd.cn.prtest.services;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RedisService {
    private static JedisPool jedisPool;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    private Gson gson = new Gson();

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

    public Boolean hSet(String collection, String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(collection, key, value);
            return true;
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    public Object hGet(String collection, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            String value = jedis.hget(collection, key);
            Object result = gson.fromJson(value, Object.class);
            return result;
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    public Map<String, String> hAll(String collection) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hgetAll(collection);
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            return new HashMap<>();
        }
    }
}
