package com.sapo.edu.mapper;

import com.sapo.edu.dto.CategoriesDTO;
import com.sapo.edu.model.Categories;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {
    Categories toEntity(CategoriesDTO categoriesDTO);
    CategoriesDTO toDTO(Categories categories);
    List<CategoriesDTO> toDTOList(List<Categories> categories);
    List<Categories> toEntityList(List<CategoriesDTO> categoriesDTOS);
}
