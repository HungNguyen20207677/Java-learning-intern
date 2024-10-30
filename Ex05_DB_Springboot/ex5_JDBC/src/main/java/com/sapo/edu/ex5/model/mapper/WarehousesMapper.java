package com.sapo.edu.ex5.model.mapper;

import com.sapo.edu.ex5.model.Warehouses;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WarehousesMapper implements RowMapper<Warehouses> {
    @Override
    public Warehouses mapRow(ResultSet rs, int rowNum) throws SQLException {
        Warehouses warehouse = new Warehouses();
        warehouse.setWarehouseId(rs.getInt("warehouse_id"));
        warehouse.setWarehouseCode(rs.getString("warehouse_code"));
        warehouse.setName(rs.getString("name"));
        warehouse.setLocation(rs.getString("location"));
        warehouse.setCreatedAt(rs.getDate("created_at"));
        warehouse.setUpdatedAt(rs.getDate("updated_at"));
        return warehouse;
    }
}

