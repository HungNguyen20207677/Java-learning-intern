package com.example.sapo.edu.service.implement;

import com.example.sapo.edu.model.Warehouses;
import com.example.sapo.edu.repository.WarehousesRepository;
import com.example.sapo.edu.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Service
public class WarehousesServiceImpl implements WarehouseService {

    @Autowired
    private WarehousesRepository warehousesRepository;

    @Override
    public void getAllWarehouses() {
        // In từng nhà kho
        System.out.println("\n------------------Bảng Warehouses------------------");
        for (Warehouses w : warehousesRepository.findAll()) {
            System.out.println(w);
        }
    }

    @Override
    public void getWareHouseById() {
        try {
            System.out.println("\n-------------Lấy thông tin của 1 kho theo id-------------");

            // Yêu cầu nhập id của kho muốn lấy ra
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter warehouse id:");
            int warehouseId = scanner.nextInt();

            // Lấy ra kho
            System.out.println("Danh mục có id = "+warehouseId+" :");
            System.out.println(warehousesRepository.findById(warehouseId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createWarehouse() {
        try {
            System.out.println("\n-------------Thêm 1 kho-------------");

            // Yêu cầu nhập các thông tin của kho
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter warehouse code:");
            String warehouseCode = scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter location:");
            String location = scanner.nextLine();

            Warehouses warehouse = new Warehouses(1, warehouseCode, name, location, Date.valueOf(LocalDate.now()), null);

            warehousesRepository.save(warehouse);

            // In ra bảng
            System.out.println("Bảng Warehouses sau khi thêm 1 kho:");
            for (Warehouses w : warehousesRepository.findAll()) {
                System.out.println(w);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateWarehouse() {
        try {
            System.out.println("\n-------------Cập nhật 1 kho-------------");

            // Yêu cầu nhập các thông tin muốn cập nhật của kho
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter warehouse id:");
            int warehouseId = scanner.nextInt();

            scanner.nextLine();  // Tiêu thụ ký tự dòng mới còn lại sau khi sử dụng nextInt()

            System.out.println("Enter warehouse code:");
            String warehouseCode = scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter location:");
            String location = scanner.nextLine();

            Optional<Warehouses> warehouseData = warehousesRepository.findById(warehouseId);

            if (warehouseData.isPresent()) {
                Warehouses _warehouse = warehouseData.get();
                _warehouse.setWarehouseCode(warehouseCode);
                _warehouse.setName(name);
                _warehouse.setLocation(location);
                _warehouse.setUpdatedAt(Date.valueOf(LocalDate.now()));

                warehousesRepository.save(_warehouse);

                System.out.println("Cập nhật thành công!");

                // In ra bảng
                System.out.println("Bảng Warehouses sau khi cập nhật 1 kho:");
                for (Warehouses w : warehousesRepository.findAll()) {
                    System.out.println(w);
                }

            } else {
                System.out.println("Id không tồn tại!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteWarehouse() {
        try {
            System.out.println("\n-------------Xóa 1 kho-------------");

            // Yêu cầu nhập các id của kho muốn xóa
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter warehouse id:");
            int warehouseId = scanner.nextInt();

            // Xoá kho
            warehousesRepository.deleteById(warehouseId);

            // In ra bảng
            System.out.println("Bảng Warehouses sau khi xóa 1 kho:");
            for (Warehouses w : warehousesRepository.findAll()) {
                System.out.println(w);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
