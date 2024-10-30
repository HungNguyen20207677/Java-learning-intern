package com.sapo.edu.repository;

import com.sapo.edu.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

// Repository để tương tác với cơ sở dữ liệu về các danh mục
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}
