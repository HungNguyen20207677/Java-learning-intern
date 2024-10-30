package com.sapo.edu.demo;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class PrinterConsole implements Printer {
    @Override
    public void printCustomer(Customer customer) {
        System.out.println("CustomerId: " + customer.getAcctNo() + ", balance: " + customer.getBalance().toString());
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void clearFile() {

    }
}

