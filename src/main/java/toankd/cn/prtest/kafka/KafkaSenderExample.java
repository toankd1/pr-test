package toankd.cn.prtest.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import toankd.cn.prtest.kafka.model.User;

@Component
@Slf4j
public class KafkaSenderExample {
    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaTemplate<String, User> userKafkaTemplate;

    @Autowired
    KafkaSenderExample(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, User> userKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.userKafkaTemplate = userKafkaTemplate;
    }

    void sendMessage(String message, String topicName) {
        log.info("Sending : {}", message);
        log.info("--------------------------------");

        kafkaTemplate.send(topicName, message);
    }

    void sendCustomMessage(User user, String topicName) {
        log.info("Sending Json Serializer : {}", user);
        log.info("--------------------------------");

        userKafkaTemplate.send(topicName, user);
    }
}
