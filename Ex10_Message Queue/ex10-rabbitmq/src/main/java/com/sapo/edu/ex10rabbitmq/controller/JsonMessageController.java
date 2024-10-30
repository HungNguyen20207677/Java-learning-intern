package com.sapo.edu.ex10rabbitmq.controller;

import com.sapo.edu.ex10rabbitmq.dto.WarehouseProductStatistics;
import com.sapo.edu.ex10rabbitmq.publisher.RabbitMQJsonProducer;
import com.sapo.edu.ex10rabbitmq.service.WarehouseProductStatisticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbitmq")
public class JsonMessageController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;
    private final WarehouseProductStatisticServiceImpl warehouseProductStatisticService;

    @Autowired
    public JsonMessageController(RabbitMQJsonProducer rabbitMQJsonProducer, WarehouseProductStatisticServiceImpl warehouseProductStatisticService) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
        this.warehouseProductStatisticService = warehouseProductStatisticService;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish() {
        warehouseProductStatisticService.deleteStatistics();
        warehouseProductStatisticService.generateStatistics();
        for (WarehouseProductStatistics statistics : warehouseProductStatisticService.getAllStatistics()) {
            rabbitMQJsonProducer.sendJsonMessage(statistics);
        }
        return ResponseEntity.ok("Json message sent to RabbitMQ...");
    }
}
