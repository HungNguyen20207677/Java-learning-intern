package com.sapo.edu.controller;

import com.sapo.edu.dto.ProductsDTO;
import com.sapo.edu.model.Products;
import com.sapo.edu.service.implement.ProductsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ProductsController {

    @Autowired
    private ProductsServiceImpl productsService;

    // Lấy tất cả các sản phẩm
    @GetMapping("/products/all")
    public ResponseEntity<List<Products>> getAllProducts() {
        return productsService.getAllProducts();
    }

    // Lấy thông tin một sản phẩm theo ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductById(@RequestParam @Valid int productId) {
        return productsService.getProductById(productId);
    }

    // Tạo mới một sản phẩm
    @PostMapping("/products")
    public ResponseEntity<Products> createProduct(@RequestBody @Valid ProductsDTO productDTO) {
        return productsService.createProduct(productDTO);
    }

    // Cập nhật thông tin một sản phẩm
    @PutMapping("/products/{id}")
    public ResponseEntity<Products> updateProduct(@RequestParam @Valid int productId, @RequestBody @Valid ProductsDTO productDTO) {
        return productsService.updateProduct(productId, productDTO);
    }

    // Xóa một sản phẩm
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@RequestParam @Valid int productId) {
        return productsService.deleteProduct(productId);
    }

    // Lọc danh sách theo tên sản phẩm, có phân trang
    @GetMapping("/products")
    public ResponseEntity<List<Products>> getProductListByName(@RequestParam @Valid String name, @RequestParam @Valid int currentPage) {
        return productsService.getProductListPagination(name, currentPage);
    }

    // Lọc các sản phẩm có chứa từ %keyword% và thuộc loại danh mục có mã là %categoryCode%
    @GetMapping("/products/filter")
    public ResponseEntity<List<Products>> filterProducts(@RequestParam @Valid String keyword, @RequestParam @Valid String categoryCode) {
        return productsService.filterProducts(keyword, categoryCode);
    }


    // Đếm số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
    @GetMapping("/products/product-count")
    public ResponseEntity<List<Object[]>> getProductCountPerCategory() {
        return productsService.getProductCountPerCategory();
    }

    // Lấy 10 sản phẩm có số lượng bán nhiều nhất
    @GetMapping("/products/top-best-selling")
    public ResponseEntity<List<Products>> getTop10BestSellingProducts() {
        return productsService.getTop10BestSellingProducts();
    }
}
