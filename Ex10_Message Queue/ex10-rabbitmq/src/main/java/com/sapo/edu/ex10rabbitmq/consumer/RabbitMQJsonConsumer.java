package com.sapo.edu.ex10rabbitmq.consumer;

import com.sapo.edu.ex10rabbitmq.dto.WarehouseProductStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);


    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void consuneJsonMessage(WarehouseProductStatistics warehouseProductStatistics){
        LOGGER.info(String.format("Received Json message -> %s", warehouseProductStatistics.toString()));

    }

}
