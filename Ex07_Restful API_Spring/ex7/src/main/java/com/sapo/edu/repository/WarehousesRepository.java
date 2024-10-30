package com.sapo.edu.repository;

import com.sapo.edu.model.Warehouses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository để tương tác với cơ sở dữ liệu về các kho hàng
@Repository
public interface WarehousesRepository extends JpaRepository<Warehouses, Integer> {
}
