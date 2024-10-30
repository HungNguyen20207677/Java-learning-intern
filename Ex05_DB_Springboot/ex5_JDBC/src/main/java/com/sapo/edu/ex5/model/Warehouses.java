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
public class Warehouses {
    private int warehouseId;
    private String warehouseCode;
    private String name;
    private String location;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return warehouseId + " - " + warehouseCode + " - " + name + " - " + location + " - " + createdAt + " - " + updatedAt;
    }
}
