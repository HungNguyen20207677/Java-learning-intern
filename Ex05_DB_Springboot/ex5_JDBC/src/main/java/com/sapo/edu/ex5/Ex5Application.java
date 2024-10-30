package com.sapo.edu.ex5;

import com.sapo.edu.ex5.jdbc.serviceImpl.CategoriesServiceImpl;
import com.sapo.edu.ex5.jdbc.serviceImpl.ProductsServiceImpl;
import com.sapo.edu.ex5.jdbc.serviceImpl.WarehousesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Ex5Application implements CommandLineRunner {

	@Autowired
	private WarehousesServiceImpl warehousesService;

	@Autowired
	private CategoriesServiceImpl categoriesService;

	@Autowired
	private ProductsServiceImpl productsService;

	public static void main(String[] args) {
		SpringApplication.run(Ex5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Warehouses CRUD
		warehousesService.createWarehouse();
//		warehousesService.deleteWarehouse();
//		warehousesService.updateWarehouse();
//		warehousesService.getAllWarehouses();

		// Categories CRUD
//		categoriesService.getAllCategories();
//		categoriesService.createCategory();
//		categoriesService.deleteCategory();
//		categoriesService.updateCategory();

		// Products CRUD
//		productsService.getAllProducts();
//		productsService.createProduct();
//		productsService.deleteProduct();
//		productsService.updateProduct();

		// Sử dụng NamedParameterJdbcTemplate
//		productsService.filterProduct();
//		productsService.findTopSelling();

		//Sử dụng SimpleJdbcTemplate
//		productsService.searchProductPagination();

	}
}
