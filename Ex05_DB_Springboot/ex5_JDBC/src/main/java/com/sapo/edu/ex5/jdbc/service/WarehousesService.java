package com.sapo.edu.ex5.jdbc.service;

import org.springframework.stereotype.Service;

@Service
public interface WarehousesService {


    void getAllWarehouses();

    void createWarehouse();

    void updateWarehouse();

    void deleteWarehouse();
}
