package com.sapo.edu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Scanner;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private BidvAtm bidvAtm;

    @Autowired
    private Printer printerFile;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Customer customer = new Customer("ABC", "1234", new BigDecimal(5000000));

        clearLogFile();

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account number:");
        String accountNo = scanner.nextLine();

        System.out.println("Enter PIN:");
        String pin = scanner.nextLine();

        System.out.println("Enter initial balance:");
        BigDecimal initialBalance = scanner.nextBigDecimal();

        Customer customer = new Customer(accountNo, pin, initialBalance);


        // Demo function
        // Atm bidvAtm = new BidvAtm();
        bidvAtm.printCurrentMoney();
        bidvAtm.displayCustomerInfo(customer);
        bidvAtm.withDraw(customer, new BigDecimal(200000));
        bidvAtm.printCurrentMoney();
        bidvAtm.withDraw(customer, new BigDecimal(10000000));
        bidvAtm.printCurrentMoney();
        bidvAtm.deposit(customer,new BigDecimal(1000000));
        bidvAtm.printCurrentMoney();
    }

    private void clearLogFile() {
        printerFile.clearFile();
    }
}
