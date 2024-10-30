package com.sapo.edu.ex10kafka.kafka;

import com.sapo.edu.ex10kafka.model.WarehouseProductStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
    private KafkaTemplate<String, WarehouseProductStatistics> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, WarehouseProductStatistics> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(WarehouseProductStatistics data) {

        LOGGER.info(String.format("Message sent -> %s", data.toString()));

        Message<WarehouseProductStatistics> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "ex10_json")
                .build();

        kafkaTemplate.send(message);
    }
}
