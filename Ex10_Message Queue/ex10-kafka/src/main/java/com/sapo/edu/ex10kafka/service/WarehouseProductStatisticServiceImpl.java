package com.sapo.edu.ex10kafka.service;

import com.sapo.edu.ex10kafka.model.WarehouseProductStatistics;
import com.sapo.edu.ex10kafka.repository.WarehouseProductStatisticsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseProductStatisticServiceImpl implements WarehouseProductStatisticsService {
    private final WarehouseProductStatisticsRepository warehouseProductStatisticsRepository;

    public WarehouseProductStatisticServiceImpl(WarehouseProductStatisticsRepository warehouseProductStatisticsRepository) {
        this.warehouseProductStatisticsRepository = warehouseProductStatisticsRepository;
    }

    @Override
    public void generateStatistics() {
        warehouseProductStatisticsRepository.generateStatistics();
    }

    @Override
    public List<WarehouseProductStatistics> getAllStatistics() {
        try {
            List<WarehouseProductStatistics> warehouseProductStatistics = new ArrayList<>(warehouseProductStatisticsRepository.findAll());
            return warehouseProductStatistics;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStatistics() {
        try {
            warehouseProductStatisticsRepository.deleteStatistics();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
