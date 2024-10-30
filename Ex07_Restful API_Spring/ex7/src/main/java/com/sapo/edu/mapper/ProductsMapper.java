package com.sapo.edu.mapper;

import com.sapo.edu.dto.ProductsDTO;
import com.sapo.edu.model.Categories;
import com.sapo.edu.model.Products;
import com.sapo.edu.repository.CategoriesRepository;
import com.sapo.edu.repository.WarehousesRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    Products toEntity(ProductsDTO productsDTO);

    ProductsDTO toDTO(Products products);

    List<ProductsDTO> toDTOList(List<Products> products);

    List<Products> toEntityList(List<ProductsDTO> productsDTOS);
}
