package com.sapo.edu.service;

import com.sapo.edu.dto.WarehousesDTO;
import com.sapo.edu.model.Warehouses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Giao diện dịch vụ để quản lý các kho hàng
@Service
public interface WarehousesService {

    // Phương thức để lấy tất cả các kho hàng
    ResponseEntity<List<Warehouses>> getAllWarehouses();

    // Phương thức để lấy một kho hàng theo ID của nó
    ResponseEntity<Warehouses> getWareHouseById(int id);

    // Phương thức để tạo một kho hàng mới
    ResponseEntity<Warehouses> createWarehouse(WarehousesDTO warehouseDTO);

    // Phương thức để cập nhật một kho hàng đã tồn tại
    ResponseEntity<Warehouses> updateWarehouse(int id,  WarehousesDTO warehouseDTO);

    // Phương thức để xóa một kho hàng bằng ID của nó
    ResponseEntity<HttpStatus> deleteWarehouse(int id);
}
