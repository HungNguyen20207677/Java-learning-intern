package com.sapo.edu.ex5.model.mapper;

import com.sapo.edu.ex5.model.Categories;
import com.sapo.edu.ex5.model.ProductCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCountMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductCount productCount = new ProductCount();
        productCount.setCategoryId(rs.getInt("category_id"));
        productCount.setCategoryCode(rs.getString("category_code"));
        productCount.setProductCount(rs.getInt("product_count"));

        return productCount;
    }
}
