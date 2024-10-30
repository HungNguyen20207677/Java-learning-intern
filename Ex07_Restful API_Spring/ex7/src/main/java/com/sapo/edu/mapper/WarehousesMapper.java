package com.sapo.edu.mapper;

import com.sapo.edu.dto.WarehousesDTO;
import com.sapo.edu.model.Warehouses;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehousesMapper {
    Warehouses toEntity(WarehousesDTO warehousesDTO);
    WarehousesDTO toDTO(Warehouses warehouses);
    List<WarehousesDTO> toDTOList(List<Warehouses> warehouses);
    List<Warehouses> toEntityList(List<WarehousesDTO> warehousesDTOS);
}
