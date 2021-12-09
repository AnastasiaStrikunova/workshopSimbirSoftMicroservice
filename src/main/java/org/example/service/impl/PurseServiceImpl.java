package org.example.service.impl;

import org.example.dto.PurseRequestDto;
import org.example.dto.PurseResponseDto;
import org.example.entity.PurseEntity;
import org.example.exception.NotFountException;
import org.example.mapper.PurseMapper;
import org.example.repository.PurseRepository;
import org.example.service.PurseService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurseServiceImpl implements PurseService {
    private final PurseRepository purseRepository;

    private final PurseMapper purseMapper = Mappers.getMapper(PurseMapper.class);

    public PurseServiceImpl(PurseRepository purseRepository) {
        this.purseRepository = purseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurseResponseDto> findAll() {
        List<PurseEntity> purseEntityList = new ArrayList<>(purseRepository.findAll());
        List<PurseResponseDto> purseResponseDtoList = new ArrayList<>();
        purseEntityList.forEach(purseEntity -> purseResponseDtoList.add(purseMapper.PurseEntityToPurseResponseDto(purseEntity)));
        return purseResponseDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public PurseResponseDto findById(Long id) {
        return purseMapper.PurseEntityToPurseResponseDto(
                purseRepository.findById(id).orElseThrow(
                        () -> new NotFountException(
                                String.format("Could not find purse with id = %d", id)
                        )
                )
        );
    }

    @Override
    @Transactional
    public PurseResponseDto add(PurseRequestDto purseRequestDto) {
        PurseEntity purseEntity = purseMapper.PurseRequestDtoToPurseEntity(purseRequestDto);
        purseRepository.save(purseEntity);
        return purseMapper.PurseEntityToPurseResponseDto(purseEntity);
    }

    @Override
    @Transactional
    public PurseResponseDto change(Long id, PurseRequestDto purseRequestDto) {
        PurseEntity purseEntity = purseRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find purse with id = %d", id)
                )
        );
        if (purseRequestDto.getBalance() != null) purseEntity.setBalance(purseRequestDto.getBalance());
        return purseMapper.PurseEntityToPurseResponseDto(purseEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PurseEntity purseEntity = purseRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find purse with id = %d", id)
                )
        );
        purseRepository.delete(purseEntity);
    }
}
