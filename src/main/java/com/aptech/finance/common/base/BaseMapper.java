package com.aptech.finance.common.base;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<E, D> {

    // Entity -> DTO
    D toDto(E entity);

    List<D> toDtoList(List<E> entities);

    // DTO -> Entity
    E toEntity(D dto);

    List<E> toEntityList(List<D> dtos);

    // Update entity từ DTO (rất quan trọng)
    void updateEntityFromDto(D dto, @MappingTarget E entity);
}