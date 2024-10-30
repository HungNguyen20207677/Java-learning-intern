package com.sapo.edu.ex10kafka.repository;

import com.sapo.edu.ex10kafka.model.WarehouseProductStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface WarehouseProductStatisticsRepository extends JpaRepository<WarehouseProductStatistics, Integer> {
    @Modifying
    @Query(value = "INSERT INTO warehouse_product_statistics (warehouse_id, warehouse_code, products_count, statisticized_at) " +
            "SELECT w.warehouse_id, w.warehouse_code, SUM(p.quantity_in_stock) AS products_count, CURRENT_DATE() AS statisticized_at " +
            "FROM warehouses w LEFT JOIN products p ON w.warehouse_id = p.warehouse_id " +
            "GROUP BY w.warehouse_id, w.warehouse_code " +
            "ON DUPLICATE KEY UPDATE " +
            "products_count = VALUES(products_count), statisticized_at = VALUES(statisticized_at)", nativeQuery = true)
    void generateStatistics();

    @Modifying
    @Query(value = "DELETE FROM warehouse_product_statistics",nativeQuery = true)
    void deleteStatistics();

}
