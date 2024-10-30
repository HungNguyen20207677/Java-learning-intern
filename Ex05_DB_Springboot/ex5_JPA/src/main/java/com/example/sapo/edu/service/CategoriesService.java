package com.example.sapo.edu.service;

import org.springframework.stereotype.Service;

@Service
public interface CategoriesService {

    // In ra console bảng Categories
    void getAllCategories();

    // Phương thức để lấy một danh mục theo ID của nó
    void getCategoryById();

    // Phương thức để tạo một danh mục mới
    void createCategory();

    // Phương thức để cập nhật một danh mục đã tồn tại
    void updateCategory();

    // Phương thức để xóa một danh mục theo ID của nó
    void deleteCategory();
}
