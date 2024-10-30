package com.example.sapo.edu.repository;

import com.example.sapo.edu.model.Warehouses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehousesRepository extends JpaRepository<Warehouses, Integer> {
}
