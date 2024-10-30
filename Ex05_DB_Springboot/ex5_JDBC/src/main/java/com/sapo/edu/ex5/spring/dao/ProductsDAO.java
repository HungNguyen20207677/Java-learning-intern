package com.sapo.edu.ex5.spring.dao;

import com.sapo.edu.ex5.model.Products;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

// Giao diện định nghĩa các phương thức để tương tác với các sản phẩm trong cơ sở dữ liệu.
public interface ProductsDAO {
    // Lấy thông tin của một sản phẩm dựa trên ID.
    Products getProductById(int id);

    // Lấy thông tin của tất cả các sản phẩm.
    List<Products> getAllProducts();

    // Xóa một sản phẩm.
    boolean deleteProduct(Products product);

    // Cập nhật thông tin của một sản phẩm.
    boolean updateProduct(Products product);

    // Tạo mới một sản phẩm.
    boolean createProduct(Products product);

    // Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple' (Sử dụng NamedParameterJdbcTemplate)
    List<Products> filterProduct(String name, String categoryCode);

    // Lấy top n sản phẩm có số lượng bán nhiều nhất
    List<Products> findTopSelling(int limitValue);


    // Đếm số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
    List<Object[]> getProductCountPerCategory();

    // Sử dụng Procedure: Tìm sản phẩm theo mã hoặc tên và theo mã kho và theo danh mục và theo thời gian tạo,
    // có phân trang (theo 2 kiểu: phân trang theo kiểu chọn số trạng và phân trang theo kiểu xem thêm, xem nữa, mỗi trang 10 bản ghi),
    // nếu điều kiện nào không nhập thì bỏ qua.
    List<Products> searchProductPagination(String productCode, String name, String warehouseCode, String categoryCode, Date startDate, Date endDate, boolean isPaginationByNumber, int currentPage, int offset);
}
