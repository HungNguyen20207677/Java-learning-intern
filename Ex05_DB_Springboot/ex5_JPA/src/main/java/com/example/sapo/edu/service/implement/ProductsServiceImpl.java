package com.example.sapo.edu.service.implement;

import com.example.sapo.edu.model.Categories;
import com.example.sapo.edu.model.Products;
import com.example.sapo.edu.model.Warehouses;
import com.example.sapo.edu.repository.CategoriesRepository;
import com.example.sapo.edu.repository.ProductsRepository;
import com.example.sapo.edu.repository.WarehousesRepository;
import com.example.sapo.edu.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private WarehousesRepository warehousesRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;

    // In bảng Products
    @Override
    public void getAllProducts() {
        try {
            // In từng sản phẩm
            System.out.println("\n------------------Bảng Products------------------");

            for (Products p : productsRepository.findAll()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Lấy ra 1 sản phẩm theo id
    @Override
    public void getProductById() {
        try {
            System.out.println("\n-------------Lấy thông tin của 1 sản phẩm theo id-------------");

            // Yêu cầu nhập id của sản phẩm muốn lấy ra
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter product id:");
            int productId = scanner.nextInt();

            // Lấy ra sản phẩm
            System.out.println("Danh mục có id = "+productId+" :");
            System.out.println(productsRepository.findById(productId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Thêm 1 sản phẩm
    @Override
    public void createProduct() {
        try {
            System.out.println("\n-------------Thêm 1 sản phẩm-------------");

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

            scanner.nextLine();  // Tiêu thụ ký tự dòng mới còn lại sau khi sử dụng nextInt()

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

            Categories _category = categoriesRepository.findById(categoryId).get();
            Warehouses _warehouse = warehousesRepository.findById(warehouseId).get();

            Products product = new Products(1, productCode, _category, _warehouse, price, name, productDescription, imageUrl, quantityInStock, quantitySold, Date.valueOf(LocalDate.now()), null);

            productsRepository.save(product);

            // In ra bảng
            System.out.println("Bảng Products sau khi thêm 1 sản phẩm:");
            for (Products p : productsRepository.findAll()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Cập nhật 1 sản phẩm
    @Override
    public void updateProduct() {
        try {
            System.out.println("\n-------------Cập nhật 1 sản phẩm-------------");

            // Yêu cầu nhập các thông tin muốn cập nhật của kho
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter product id:");
            int productId = scanner.nextInt();

            scanner.nextLine();  // Tiêu thụ ký tự dòng mới còn lại sau khi sử dụng nextInt()

            System.out.println("Enter product code:");
            String productCode = scanner.nextLine();

            System.out.println("Enter categoryId:");
            int categoryId = scanner.nextInt();

            System.out.println("Enter warehouseId:");
            int warehouseId = scanner.nextInt();

            System.out.println("Enter price:");
            float price = scanner.nextFloat();

            scanner.nextLine();  // Tiêu thụ ký tự dòng mới còn lại sau khi sử dụng nextInt()

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

            Categories _category = categoriesRepository.findById(categoryId).get();
            Warehouses _warehouse = warehousesRepository.findById(warehouseId).get();

            Optional<Products> productData = productsRepository.findById(productId);

            if (productData.isPresent()) {
                Products _product = productData.get();

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
                    productsRepository.save(_product);
                }

                System.out.println("Cập nhật thành công!");

                // In ra bảng
                System.out.println("Bảng Products sau khi cập nhật 1 sản phẩm:");
                for (Products p : productsRepository.findAll()) {
                    System.out.println(p);
                }

            } else {
                System.out.println("Id không tồn tại!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Xóa 1 sản phẩm
    @Override
    public void deleteProduct() {
        try {
            System.out.println("\n-------------Xóa 1 sản phẩm-------------");

            // Yêu cầu nhập các id của sản phẩm muốn xóa
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter product id:");
            int productId = scanner.nextInt();

            // Xoá sản phẩm
            productsRepository.deleteById(productId);

            // In ra bảng
            System.out.println("Bảng Products sau khi xóa 1 sản phẩm:");
            for (Products p : productsRepository.findAll()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Lọc danh sách theo tên sản phẩm, có phân trang
    @Override
    public void getProductListPagination() {
        // Yêu cầu nhập các thông tin
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product name:");
        String name = scanner.nextLine();

        System.out.println("Enter current page:");
        int currentPage = scanner.nextInt();

        Pageable pageable = PageRequest.of(currentPage, 10, Sort.by("name").ascending());
        List<Products> productsList = productsRepository.findAllByName(name, pageable);

        System.out.println("Danh sách sản phẩm chứa tên " + name+ " :");
        for (Products p : productsList) {
            System.out.println(p);
        }
    }

    // Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple'
    @Override
    public void filterProducts() {
        try {
            System.out.println("\n-------------Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple'-------------");

            // Yêu cầu nhập các thông tin
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter keyword for name:");
            String keyword = scanner.nextLine();

            System.out.println("Enter keyword for categoryCode:");
            String categoryCode = scanner.nextLine();

            // Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple' (Sử dụng NamedParameterJdbcTemplate)
            List<Products> products = productsRepository.findFilteredProducts(keyword, categoryCode);

            if (!products.isEmpty()) {
                System.out.println("Danh sách các sản phẩm có tên chứa \"" + keyword + "\" và thuộc loại danh mục có mã là \"" + categoryCode+ "\" :");
                for (Products p : products) {
                    System.out.println(p);
                }
            } else {
                System.out.println("Không tồn tại sản phẩm.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Đếm số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
    @Override
    public void getProductCountPerCategory() {
        try {
            List<Object[]> counts = productsRepository.getProductCountPerCategory();
            System.out.println("Số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần:");
            for (Object o : counts) {
                System.out.println(o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Lấy 10 sản phẩm có số lượng bán nhiều nhất
    @Override
    public void getTop10BestSellingProducts() {
        try {
            System.out.println("\n-------------Lấy 10 sản phẩm có số lượng bán nhiều nhất-------------");

            // Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple' (Sử dụng NamedParameterJdbcTemplate)
            List<Products> products = productsRepository.findTop10ByOrderByQuantitySoldDesc();

            System.out.println("Top 10 sản phẩm có số lượng bán nhiều nhất:");
            for (Products p : products) {
                System.out.println(p);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
