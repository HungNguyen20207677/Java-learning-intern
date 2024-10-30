package com.sapo.edu.ex5.spring.dao.implement;

import com.sapo.edu.ex5.model.Warehouses;
import com.sapo.edu.ex5.model.mapper.WarehousesMapper;
import com.sapo.edu.ex5.spring.dao.WarehousesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class WarehousesDAOImpl implements WarehousesDAO {
    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_WAREHOUSE = "select * from warehouses where warehouse_id = ?";
    private final String SQL_DELETE_WAREHOUSE = "delete from warehouses where warehouse_id = ?";
    private final String SQL_UPDATE_WAREHOUSE = "update warehouses set warehouse_code = ?, name = ?, location = ?, updated_at = NOW() where warehouse_id = ?";
    private final String SQL_GET_ALL = "select * from warehouses";
    private final String SQL_INSERT_WAREHOUSE = "insert into warehouses (warehouse_code, name, location, created_at, updated_at) values(?,?,?,NOW(),null)";

    @Autowired
    public WarehousesDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Lấy thông tin của một kho hàng dựa trên ID
    @Override
    public Warehouses getWarehouseById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_WAREHOUSE, new Object[] { id }, new WarehousesMapper());
    }

    // Lấy thông tin của tất cả các kho hàng
    @Override
    public List<Warehouses> getAllWarehouses() {
        return jdbcTemplate.query(SQL_GET_ALL, new WarehousesMapper());
    }

    // Xóa một kho hàng
    @Override
    public boolean deleteWarehouse(Warehouses warehouse) {
        return jdbcTemplate.update(SQL_DELETE_WAREHOUSE, warehouse.getWarehouseId()) > 0;
    }

    // Cập nhật thông tin của một kho hàng
    @Override
    public boolean updateWarehouse(Warehouses warehouse) {
        return jdbcTemplate.update(SQL_UPDATE_WAREHOUSE, warehouse.getWarehouseCode(), warehouse.getName(), warehouse.getLocation(), warehouse.getWarehouseId()) > 0;    }

    // Tạo mới một kho hàng
    @Override
    public boolean createWarehouse(Warehouses warehouse) {
        return jdbcTemplate.update(SQL_INSERT_WAREHOUSE, warehouse.getWarehouseCode(), warehouse.getName(), warehouse.getLocation()) > 0;
    }
}
