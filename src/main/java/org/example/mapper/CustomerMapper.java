package org.example.mapper;

import org.example.dto.CustomerRequestDto;
import org.example.dto.CustomerResponseDto;
import org.example.entity.CustomerEntity;
import org.example.entity.PurseEntity;
import org.mapstruct.*;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerMapper {
    @AfterMapping
    default void setEntity(@MappingTarget CustomerEntity customerEntity, CustomerRequestDto customerRequestDto){
        if (customerRequestDto.getIdPurse() != null) customerEntity.setPurseEntity(new PurseEntity(customerRequestDto.getIdPurse()));
    }
    CustomerEntity CustomerRequestDtoToCustomerEntity(CustomerRequestDto customerRequestDto);

    @AfterMapping
    default void setId(@MappingTarget CustomerResponseDto customerResponseDto, CustomerEntity customerEntity){
        if (customerEntity.getPurseEntity() != null) customerResponseDto.setIdPurse(customerEntity.getPurseEntity().getIdPurse());
    }
    CustomerResponseDto CustomerEntityToCustomerResponseDto(CustomerEntity customerEntity);
}
