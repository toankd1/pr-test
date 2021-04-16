package toankd.cn.prtest.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListenersExample {
    @KafkaListener(topics = "toankd1")
    void listener(String message) {
        log.info("toankd listener [{}]", message);
    }
}
