package com.sapo.edu.repository;

import com.sapo.edu.model.Categories;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

// Repository để tương tác với cơ sở dữ liệu về các danh mục
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    // Phân trang danh sách các danh mục
    Page<Categories> findAll(Pageable pageable);

}
