package com.sapo.edu.ex5.jdbc.serviceImpl;

import com.sapo.edu.ex5.jdbc.service.ProductsService;
import com.sapo.edu.ex5.model.Categories;
import com.sapo.edu.ex5.model.Products;
import com.sapo.edu.ex5.model.Warehouses;
import com.sapo.edu.ex5.spring.config.AppConfig;
import com.sapo.edu.ex5.spring.dao.CategoriesDAO;
import com.sapo.edu.ex5.spring.dao.ProductsDAO;
import com.sapo.edu.ex5.spring.dao.WarehousesDAO;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Override
    public void getAllProducts() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean ProductsDAO từ context
            ProductsDAO productDAO = context.getBean(ProductsDAO.class);

            // In ra danh sách các sản phẩm
            System.out.println("Danh sách các sản phẩm:");

            for (Products p : productDAO.getAllProducts()) {
                System.out.println(p);
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createProduct() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean ProductsDAO từ context
            ProductsDAO productDAO = context.getBean(ProductsDAO.class);
            CategoriesDAO categoryDAO = context.getBean(CategoriesDAO.class);
            WarehousesDAO warehouseDAO = context.getBean(WarehousesDAO.class);


            // Yêu cầu nhập các thông tin của sản phẩm
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter product code:");
            String productCode = scanner.nextLine();

            System.out.println("Enter categoryId:");
            int categoryId = scanner.nextInt();

            System.out.println("Enter warehouseId:");
            int warehouseId = scanner.nextInt();

            System.out.println("Enter price:");
            float price = scanner.nextFloat();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter productDescription:");
            String productDescription = scanner.nextLine();

            System.out.println("Enter imageUrl:");
            String imageUrl = scanner.nextLine();

            System.out.println("Enter quantityInStock:");
            int quantityInStock = scanner.nextInt();

            System.out.println("Enter quantitySold:");
            int quantitySold = scanner.nextInt();

            Categories _category = categoryDAO.getCategoryById(categoryId);
            Warehouses _warehouse = warehouseDAO.getWarehouseById(warehouseId);

            // Tạo một sản phẩm mới và thêm vào cơ sở dữ liệu
            System.out.println("\nTạo một sản phẩm mới:");
            Products product = new Products(new Random().nextInt(), productCode, _category, _warehouse, price, name, productDescription, imageUrl, quantityInStock, quantitySold, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
            System.out.println(product);
            productDAO.createProduct(product);
            System.out.println("\nDanh sách các sản phẩm sau khi thêm mới:");

            for (Products p : productDAO.getAllProducts()) {
                System.out.println(p);
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct() {
        // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean ProductsDAO từ context
        ProductsDAO productDAO = context.getBean(ProductsDAO.class);
        CategoriesDAO categoryDAO = context.getBean(CategoriesDAO.class);
        WarehousesDAO warehouseDAO = context.getBean(WarehousesDAO.class);

        // Yêu cầu nhập các thông tin muốn cập nhật của kho
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product id:");
        int productId = scanner.nextInt();
        
        System.out.println("Enter product code:");
        String productCode = scanner.nextLine();

        System.out.println("Enter categoryId:");
        int categoryId = scanner.nextInt();

        System.out.println("Enter warehouseId:");
        int warehouseId = scanner.nextInt();

        System.out.println("Enter price:");
        float price = scanner.nextFloat();

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter productDescription:");
        String productDescription = scanner.nextLine();

        System.out.println("Enter imageUrl:");
        String imageUrl = scanner.nextLine();

        System.out.println("Enter quantityInStock:");
        int quantityInStock = scanner.nextInt();

        System.out.println("Enter quantitySold:");
        int quantitySold = scanner.nextInt();


        Categories _category = categoryDAO.getCategoryById(categoryId);
        Warehouses _warehouse = warehouseDAO.getWarehouseById(warehouseId);

        // Cập nhật thông tin của sản phẩm theo ID
        System.out.println("\nCập nhật thông tin của sản phẩm theo ID");
        Products _product = productDAO.getProductById(productId);

        // Kiểm tra xem người dùng đã nhập dữ liệu mới hay không
        if (!productCode.isEmpty()) {
            _product.setProductCode(productCode);
        }

        if (categoryId > 0) {
            _product.setCategory(_category);
        }

        if (warehouseId > 0) {
            _product.setWarehouse(_warehouse);
        }

        if (price > 0) {
            _product.setPrice(price);
        }

        if (!name.isEmpty()) {
            _product.setName(name);
        }

        if (!productDescription.isEmpty()) {
            _product.setProductDescription(productDescription);
        }

        if (!imageUrl.isEmpty()) {
            _product.setImageUrl(imageUrl);
        }

        if (quantityInStock > 0) {
            _product.setQuantityInStock(quantityInStock);
        }

        if (quantitySold > 0) {
            _product.setQuantitySold(quantitySold);
        }
        
        // Tiến hành cập nhật thông tin sản phẩm chỉ khi có dữ liệu mới được nhập
        if (!productCode.isEmpty() || categoryId > 0 || warehouseId > 0 || price > 0 || !name.isEmpty() || !productDescription.isEmpty() || !imageUrl.isEmpty() || quantityInStock > 0 || quantitySold > 0) {
            productDAO.updateProduct(_product);
        }

        // In ra danh sách các sản phẩm sau khi cập nhật
        System.out.println("\nDanh sách các sản phẩm sau khi cập nhật:");
        for (Products p : productDAO.getAllProducts()) {
            System.out.println(p);
        }

        // Đóng context
        context.close();
    }

    @Override
    public void deleteProduct() {
        // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean ProductsDAO từ context
        ProductsDAO productDAO = context.getBean(ProductsDAO.class);

        // Yêu cầu nhập các thông tin muốn cập nhật của kho
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product id:");
        int productId = scanner.nextInt();

        // Xóa sản phẩm theo ID
        System.out.println("\nXóa sản phẩm theo ID");
        Products _productById = productDAO.getProductById(productId);
        productDAO.deleteProduct(_productById);
        System.out.println("\nDanh sách các sản phẩm sau khi xóa:");

        for (Products p : productDAO.getAllProducts()) {
            System.out.println(p);
        }

        // Đóng context
        context.close();
    }

    @Override
    public void filterProduct() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean ProductsDAO từ context
            ProductsDAO productDAO = context.getBean(ProductsDAO.class);

            // Yêu cầu nhập các thông tin
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter keyword for name:");
            String name = scanner.nextLine();

            System.out.println("Enter keyword for categoryCode:");
            String categoryCode = scanner.nextLine();

            // Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple' (Sử dụng NamedParameterJdbcTemplate)
            List<Products> products = productDAO.filterProduct(name, categoryCode);

            if (!products.isEmpty()) {
                System.out.println("Danh sách các sản phẩm có tên chứa \"" + name + "\" và thuộc loại danh mục có mã là \"" + categoryCode+ "\" :");
                for (Products p : products) {
                    System.out.println(p);
                }
            } else {
                System.out.println("Không tồn tại sản phẩm.");
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void countProduct() {
        // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean ProductsDAO từ context
        ProductsDAO productDAO = context.getBean(ProductsDAO.class);

        List<Object[]> products = productDAO.getProductCountPerCategory();

        System.out.println("Top " + limitValue + " sản phẩm có số lượng bán nhiều nhất:");
        for (Object p : products) {
            System.out.println(p);
        }

        // Đóng context
        context.close();
    }

    @Override
    public void findTopSelling() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean ProductsDAO từ context
            ProductsDAO productDAO = context.getBean(ProductsDAO.class);

            // Yêu cầu nhập các thông tin
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter số top sản phẩm có số lượng bán cao nhất muốn hiện:");
            int limitValue = scanner.nextInt();

            // Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple' (Sử dụng NamedParameterJdbcTemplate)
            List<Products> products = productDAO.findTopSelling(limitValue);

            System.out.println("Top " + limitValue + " sản phẩm có số lượng bán nhiều nhất:");
            for (Products p : products) {
                System.out.println(p);
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void searchProductPagination() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean ProductsDAO từ context
            ProductsDAO productDAO = context.getBean(ProductsDAO.class);

            // Yêu cầu nhập các thông tin
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter productCode:");
            String productCode = scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter warehouseCode:");
            String warehouseCode = scanner.nextLine();

            System.out.println("Enter categoryCode:");
            String categoryCode = scanner.nextLine();

//            System.out.println("Enter the date (format: yyyy-MM-dd):");
//            String createdAfterString = scanner.nextLine();
//            Date createdAfter = null;

            Date startDate = null;
            Date endDate = null;

            System.out.println("Pagination? (True/False):");
            boolean isPaginationByNumber = scanner.nextBoolean();

            System.out.println("Enter currentPage:");
            int currentPage = scanner.nextInt();

            System.out.println("Enter offset if Pagination is false:");
            int offset = scanner.nextInt();


            // Thực thi Stored Procedure
            List<Products> products = productDAO.searchProductPagination(productCode, name, warehouseCode, categoryCode, startDate, endDate,  isPaginationByNumber, currentPage, offset);

            System.out.println("Danh sách sản phẩm thỏa mãn điều kiện lọc:");
            for (Products p : products) {
                System.out.println(p);
            }

            // Đóng context
            context.close();

        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

}
