package com.sapo.edu.ex5.spring.dao.implement;

import com.sapo.edu.ex5.model.Categories;
import com.sapo.edu.ex5.model.mapper.CategoriesMapper;
import com.sapo.edu.ex5.spring.dao.CategoriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CategoriesDAOImpl implements CategoriesDAO {
    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_CATEGORY = "select * from categories where category_id = ?";
    private final String SQL_DELETE_CATEGORY = "delete from categories where category_id = ?";
    private final String SQL_UPDATE_CATEGORY = "update categories set category_code = ?, name = ?, description = ?, updated_at = NOW() where category_id = ?";
    private final String SQL_GET_ALL = "select * from categories";
    private final String SQL_INSERT_CATEGORY = "insert into categories (category_code, name, description, created_at, updated_at) values(?,?,?,NOW(),null)";

    @Autowired
    public CategoriesDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Lấy thông tin của một danh mục dựa trên ID
    @Override
    public Categories getCategoryById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_CATEGORY, new Object[] { id }, new CategoriesMapper());
    }

    // Lấy thông tin của tất cả các danh mục
    @Override
    public List<Categories> getAllCategories() {
        return jdbcTemplate.query(SQL_GET_ALL, new CategoriesMapper());
    }

    // Xóa một danh mục
    @Override
    public boolean deleteCategory(Categories category) {
        return jdbcTemplate.update(SQL_DELETE_CATEGORY, category.getCategoryId()) > 0;
    }

    // Cập nhật thông tin của một danh mục
    @Override
    public boolean updateCategory(Categories category) {
        return jdbcTemplate.update(SQL_UPDATE_CATEGORY, category.getCategoryCode(), category.getName(), category.getDescription(), category.getCategoryId()) > 0;}

    // Tạo mới một danh mục
    @Override
    public boolean createCategory(Categories category) {
        return jdbcTemplate.update(SQL_INSERT_CATEGORY, category.getCategoryCode(), category.getName(), category.getDescription()) > 0;
    }
}
