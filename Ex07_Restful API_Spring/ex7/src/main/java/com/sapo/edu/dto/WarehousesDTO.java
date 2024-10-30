package com.sapo.edu.dto;

import jakarta.validation.constraints.Size;

public class WarehousesDTO {
    @Size(min = 1, max = 255)
    private String warehouseCode;
    @Size(min = 1, max = 255)
    private String name;
    @Size(min = 1, max = 255)
    private String location;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
