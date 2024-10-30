package com.sapo.edu.mapper;

import com.sapo.edu.dto.ProductsDTO;
import com.sapo.edu.model.Products;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    Products toEntity(ProductsDTO productsDTO);

    ProductsDTO toDTO(Products products);

    List<ProductsDTO> toDTOList(List<Products> products);

    List<Products> toEntityList(List<ProductsDTO> productsDTOS);
}
