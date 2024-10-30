package com.sapo.edu.ex5.spring.dao;

import com.sapo.edu.ex5.model.Warehouses;

import java.util.List;

// Giao diện định nghĩa các phương thức để tương tác với các kho hàng trong cơ sở dữ liệu.
public interface WarehousesDAO {
    // Lấy thông tin của một kho hàng dựa trên ID.
    Warehouses getWarehouseById(int id);

    // Lấy thông tin của tất cả các kho hàng.
    List<Warehouses> getAllWarehouses();

    // Xóa một kho hàng.
    boolean deleteWarehouse(Warehouses warehouse);

    // Cập nhật thông tin của một kho hàng.
    boolean updateWarehouse(Warehouses warehouse);

    // Tạo mới một kho hàng.
    boolean createWarehouse(Warehouses warehouse);
}
