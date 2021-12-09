package org.example.mapper;

import org.example.dto.PaymentRequestDto;
import org.example.dto.PaymentResponseDto;
import org.example.entity.CustomerEntity;
import org.example.entity.PaymentEntity;
import org.mapstruct.*;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentMapper {
    @AfterMapping
    default void setEntity(@MappingTarget PaymentEntity paymentEntity, PaymentRequestDto paymentRequestDto){
        if (paymentRequestDto.getPersonalAccount() != null) paymentEntity.setCustomerEntity(new CustomerEntity(paymentRequestDto.getPersonalAccount()));
    }
    PaymentEntity PaymentRequestDtoToPaymentEntity(PaymentRequestDto paymentRequestDto);

    @AfterMapping
    default void setId(@MappingTarget PaymentResponseDto paymentResponseDto, PaymentEntity paymentEntity){
        if (paymentEntity.getCustomerEntity() != null) paymentResponseDto.setPersonalAccount(paymentEntity.getCustomerEntity().getPersonalAccount());
    }
    PaymentResponseDto PaymentEntityToPaymentResponseDto(PaymentEntity paymentEntity);
}
