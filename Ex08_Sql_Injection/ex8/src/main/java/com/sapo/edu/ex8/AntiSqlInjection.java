package com.sapo.edu.ex8;

import com.sapo.edu.ex8.model.Accounts;

import java.sql.*;
import java.util.ArrayList;

public class AntiSqlInjection {
    public void getAccount(String accountPassword) {
        //Tạo kết nối với csdl
        ConnectDB connectDB = new ConnectDB();
        Connection conn = connectDB.connect();

        // Câu truy vấn lấy dữ liệu của các bảng
        String accountsQuery = "SELECT * FROM accounts WHERE password = ?";

        // Tạo mảng tương ứng
        ArrayList<Accounts> accountsList = new ArrayList<>();

        // Lấy dữ liệu của các bảng
        try {
            // Tạo prepared statement với tham số là accountPassword
            PreparedStatement stm = conn.prepareStatement(accountsQuery);
            stm.setString(1, accountPassword); // Set giá trị cho tham số

            // Tạo resultset chứa các bản ghi sau mỗi câu truy vấn
            ResultSet accountResult = stm.executeQuery();

            // Lấy thông tin của tài khoản có password được nhập
            while (accountResult.next()) {
                int accountId = accountResult.getInt("account_id");
                String username = accountResult.getString("username");
                String password = accountResult.getString("password");

                accountsList.add(new Accounts(accountId, username, password));
            }

            // Đóng kết nối
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // In ra console
        if (accountsList.isEmpty()) {
            System.out.println("Account doesn't exist");
        } else {
            System.out.println("Your account's information:");
            accountsList.forEach(System.out::println);
        }
    }
}
