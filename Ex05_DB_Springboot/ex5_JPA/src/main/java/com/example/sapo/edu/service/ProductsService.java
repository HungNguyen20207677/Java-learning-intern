package com.example.sapo.edu.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface ProductsService {
    
    // In ra bảng Products
    void getAllProducts();

    // Phương thức để lấy một sản phẩm theo ID của nó
    void getProductById();

    // Phương thức để tạo một sản phẩm mới
    void createProduct();

    // Phương thức để cập nhật một sản phẩm đã tồn tại
    void updateProduct();

    // Phương thức để xóa một sản phẩm theo ID của nó
    void deleteProduct();

    // Phương thức để lọc danh sách theo tên sản phẩm, có phân trang
    void getProductListPagination();

    // Lọc các sản phẩm có chứa từ %keyword% và thuộc loại danh mục có mã là %categoryCode%
    void filterProducts();

    // Đếm số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
    void getProductCountPerCategory();

    // Lấy 10 sản phẩm có số lượng bán nhiều nhất
    void getTop10BestSellingProducts();
}
