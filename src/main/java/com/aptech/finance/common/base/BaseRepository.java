package com.aptech.finance.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID> {

    // ===============================
    // SOFT DELETE SUPPORT
    // ===============================

    default void softDelete(T entity) {
        entity.setIsDeleted(true);
        save(entity);
    }

    default void softDeleteById(ID id) {
        findById(id).ifPresent(entity -> {
            entity.setIsDeleted(true);
            save(entity);
        });
    }

    // ===============================
    // FIND NOT DELETED
    // ===============================

    List<T> findAllByIsDeletedFalse();

    Optional<T> findByIdAndIsDeletedFalse(ID id);

    boolean existsByIdAndIsDeletedFalse(ID id);
}