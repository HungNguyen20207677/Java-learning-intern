package com.sapo.edu.ex10rabbitmq.service;

import com.sapo.edu.ex10rabbitmq.dto.WarehouseProductStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WarehouseProductStatisticsService {

    void generateStatistics();
    List<WarehouseProductStatistics> getAllStatistics();

    void deleteStatistics();

}
