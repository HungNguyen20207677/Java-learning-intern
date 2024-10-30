package com.sapo.edu.service.implement;

import com.sapo.edu.dto.ProductsDTO;
import com.sapo.edu.mapper.ProductsMapper;
import com.sapo.edu.model.Categories;
import com.sapo.edu.model.Products;
import com.sapo.edu.model.Warehouses;
import com.sapo.edu.repository.ProductsRepository;
import com.sapo.edu.service.ProductsService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private EntityManager entityManager;


    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    // Lấy tất cả sản phẩm
    @Override
    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            List<Products> products = new ArrayList<>(productsRepository.findAll());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Lấy thông tin sản phẩm theo ID
    @Override
    public ResponseEntity<Products> getProductById(@Valid int productId) {
        try {
            Optional<Products> productData = productsRepository.findById(productId);

            if (productData.isPresent()) {
                return new ResponseEntity<>(productData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tạo mới sản phẩm
    @Override
    public ResponseEntity<Products> createProduct(@Valid ProductsDTO productDTO) {
        try {
            // Mapping
            Products product = productsMapper.toEntity(productDTO);

            // Tham chiếu đến category từ ID
            Categories category = entityManager.find(Categories.class, productDTO.getCategoryId());
            product.setCategory(category);

            // Tham chiếu đến warehouse từ ID
            Warehouses warehouse = entityManager.find(Warehouses.class, productDTO.getWarehouseId());
            product.setWarehouse(warehouse);

            Products _product = productsRepository.save(product);
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cập nhật thông tin sản phẩm
    @Override
    public ResponseEntity<Products> updateProduct(@Valid int productId, @Valid ProductsDTO productDTO) {
        try {
            Optional<Products> productData = productsRepository.findById(productId);

            // Mapping
            Products product = productsMapper.toEntity(productDTO);

            // Tham chiếu đến category từ ID
            Categories category = entityManager.find(Categories.class, productDTO.getCategoryId());
            product.setCategory(category);

            // Tham chiếu đến warehouse từ ID
            Warehouses warehouse = entityManager.find(Warehouses.class, productDTO.getWarehouseId());
            product.setWarehouse(warehouse);

            if (productData.isPresent()) {
                Products _product = productData.get();
                _product.setProductCode(product.getProductCode());
                _product.setCategory(product.getCategory());
                _product.setWarehouse(product.getWarehouse());
                _product.setPrice(product.getPrice());
                _product.setName(product.getName());
                _product.setProductDescription(product.getProductDescription());
                _product.setImageUrl(product.getImageUrl());
                _product.setQuantityInStock(product.getQuantityInStock());
                _product.setQuantitySold(product.getQuantitySold());

                return new ResponseEntity<>(productsRepository.save(_product), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Xóa sản phẩm
    @Override
    public ResponseEntity<HttpStatus> deleteProduct(@Valid int productId) {
        try {
            productsRepository.deleteById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Lọc danh sách theo tên sản phẩm, có phân trang
    @Override
    public ResponseEntity<List<Products>> getProductListPagination(@Valid String name, @Valid int currentPage) {
        try {
//            Pageable firstPageWithTwoElements = (Pageable) PageRequest.of(currentPage, 10);
            Pageable pageable = PageRequest.of(currentPage, 10, Sort.by("name").ascending());
            List<Products> productsList = productsRepository.findAllByName(name, pageable);
            if (productsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(productsList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Lọc các sản phẩm có chứa từ %keyword% và thuộc loại danh mục có mã là %categoryCode%
    @Override
    public ResponseEntity<List<Products>> filterProducts(@Valid String keyword, @Valid String categoryCode) {
        try {
            List<Products> filteredProducts = productsRepository.findFilteredProducts(keyword, categoryCode);
            return ResponseEntity.ok().body(filteredProducts);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Đếm số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
    @Override
    public ResponseEntity<List<Object[]>> getProductCountPerCategory() {
        try {
            List<Object[]> counts = productsRepository.getProductCountPerCategory();
            return ResponseEntity.ok().body(counts);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Lấy 10 sản phẩm có số lượng bán nhiều nhất
    @Override
    public ResponseEntity<List<Products>> getTop10BestSellingProducts() {
        try {
            List<Products> topProducts = productsRepository.findTop10ByOrderByQuantitySoldDesc();
            return ResponseEntity.ok().body(topProducts);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
