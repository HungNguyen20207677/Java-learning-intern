package com.example.sapo.edu;

import com.example.sapo.edu.service.implement.CategoriesServiceImpl;
import com.example.sapo.edu.service.implement.ProductsServiceImpl;
import com.example.sapo.edu.service.implement.WarehousesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex5JpaApplication implements CommandLineRunner {

	@Autowired
	private WarehousesServiceImpl warehousesService;

	@Autowired
	private CategoriesServiceImpl categoriesService;

	@Autowired
	private ProductsServiceImpl productsService;


	public static void main(String[] args) {
		SpringApplication.run(Ex5JpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Categories
//		categoriesService.getAllCategories();
//		categoriesService.getCategoryById();
//		categoriesService.createCategory();
//		categoriesService.updateCategory();
//		categoriesService.deleteCategory();

		// Warehouses
//		warehousesService.getAllWarehouses();
//		warehousesService.getWareHouseById();
//		warehousesService.createWarehouse();
//		warehousesService.updateWarehouse();
//		warehousesService.deleteWarehouse();

		// Products
//		productsService.getAllProducts();
//		productsService.getProductById();
//		productsService.createProduct();
//		productsService.updateProduct();
//		productsService.deleteProduct();


	}
}
