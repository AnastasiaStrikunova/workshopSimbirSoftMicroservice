package org.example.service;


import org.example.dto.PaymentRequestDto;
import org.example.dto.PaymentResponseDto;

import java.util.List;

public interface PaymentService {
    List<PaymentResponseDto> findAll();
    PaymentResponseDto findById(Long id);
    PaymentResponseDto add(PaymentRequestDto paymentRequestDto);
    PaymentResponseDto change(Long id, PaymentRequestDto paymentRequestDto);
    void delete(Long id);

    Boolean isPaid(Long idProject);
}
