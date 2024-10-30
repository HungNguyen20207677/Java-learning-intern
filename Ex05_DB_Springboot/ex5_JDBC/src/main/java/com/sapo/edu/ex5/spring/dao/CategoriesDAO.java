package com.sapo.edu.ex5.spring.dao;

import com.sapo.edu.ex5.model.Categories;

import java.util.List;

// Giao diện định nghĩa các phương thức để tương tác với các danh mục trong cơ sở dữ liệu.
public interface CategoriesDAO {
    // Lấy thông tin của một danh mục dựa trên ID.
    Categories getCategoryById(int id);

    // Lấy thông tin của tất cả các danh mục.
    List<Categories> getAllCategories();

    // Xóa một danh mục.
    boolean deleteCategory(Categories category);

    // Cập nhật thông tin của một danh mục.
    boolean updateCategory(Categories category);

    // Tạo mới một danh mục.
    boolean createCategory(Categories category);
}
