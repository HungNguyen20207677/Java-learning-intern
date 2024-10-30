package com.sapo.edu.ex10kafka.service;

import com.sapo.edu.ex10kafka.model.WarehouseProductStatistics;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WarehouseProductStatisticsService {

    void generateStatistics();
    List<WarehouseProductStatistics> getAllStatistics();

    void deleteStatistics();

}
