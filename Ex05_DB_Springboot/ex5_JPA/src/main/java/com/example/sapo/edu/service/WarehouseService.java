package com.example.sapo.edu.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface WarehouseService {
    
    // In ra console bảng Warehouses
    void getAllWarehouses();

    // Phương thức để lấy một kho hàng theo ID của nó
    void getWareHouseById();

    // Phương thức để tạo một kho hàng mới
    void createWarehouse();

    // Phương thức để cập nhật một kho hàng đã tồn tại
    void updateWarehouse();

    // Phương thức để xóa một kho hàng bằng ID của nó
    void deleteWarehouse();
}
