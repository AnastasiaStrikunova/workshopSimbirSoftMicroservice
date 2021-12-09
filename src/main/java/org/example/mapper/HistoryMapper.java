package org.example.mapper;

import org.example.dto.HistoryRequestDto;
import org.example.dto.HistoryResponseDto;
import org.example.entity.CustomerEntity;
import org.example.entity.HistoryEntity;
import org.mapstruct.*;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface HistoryMapper {
    @AfterMapping
    default void setEntity(@MappingTarget HistoryEntity historyEntity, HistoryRequestDto historyRequestDto){
        if (historyRequestDto.getPersonalAccount() != null) historyEntity.setCustomerEntity(new CustomerEntity(historyRequestDto.getPersonalAccount()));
    }
    HistoryEntity HistoryRequestDtoToHistoryEntity(HistoryRequestDto historyRequestDto);

    @AfterMapping
    default void setId(@MappingTarget HistoryResponseDto historyResponseDto, HistoryEntity historyEntity){
        if (historyEntity.getCustomerEntity() != null) historyResponseDto.setPersonalAccount(historyEntity.getCustomerEntity().getPersonalAccount());
    }
    HistoryResponseDto HistoryEntityToHistoryResponseDto(HistoryEntity historyEntity);
}
