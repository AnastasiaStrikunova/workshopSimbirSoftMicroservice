package org.example.service;

import org.example.dto.CustomerRequestDto;
import org.example.dto.CustomerResponseDto;
import org.example.dto.HistoryResponseDto;
import org.example.dto.PurseResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerResponseDto> findAll();
    CustomerResponseDto findById(UUID id);
    CustomerResponseDto add(CustomerRequestDto customerRequestDto);
    CustomerResponseDto change(UUID id, CustomerRequestDto customerRequestDto);
    void delete(UUID id);

    List<HistoryResponseDto> seeHistory(UUID id);
    PurseResponseDto topUpBalance(UUID id, Long balance);
    PurseResponseDto payProject(Long idPayment);
}
