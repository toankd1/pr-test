package toankd.cn.prtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "toankd.cn")
public class PrTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ct = SpringApplication.run(PrTestApplication.class, args);
        System.out.println(ct);
    }
}
