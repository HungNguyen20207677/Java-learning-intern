package com.sapo.edu.ex5.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private int productId;
    private String productCode;
    private Categories category;
    private Warehouses warehouse;
    private float price;
    private String name;
    private String productDescription;
    private String imageUrl;
    private int quantityInStock;
    private int quantitySold;
    private Date createdAt;
    private Date updatedAt;

    public String toString() {
        return "Product ID: " + productId +
                ", Product Code: " + productCode +
                ", Category ID: " + category.getCategoryId() +
                ", Warehouse ID: " + warehouse.getWarehouseId() +
                ", Price: " + price +
                ", Name: " + name +
                ", Description: " + productDescription +
                ", Image URL: " + imageUrl +
                ", Quantity in Stock: " + quantityInStock +
                ", Quantity Sold: " + quantitySold +
                ", Created At: " + createdAt +
                ", Updated At: " + updatedAt;
    }
}
