package com.sapo.edu.ex10rabbitmq.dto;

import jakarta.persistence.*;
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
    private String warehouseCode;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;
}
