package com.sapo.edu.service;

import com.sapo.edu.dto.ProductsDTO;
import com.sapo.edu.model.Products;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductsService {

    // Phương thức để lấy tất cả các sản phẩm
    ResponseEntity<List<Products>> getAllProducts();

    // Phương thức để lấy một sản phẩm theo ID của nó
    ResponseEntity<Products> getProductById(int id);

    // Phương thức để tạo một sản phẩm mới
    ResponseEntity<Products> createProduct(ProductsDTO productsDTO);

    // Phương thức để cập nhật một sản phẩm đã tồn tại
    ResponseEntity<Products> updateProduct(int id, ProductsDTO productsDTO);

    // Phương thức để xóa một sản phẩm theo ID của nó
    ResponseEntity<HttpStatus> deleteProduct(int id);

    // Phương thức để lọc danh sách theo tên sản phẩm, có phân trang
    ResponseEntity<List<Products>> getProductListPagination(String name, int currentPage);

    // Lọc các sản phẩm có chứa từ %keyword% và thuộc loại danh mục có mã là %categoryCode%
    ResponseEntity<List<Products>> filterProducts(String keyword, String categoryCode);

    // Đếm số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
    ResponseEntity<List<Object[]>> getProductCountPerCategory();

    // Lấy 10 sản phẩm có số lượng bán nhiều nhất
    ResponseEntity<List<Products>> getTop10BestSellingProducts();
}
