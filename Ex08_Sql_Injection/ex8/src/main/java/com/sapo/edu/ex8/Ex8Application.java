package com.sapo.edu.ex8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
public class Ex8Application {

	private static SqlInjection sqlInjection = new SqlInjection();

	private static AntiSqlInjection antiSqlInjection = new AntiSqlInjection();

    public static void main(String[] args) {

		// Yêu cầu nhập mật khẩu
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter account password:");
		String accountPassword = scanner.nextLine();

		// Trường hợp bị SQL Injection
		System.out.println("SQL Injection:");


		// SQL Injection dựa trên 1=1 luôn đúng => Type in: " or 1=1 or "
		// SQL Injection dựa trên ""="" luôn đúng => Type in: " or ""="
		// Hậu quả: Làm lộ thông tin của các tài khoản khác trong csdl
		sqlInjection.getAccount(accountPassword);

		System.out.println("\n---------------------------------------------------------\n");

		// Cách phòng chống SQL Injection
		System.out.println("Anti-SQL Injection:");
		// Cách phòng chống: Sử dụng các truy vấn có tham số (PreparedStatement)
		// thay vì nối trực tiếp input của người dùng vào chuỗi truy vấn SQL
		antiSqlInjection.getAccount(accountPassword);
	}
}
