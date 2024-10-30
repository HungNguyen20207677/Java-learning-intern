package com.sapo.edu.ex10kafka.kafka;

import com.sapo.edu.ex10kafka.model.WarehouseProductStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "ex10_json", groupId = "myGroup")
    public void consume(WarehouseProductStatistics warehouseProductStatistics) {
        LOGGER.info(String.format("Json message received -> %s", warehouseProductStatistics.toString()));
    }
}
