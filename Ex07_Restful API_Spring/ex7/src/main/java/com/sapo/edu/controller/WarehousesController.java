package com.sapo.edu.controller;

import com.sapo.edu.dto.WarehousesDTO;
import com.sapo.edu.model.Warehouses;
import com.sapo.edu.repository.WarehousesRepository;
import com.sapo.edu.service.implement.WarehousesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class WarehousesController {
    @Autowired
    WarehousesRepository warehousesRepository;

    @Autowired
    private WarehousesServiceImpl warehousesService;

    // Lấy tất cả các kho hàng
    @GetMapping("/warehouses")
    public ResponseEntity<List<Warehouses>> getAllWarehouses() {
        return warehousesService.getAllWarehouses();
    }

    // Lấy thông tin một kho hàng theo ID
    @GetMapping("/warehouses/{id}")
    public ResponseEntity<Warehouses> getProductById(@RequestParam int id) {
        return warehousesService.getWareHouseById(id);
    }

    // Tạo một kho hàng mới
    @PostMapping("/warehouses")
    public ResponseEntity<Warehouses> createWarehouse(@RequestBody WarehousesDTO warehousesDTO) {
        return warehousesService.createWarehouse(warehousesDTO);
    }

    // Cập nhật thông tin một kho hàng
    @PutMapping("/warehouses/{id}")
    public ResponseEntity<Warehouses> updateWarehouse(@RequestParam int id, @RequestBody WarehousesDTO warehousesDTO) {
        return warehousesService.updateWarehouse(id, warehousesDTO);
    }

    // Xóa một kho hàng
    @DeleteMapping("/warehouses/{id}")
    public ResponseEntity<HttpStatus> deleteWarehouse(@RequestParam int id) {
        return warehousesService.deleteWarehouse(id);
    }

}
