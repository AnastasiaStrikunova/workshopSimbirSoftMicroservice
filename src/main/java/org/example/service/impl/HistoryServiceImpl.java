package org.example.service.impl;


import org.example.dto.HistoryRequestDto;
import org.example.dto.HistoryResponseDto;
import org.example.entity.CustomerEntity;
import org.example.entity.HistoryEntity;
import org.example.exception.NotFountException;
import org.example.mapper.HistoryMapper;
import org.example.repository.HistoryRepository;
import org.example.service.HistoryService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    private final HistoryMapper historyMapper = Mappers.getMapper(HistoryMapper.class);

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryResponseDto> findAll() {
        List<HistoryEntity> historyEntityList = new ArrayList<>(historyRepository.findAll());
        List<HistoryResponseDto> historyResponseDtoList = new ArrayList<>();
        historyEntityList.forEach(historyEntity -> historyResponseDtoList.add(historyMapper.HistoryEntityToHistoryResponseDto(historyEntity)));
        return historyResponseDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public HistoryResponseDto findById(Long id) {
        return historyMapper.HistoryEntityToHistoryResponseDto(
                historyRepository.findById(id).orElseThrow(
                        () -> new NotFountException(
                                String.format("Could not find history with id = %d", id)
                        )
                )
        );
    }

    @Override
    @Transactional
    public HistoryResponseDto add(HistoryRequestDto historyRequestDto) {
        HistoryEntity historyEntity = historyMapper.HistoryRequestDtoToHistoryEntity(historyRequestDto);
        historyRepository.save(historyEntity);
        return historyMapper.HistoryEntityToHistoryResponseDto(historyEntity);

    }

    @Override
    @Transactional
    public HistoryResponseDto change(Long id, HistoryRequestDto historyRequestDto) {
        HistoryEntity historyEntity = historyRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find history with id = %d", id)
                )
        );
        if (historyRequestDto.getPersonalAccount() != null) historyEntity.setCustomerEntity(new CustomerEntity(historyRequestDto.getPersonalAccount()));
        if (historyRequestDto.getAmount() != null) historyEntity.setAmount(historyRequestDto.getAmount());
        if (historyRequestDto.getTypeOfTransaction() != null) historyEntity.setTypeOfTransaction(historyRequestDto.getTypeOfTransaction());
        if (historyRequestDto.getTime() != null) historyEntity.setTime(historyRequestDto.getTime());
        return historyMapper.HistoryEntityToHistoryResponseDto(historyEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        HistoryEntity historyEntity = historyRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find history with id = %d", id)
                )
        );
        historyRepository.delete(historyEntity);
    }
}
