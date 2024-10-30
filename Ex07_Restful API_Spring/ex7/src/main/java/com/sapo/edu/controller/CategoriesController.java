package com.sapo.edu.controller;

import com.sapo.edu.dto.CategoriesDTO;
import com.sapo.edu.model.Categories;
import com.sapo.edu.model.Products;
import com.sapo.edu.service.implement.CategoriesServiceImpl;
import com.sapo.edu.service.implement.ProductsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CategoriesController {

    @Autowired
    private CategoriesServiceImpl categoriesService;

    @Autowired
    private ProductsServiceImpl productsService;

    // Lấy tất cả các danh mục
    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    // Lấy thông tin một kho hàng theo ID
    @GetMapping("/categories/{id}")
    public ResponseEntity<Categories> getCategoryById(@RequestParam @Valid int categoryId) {
        return categoriesService.getCategoryById(categoryId);
    }

    // Tạo một danh mục mới
    @PostMapping("/categories")
    public ResponseEntity<Categories> createCategory(@RequestBody @Valid CategoriesDTO categoriesDTO) {
        return categoriesService.createCategory(categoriesDTO);
    }

    // Cập nhật thông tin một danh mục
    @PutMapping("/categories/{id}")
    public ResponseEntity<Categories> updateCategory(@RequestParam @Valid int categoryId, @RequestBody @Valid CategoriesDTO categoriesDTO) {
        return categoriesService.updateCategory(categoryId, categoriesDTO);
    }

    // Xóa một danh mục đồng thời xóa các sản phẩm thuộc danh mục đó
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@RequestParam @Valid int categoryId) {
        categoriesService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy tất cả danh mục, có phân trang
    @GetMapping("/categories/pagination")
    public ResponseEntity<Page<Categories>> getProductListByName(@RequestParam @Valid int currentPage, @RequestParam @Valid int pageSize) {
        return categoriesService.getCategoryListPagination(currentPage, pageSize);
    }
}
