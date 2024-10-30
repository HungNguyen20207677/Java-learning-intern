package com.sapo.edu.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PrinterFile implements Printer {

    private String filePath = "./message-log.txt";

    @Override
    public void printCustomer(Customer customer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println("CustomerId: " + customer.getAcctNo() + ", balance: " + customer.getBalance());
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ phù hợp
        }
    }

    @Override
    public void printMessage(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ phù hợp
        }
    }

    public void clearFile() {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.print(""); // Xóa nội dung hiện có của file
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ phù hợp
        }
    }
}


