package com.sapo.edu.service.implement;

import com.sapo.edu.dto.CategoriesDTO;
import com.sapo.edu.mapper.CategoriesMapper;
import com.sapo.edu.model.Categories;
import com.sapo.edu.repository.CategoriesRepository;
import com.sapo.edu.repository.ProductsRepository;
import com.sapo.edu.service.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Implement các phương thức từ CategoriesService
@Service
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, ProductsRepository productsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.productsRepository = productsRepository;
    }

    // Lấy tất cả danh mục
    @Override
    public ResponseEntity<List<Categories>> getAllCategories() {
        try {
            List<Categories> categories = new ArrayList<>(categoriesRepository.findAll());
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Categories> getCategoryById(@Valid int id) {
        try {
            Optional<Categories> categoryData = categoriesRepository.findById(id);

            if (categoryData.isPresent()) {
                return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tạo mới một danh mục
    @Override
    public ResponseEntity<Categories> createCategory(@Valid CategoriesDTO categoriesDTO) {
        try {
            Categories _category = categoriesRepository.save(categoriesMapper.toEntity(categoriesDTO));
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Cập nhật một danh mục
    @Override
    public ResponseEntity<Categories> updateCategory(@Valid int id, @Valid CategoriesDTO categoriesDTO) {
        try {
            Optional<Categories> categoryData = categoriesRepository.findById(id);

            if (categoryData.isPresent()) {
                Categories existingCategory = categoryData.get();

                // Map các trường từ DTO sang Entity
                Categories updatedCategory = categoriesMapper.toEntity(categoriesDTO);

                // Cập nhật
                existingCategory.setCategoryCode(updatedCategory.getCategoryCode());
                existingCategory.setName(updatedCategory.getName());
                existingCategory.setDescription(updatedCategory.getDescription());

                return new ResponseEntity<>(categoriesRepository.save(existingCategory), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Xóa một danh mục
    @Override
    @Transactional
    public void deleteCategory(@Valid int id) {
        try {
            // Xoá các sản phẩm thuộc danh mục này
            productsRepository.deleteByCategory_CategoryId(id);
            // Xoá danh mục
            categoriesRepository.deleteById(id);

        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Page<Categories>> getCategoryListPagination(int currentPage, int pageSize) {
        try {
//            Pageable firstPageWithTwoElements = (Pageable) PageRequest.of(currentPage, 10);
            Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("updatedAt").ascending());
            Page<Categories> categoriesList = categoriesRepository.findAll(pageable);
            if (categoriesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(categoriesList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
