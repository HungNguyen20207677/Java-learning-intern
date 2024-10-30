package com.sapo.edu.ex8;

import com.sapo.edu.ex8.ConnectDB;
import com.sapo.edu.ex8.model.Accounts;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class SqlInjection {
    public void getAccount(String accountPassword) {
        //Tạo kết nối với csdl
        ConnectDB connectDB = new ConnectDB();
        Connection conn = connectDB.connect();

        // Câu truy vấn lấy dữ liệu của các bảng
        String accountsQuery = "SELECT * FROM accounts WHERE password = \"" + accountPassword + "\"";


        // Tạo mảng tương ứng
        ArrayList<Accounts> accountsList = new ArrayList<>();

        // Lấy dữ liệu của các bảng
        try {

            // Tạo các interface statement
            Statement stm = conn.createStatement();

            // Tạo các resultset chứa các bản ghi sau mỗi câu truy vấn
            ResultSet accountResult = stm.executeQuery(accountsQuery);

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
