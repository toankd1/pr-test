package toankd.cn.prtest.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import toankd.cn.prtest.kafka.model.User;

@Component
@Slf4j
public class InitSend {
    @Value("${spring.kafka.template.default-topic}")
    private String topic1;

    @Autowired
    private KafkaSenderExample kafkaSenderExample;

    // @EventListener
    void initiateSendingMessage(ApplicationReadyEvent event) throws InterruptedException {
        Thread.sleep(5000);
        log.info("---------------------------------");
        kafkaSenderExample.sendMessage("I'll be recevied by MultipleTopicListener, Listener & ClassLevel KafkaHandler", topic1);

        Thread.sleep(5000);
        log.info("---------------------------------");
        kafkaSenderExample.sendCustomMessage(new User("Ngoc", 26), "toankd1-user");
    }
}
