package com.sapo.edu.service.implement;

import com.sapo.edu.dto.WarehousesDTO;
import com.sapo.edu.mapper.WarehousesMapper;
import com.sapo.edu.model.Warehouses;
import com.sapo.edu.repository.WarehousesRepository;
import com.sapo.edu.service.WarehousesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Implement các phương thức từ WarehousesService
@Service
public class WarehousesServiceImpl implements WarehousesService {
    private final WarehousesRepository warehousesRepository;

    @Autowired
    private WarehousesMapper warehousesMapper;

    @Autowired
    public WarehousesServiceImpl(WarehousesRepository warehousesRepository) {
        this.warehousesRepository = warehousesRepository;
    }

    // Lấy tất cả các kho hàng
    @Override
    public ResponseEntity<List<Warehouses>> getAllWarehouses() {
        try {
            List<Warehouses> warehouses = new ArrayList<>(warehousesRepository.findAll());
            return new ResponseEntity<>(warehouses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Lấy một kho hàng theo Id
    @Override
    public ResponseEntity<Warehouses> getWareHouseById(@Valid int id) {
        try {
            Optional<Warehouses> warehouseData = warehousesRepository.findById(id);

            if (warehouseData.isPresent()) {
                return new ResponseEntity<>(warehouseData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tạo mới một kho hàng
    @Override
    public ResponseEntity<Warehouses> createWarehouse(@Valid WarehousesDTO warehousesDTO) {
        try {
            Warehouses _warehouse = warehousesRepository.save(warehousesMapper.toEntity(warehousesDTO));
            return new ResponseEntity<>(_warehouse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cập nhật một kho hàng
    @Override
    public ResponseEntity<Warehouses> updateWarehouse(@Valid int id, @Valid WarehousesDTO warehouseDTO) {
        try {
            Optional<Warehouses> warehouseData = warehousesRepository.findById(id);

            if (warehouseData.isPresent()) {
                Warehouses _warehouse = warehouseData.get();

                // Map các trường từ DTO sang Entity
                Warehouses updatedWarehouse = warehousesMapper.toEntity(warehouseDTO);

                // Cập nhật
                _warehouse.setWarehouseCode(updatedWarehouse.getWarehouseCode());
                _warehouse.setName(updatedWarehouse.getName());
                _warehouse.setLocation(updatedWarehouse.getLocation());

                return new ResponseEntity<>(warehousesRepository.save(_warehouse), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Xóa một kho hàng
    @Override
    public ResponseEntity<HttpStatus> deleteWarehouse(@Valid int id) {
        try {
            warehousesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
