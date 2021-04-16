package toankd.cn.prtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;
import toankd.cn.prtest.services.RedisService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableKafka
public class PrTestApplication {
    @Autowired
    RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(PrTestApplication.class, args);
    }

    /**
     * This method triggers initialization of the Jedis Pool. Only one Pool will be available
     * in the life of the running application.
     * Running this as @PostConstruct ensures that all the static variables
     * needed to initialize the pool are available.
     * Triggering the initialization from "main" method will result in Null Pointr
     * as the static variables would not have been initialized.
     */
    @PostConstruct
    private void initializeJedisPool() {
        redisService.initializeJedisPool();
    }

    /**
     * This method triggers the Jedis Pool closure.
     * Running this as @PreDestroy will ensure that the pool is closed after
     * the application is shut down.
     */
    @PreDestroy
    private void closeJedisPool() {
        redisService.destroyJedisPool();
    }
}
