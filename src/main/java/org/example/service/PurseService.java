package org.example.service;

import org.example.dto.PurseRequestDto;
import org.example.dto.PurseResponseDto;

import java.util.List;

public interface PurseService {
    List<PurseResponseDto> findAll();
    PurseResponseDto findById(Long id);
    PurseResponseDto add(PurseRequestDto purseRequestDto);
    PurseResponseDto change(Long id, PurseRequestDto purseRequestDto);
    void delete(Long id);
}
