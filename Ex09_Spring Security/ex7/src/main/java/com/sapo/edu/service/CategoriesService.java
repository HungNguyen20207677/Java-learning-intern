package com.sapo.edu.service;

import com.sapo.edu.dto.CategoriesDTO;
import com.sapo.edu.model.Categories;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Giao diện dịch vụ để xử lý các danh mục
@Service
public interface CategoriesService {

    // Phương thức để lấy tất cả các danh mục
    ResponseEntity<List<Categories>> getAllCategories();

    // Phương thức để lấy một danh mục theo ID của nó
    ResponseEntity<Categories> getCategoryById(int id);

    // Phương thức để tạo một danh mục mới
    ResponseEntity<Categories> createCategory(CategoriesDTO categoriesDTO);

    // Phương thức để cập nhật một danh mục đã tồn tại
    ResponseEntity<Categories> updateCategory(int id, CategoriesDTO categoriesDTO);

    // Phương thức để xóa một danh mục theo ID của nó
    void deleteCategory(int id);

    // Phương thức để lấy danh sách danh mục, có phân trang
    ResponseEntity<Page<Categories>> getCategoryListPagination(int currentPage, int pageSize);
}
