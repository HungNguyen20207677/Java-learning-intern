package com.example.sapo.edu.service.implement;

import com.example.sapo.edu.model.Categories;
import com.example.sapo.edu.repository.CategoriesRepository;
import com.example.sapo.edu.repository.ProductsRepository;
import com.example.sapo.edu.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, ProductsRepository productsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public void getAllCategories() {
        try {
            // In từng danh mục
            System.out.println("\n------------------Bảng Categories------------------");
            for (Categories c : categoriesRepository.findAll()) {
                System.out.println(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getCategoryById() {
        try {
            System.out.println("\n-------------Lấy ra thông tin của 1 danh mục theo id-------------");

            // Yêu cầu nhập id của danh mục muốn lấy ra
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter category id:");
            int categoryId = scanner.nextInt();

            // Lấy ra danh mục
            System.out.println("Danh mục có id = "+categoryId+" :");
            System.out.println(categoriesRepository.findById(categoryId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createCategory() {
        try {
            System.out.println("\n-------------Thêm 1 danh mục-------------");

            // Yêu cầu nhập các thông tin của danh mục
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter category code:");
            String categoryCode = scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter description:");
            String description = scanner.nextLine();

            Categories category = new Categories(1, categoryCode, name, description, Date.valueOf(LocalDate.now()), null);

            categoriesRepository.save(category);

            // In ra bảng
            System.out.println("Bảng Categories sau khi thêm 1 danh mục:");
            for (Categories c : categoriesRepository.findAll()) {
                System.out.println(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCategory() {
        try {
            System.out.println("\n-------------Cập nhật 1 danh mục-------------");

            // Yêu cầu nhập các thông tin muốn cập nhật của danh mục
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter category id:");
            int categoryId = scanner.nextInt();

            scanner.nextLine();  // Tiêu thụ ký tự dòng mới còn lại sau khi sử dụng nextInt()

            System.out.println("Enter category code:");
            String categoryCode = scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter description:");
            String description = scanner.nextLine();

            Optional<Categories> categoryData = categoriesRepository.findById(categoryId);

            if (categoryData.isPresent()) {
                Categories _category = categoryData.get();
                _category.setCategoryCode(categoryCode);
                _category.setName(name);
                _category.setDescription(description);
                _category.setUpdatedAt(Date.valueOf(LocalDate.now()));

                categoriesRepository.save(_category);

                System.out.println("Cập nhật thành công!");

                // In ra bảng
                System.out.println("Bảng Categories sau khi cập nhật 1 danh mục:");
                for (Categories c : categoriesRepository.findAll()) {
                    System.out.println(c);
                }

            } else {
                System.out.println("Id không tồn tại!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void deleteCategory() {
        try {
            System.out.println("\n-------------Xóa 1 danh mục (đồng thời xóa sản phẩm thuộc danh mục)-------------");

            // Yêu cầu nhập các id của danh mục muốn xóa
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter category id:");
            int categoryId = scanner.nextInt();

            // Xoá các sản phẩm thuộc danh mục này
            productsRepository.deleteByCategory_CategoryId(categoryId);

            // Xoá danh mục
            categoriesRepository.deleteById(categoryId);

            // In ra bảng
            System.out.println("Bảng Categories sau khi xóa 1 danh mục:");
            for (Categories c : categoriesRepository.findAll()) {
                System.out.println(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
