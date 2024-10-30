package com.sapo.edu.ex5.jdbc.serviceImpl;

import com.sapo.edu.ex5.jdbc.service.CategoriesService;
import com.sapo.edu.ex5.model.Categories;
import com.sapo.edu.ex5.spring.config.AppConfig;
import com.sapo.edu.ex5.spring.dao.CategoriesDAO;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Override
    public void getAllCategories() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean CategoriesDAO từ context
            CategoriesDAO categoryDAO = context.getBean(CategoriesDAO.class);

            // In ra danh sách các danh mục
            System.out.println("Danh sách các danh mục:");

            for (Categories c : categoryDAO.getAllCategories()) {
                System.out.println(c);
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createCategory() {
        // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean CategoriesDAO từ context
        CategoriesDAO categoryDAO = context.getBean(CategoriesDAO.class);

        // Yêu cầu nhập các thông tin của danh mục
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter category code:");
        String categoryCode = scanner.nextLine();

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        // Tạo một danh mục mới và thêm vào cơ sở dữ liệu
        System.out.println("\nTạo một danh mục mới:");
        Categories category = new Categories(new Random().nextInt(), categoryCode, name, description, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
        System.out.println(category);
        categoryDAO.createCategory(category);
        System.out.println("\nDanh sách các danh mục sau khi thêm mới:");

        for (Categories c : categoryDAO.getAllCategories()) {
            System.out.println(c);
        }

        // Đóng context
        context.close();
    }

    @Override
    public void updateCategory() {
        // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean CategoriesDAO từ context
        CategoriesDAO categoryDAO = context.getBean(CategoriesDAO.class);

        // Yêu cầu nhập các thông tin muốn cập nhật của kho
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter category id:");
        int categoryId = scanner.nextInt();

        // Tiêu thụ ký tự dòng mới còn lại sau khi sử dụng nextInt()
        scanner.nextLine();

        System.out.println("Enter category code:");
        String categoryCode = scanner.nextLine();

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter description:");
        String description = scanner.nextLine();


        // Cập nhật thông tin của danh mục theo ID
        System.out.println("\nCập nhật thông tin của danh mục theo ID");
        Categories _category = categoryDAO.getCategoryById(categoryId);

        // Kiểm tra xem người dùng đã nhập dữ liệu mới hay không
        if (!categoryCode.isEmpty()) {
            _category.setCategoryCode(categoryCode);
        }
        if (!name.isEmpty()) {
            _category.setName(name);
        }
        if (!description.isEmpty()) {
            _category.setDescription(description);
        }

        // Tiến hành cập nhật thông tin danh mục chỉ khi có dữ liệu mới được nhập
        if (!categoryCode.isEmpty() || !name.isEmpty() || !description.isEmpty()) {
            categoryDAO.updateCategory(_category);
        }

        // In ra danh sách các danh mục sau khi cập nhật
        System.out.println("\nDanh sách các danh mục sau khi cập nhật:");
        for (Categories c : categoryDAO.getAllCategories()) {
            System.out.println(c);
        }

        // Đóng context
        context.close();
    }

    @Override
    public void deleteCategory() {
        // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean CategoriesDAO từ context
        CategoriesDAO categoryDAO = context.getBean(CategoriesDAO.class);

        // Yêu cầu nhập các thông tin muốn cập nhật của kho
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter category id:");
        int categoryId = scanner.nextInt();

        // Xóa danh mục theo ID
        System.out.println("\nXóa danh mục theo ID");
        Categories _categoryById = categoryDAO.getCategoryById(categoryId);
        categoryDAO.deleteCategory(_categoryById);
        System.out.println("\nDanh sách các danh mục sau khi xóa:");

        for (Categories c : categoryDAO.getAllCategories()) {
            System.out.println(c);
        }

        // Đóng context
        context.close();
    }
}
