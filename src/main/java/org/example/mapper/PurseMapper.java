package org.example.mapper;

import org.example.dto.PurseRequestDto;
import org.example.dto.PurseResponseDto;
import org.example.entity.PurseEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PurseMapper {
    PurseEntity PurseRequestDtoToPurseEntity(PurseRequestDto purseRequestDto);
    PurseResponseDto PurseEntityToPurseResponseDto(PurseEntity purseEntity);
}
