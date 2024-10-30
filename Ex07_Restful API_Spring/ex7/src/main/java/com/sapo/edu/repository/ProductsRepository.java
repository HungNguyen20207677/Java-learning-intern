package com.sapo.edu.repository;

import com.sapo.edu.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

// Repository để tương tác với cơ sở dữ liệu về các sản phẩm
@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    //Lọc sản phẩm theo tên sản phẩm
    List<Products> findAllByName(String name, Pageable pageable);

    // Xóa sản phẩm chứa mã danh mục là categoryId
    void deleteByCategory_CategoryId(int categoryId);

    // Lọc các sản phẩm có chứa từ %keyword% và thuộc loại danh mục có mã là %categoryCode%
    @Query("SELECT p FROM Products p JOIN p.category c WHERE p.name LIKE %:keyword% AND c.categoryCode = :categoryCode")
    List<Products> findFilteredProducts(@Param("keyword") String keyword, @Param("categoryCode") String categoryCode);

    // Đếm số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
    @Query("SELECT c.categoryId, c.categoryCode, COUNT(p.productId) FROM Products p JOIN p.category c GROUP BY c.categoryId, c.categoryCode ORDER BY COUNT(p.productId) DESC")
    List<Object[]> getProductCountPerCategory();

    // Lấy 10 sản phẩm có số lượng bán nhiều nhất
    List<Products> findTop10ByOrderByQuantitySoldDesc();

}
