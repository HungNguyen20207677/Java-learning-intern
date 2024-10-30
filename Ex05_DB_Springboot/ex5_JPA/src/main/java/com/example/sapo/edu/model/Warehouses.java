package com.example.sapo.edu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "warehouses")
public class Warehouses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int warehouseId;

    @Column(name = "warehouseCode")
    @Size(min = 1, max = 255)
    private String warehouseCode;

    @Column(name = "name")
    @Size(min = 1, max = 255)
    private String name;

    @Column(name = "location")
    @Size(min = 1, max = 255)
    private String location;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;
}
