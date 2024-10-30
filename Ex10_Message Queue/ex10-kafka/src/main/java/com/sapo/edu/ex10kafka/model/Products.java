package com.sapo.edu.ex10kafka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @Column(name = "productCode")
    private String productCode;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "warehouseId", referencedColumnName = "warehouseId")
    private Warehouses warehouse;

    @Column(name = "price")
    private float price;

    @Column(name = "name")
    private String name;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "quantityInStock")
    private int quantityInStock;

    @Column(name = "quantitySold")
    private int quantitySold;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;
}