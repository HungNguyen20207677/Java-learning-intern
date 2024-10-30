package com.sapo.edu.ex5.jdbc.service;

import com.sapo.edu.ex5.model.Products;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public interface ProductsService {

    void getAllProducts();

    void createProduct();

    void updateProduct();

    void deleteProduct();

    void filterProduct();

    void countProduct();

    void findTopSelling();

    void searchProductPagination();
}
