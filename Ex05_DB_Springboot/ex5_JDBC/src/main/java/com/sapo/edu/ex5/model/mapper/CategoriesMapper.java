package com.sapo.edu.ex5.model.mapper;

import com.sapo.edu.ex5.model.Categories;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesMapper implements RowMapper<Categories> {
    @Override
    public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {
        Categories category = new Categories();
        category.setCategoryId(rs.getInt("category_id"));
        category.setCategoryCode(rs.getString("category_code"));
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));
        category.setCreatedAt(rs.getDate("created_at"));
        category.setUpdatedAt(rs.getDate("updated_at"));
        return category;
    }
}
