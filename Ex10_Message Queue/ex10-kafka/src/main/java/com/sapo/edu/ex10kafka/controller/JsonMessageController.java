package com.sapo.edu.ex10kafka.controller;

import com.sapo.edu.ex10kafka.Ex10KafkaApplication;
import com.sapo.edu.ex10kafka.kafka.JsonKafkaProducer;
import com.sapo.edu.ex10kafka.model.WarehouseProductStatistics;
import com.sapo.edu.ex10kafka.service.WarehouseProductStatisticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class JsonMessageController {

    public static void main(String[] args) {
        SpringApplication.run(Ex10KafkaApplication.class, args);
    }

    private JsonKafkaProducer kafkaProducer;

    private final WarehouseProductStatisticServiceImpl warehouseProductStatisticService;

    @Autowired
    public JsonMessageController(JsonKafkaProducer kafkaProducer, WarehouseProductStatisticServiceImpl warehouseProductStatisticService) {
        this.kafkaProducer = kafkaProducer;
        this.warehouseProductStatisticService = warehouseProductStatisticService;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish() {
        warehouseProductStatisticService.deleteStatistics();
        warehouseProductStatisticService.generateStatistics();
        for (WarehouseProductStatistics statistics : warehouseProductStatisticService.getAllStatistics()) {
            kafkaProducer.sendMessage(statistics);
        }
        return ResponseEntity.ok("Json message sent to kafka topic");
    }
}
