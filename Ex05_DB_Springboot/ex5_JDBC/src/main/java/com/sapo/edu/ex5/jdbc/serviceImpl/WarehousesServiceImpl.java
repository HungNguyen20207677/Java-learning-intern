package com.sapo.edu.ex5.jdbc.serviceImpl;

import com.sapo.edu.ex5.jdbc.service.WarehousesService;
import com.sapo.edu.ex5.model.Warehouses;
import com.sapo.edu.ex5.spring.config.AppConfig;
import com.sapo.edu.ex5.spring.dao.WarehousesDAO;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

@Service
public class WarehousesServiceImpl implements WarehousesService {

    public void getAllWarehouses() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean WarehousesDAO từ context
            WarehousesDAO warehouseDAO = context.getBean(WarehousesDAO.class);

            // In ra danh sách các kho hàng
            System.out.println("Danh sách các kho hàng:");

            for (Warehouses w : warehouseDAO.getAllWarehouses()) {
                System.out.println(w);
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createWarehouse() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean WarehousesDAO từ context
            WarehousesDAO warehouseDAO = context.getBean(WarehousesDAO.class);

            // Yêu cầu nhập các thông tin của kho hàng
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter warehouse code:");
            String warehouseCode = scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter location:");
            String location = scanner.nextLine();

            // Tạo một kho hàng mới và thêm vào cơ sở dữ liệu
            System.out.println("\nTạo một kho hàng mới:");
            Warehouses warehouse = new Warehouses(new Random().nextInt(), warehouseCode, name, location, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
            System.out.println(warehouse);
            warehouseDAO.createWarehouse(warehouse);
            System.out.println("\nDanh sách các kho hàng sau khi thêm mới:");

            for (Warehouses w : warehouseDAO.getAllWarehouses()) {
                System.out.println(w);
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateWarehouse() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean WarehousesDAO từ context
            WarehousesDAO warehouseDAO = context.getBean(WarehousesDAO.class);

            // Yêu cầu nhập các thông tin muốn cập nhật của kho
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter warehouse id:");
            int warehouseId = scanner.nextInt();

            // Tiêu thụ ký tự dòng mới còn lại sau khi sử dụng nextInt()
            scanner.nextLine();

            System.out.println("Enter warehouse code:");
            String warehouseCode = scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter location:");
            String location = scanner.nextLine();


            // Cập nhật thông tin của kho hàng theo ID
            System.out.println("\nCập nhật thông tin của kho hàng theo ID");
            Warehouses _warehouse = warehouseDAO.getWarehouseById(warehouseId);

            // Kiểm tra xem người dùng đã nhập dữ liệu mới hay không
            if (!warehouseCode.isEmpty()) {
                _warehouse.setWarehouseCode(warehouseCode);
            }
            if (!name.isEmpty()) {
                _warehouse.setName(name);
            }
            if (!location.isEmpty()) {
                _warehouse.setLocation(location);
            }

            // Tiến hành cập nhật thông tin kho hàng chỉ khi có dữ liệu mới được nhập
            if (!warehouseCode.isEmpty() || !name.isEmpty() || !location.isEmpty()) {
                warehouseDAO.updateWarehouse(_warehouse);
            }

            // In ra danh sách các kho hàng sau khi cập nhật
            System.out.println("\nDanh sách các kho hàng sau khi cập nhật:");
            for (Warehouses w : warehouseDAO.getAllWarehouses()) {
                System.out.println(w);
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteWarehouse() {
        try {
            // Tạo một đối tượng ApplicationContext từ cấu hình được chỉ định
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // Lấy bean WarehousesDAO từ context
            WarehousesDAO warehouseDAO = context.getBean(WarehousesDAO.class);

            // Yêu cầu nhập các thông tin muốn cập nhật của kho
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter warehouse id:");
            int warehouseId = scanner.nextInt();

            // Xóa kho hàng theo ID
            System.out.println("\nXóa kho hàng theo ID");
            Warehouses _warehouseById = warehouseDAO.getWarehouseById(warehouseId);
            warehouseDAO.deleteWarehouse(_warehouseById);
            System.out.println("\nDanh sách các kho hàng sau khi xóa:");

            for (Warehouses w : warehouseDAO.getAllWarehouses()) {
                System.out.println(w);
            }

            // Đóng context
            context.close();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }
}
