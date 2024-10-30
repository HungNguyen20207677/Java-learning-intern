package com.sapo.edu.dto;

import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

public class ProductsDTO {
    @Size(min = 1, max = 255)
    private String productCode;
    @NonNull
    private int categoryId;
    @NonNull
    private int warehouseId;
    @NonNull
    private float price;
    @Size(min = 1, max = 255)
    private String name;
    @Size(min = 1, max = 255)
    private String productDescription;
    @Size(min = 1, max = 255)
    private String imageUrl;
    @NonNull
    private int quantityInStock;
    @NonNull
    private int quantitySold;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
}
