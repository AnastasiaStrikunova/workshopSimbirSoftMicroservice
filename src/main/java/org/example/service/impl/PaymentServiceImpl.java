package org.example.service.impl;

import org.example.dto.PaymentRequestDto;
import org.example.dto.PaymentResponseDto;
import org.example.entity.CustomerEntity;
import org.example.entity.PaymentEntity;
import org.example.exception.NotFountException;
import org.example.mapper.PaymentMapper;
import org.example.repository.PaymentRepository;
import org.example.service.PaymentService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDto> findAll() {
        List<PaymentEntity> paymentEntityList = new ArrayList<>(paymentRepository.findAll());
        List<PaymentResponseDto> paymentResponseDtoList = new ArrayList<>();
        paymentEntityList.forEach(paymentEntity -> paymentResponseDtoList.add(paymentMapper.PaymentEntityToPaymentResponseDto(paymentEntity)));
        return paymentResponseDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDto findById(Long id) {
            return paymentMapper.PaymentEntityToPaymentResponseDto(
                    paymentRepository.findById(id).orElseThrow(
                            () -> new NotFountException(
                                    String.format("Could not find payment with id = %d", id)
                            )
                    )
            );
    }

    @Override
    @Transactional
    public PaymentResponseDto add(PaymentRequestDto paymentRequestDto) {
        PaymentEntity paymentEntity = paymentMapper.PaymentRequestDtoToPaymentEntity(paymentRequestDto);
        paymentRepository.save(paymentEntity);
        return paymentMapper.PaymentEntityToPaymentResponseDto(paymentEntity);
    }

    @Override
    @Transactional
    public PaymentResponseDto change(Long id, PaymentRequestDto paymentRequestDto) {
        PaymentEntity paymentEntity = paymentRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find payment with id = %d", id)
                )
        );
        if (paymentRequestDto.getIdProject() != null) paymentEntity.setIdProject(paymentRequestDto.getIdProject());
        if (paymentRequestDto.getPrice() != null) paymentEntity.setPrice(paymentRequestDto.getPrice());
        if (paymentRequestDto.getPersonalAccount() != null) paymentEntity.setCustomerEntity(new CustomerEntity(paymentRequestDto.getPersonalAccount()));
        if (paymentRequestDto.getIsPaid() != null) paymentEntity.setIsPaid(paymentRequestDto.getIsPaid());
        return paymentMapper.PaymentEntityToPaymentResponseDto(paymentEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find payment with id = %d", id)
                )
        );
        paymentRepository.delete(paymentEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isPaid(Long idProject) {
        return paymentRepository.findByIdProject(idProject).getIsPaid();
    }
}
