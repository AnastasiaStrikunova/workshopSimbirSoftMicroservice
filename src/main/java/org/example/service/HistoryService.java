package org.example.service;


import org.example.dto.HistoryRequestDto;
import org.example.dto.HistoryResponseDto;

import java.util.List;

public interface HistoryService {
    List<HistoryResponseDto> findAll();
    HistoryResponseDto findById(Long id);
    HistoryResponseDto add(HistoryRequestDto historyRequestDto);
    HistoryResponseDto change(Long id, HistoryRequestDto historyRequestDto);
    void delete(Long id);
}
