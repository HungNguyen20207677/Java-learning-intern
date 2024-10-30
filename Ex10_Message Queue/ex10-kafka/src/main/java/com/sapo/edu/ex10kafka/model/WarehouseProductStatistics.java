package com.sapo.edu.ex10kafka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "warehouse_product_statistics")
public class WarehouseProductStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "warehouseId")
    private int warehouseId;

    @Column(name = "warehouseCode")
    private String warehouseCode;

    @Column(name = "productsCount")
    private int productsCount;

    @Column(name = "statisticizedAt")
    private Date statisticizedAt;

}
