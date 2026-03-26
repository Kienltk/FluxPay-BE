package com.aptech.finance.common.base;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity, ID extends Serializable> {

    protected final BaseRepository<T, ID> repository;

    // ===============================
    // CREATE
    // ===============================

    public T create(T entity) {
        return repository.save(entity);
    }

    // ===============================
    // UPDATE
    // ===============================

    public T update(ID id, T entity) {
        T existing = findById(id);

        // giữ lại id
        entity.setId(existing.getId());

        return repository.save(entity);
    }

    // ===============================
    // FIND
    // ===============================

    public T findById(ID id) {
        return repository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + id));
    }

    public List<T> findAll() {
        return repository.findAllByIsDeletedFalse();
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // ===============================
    // DELETE (SOFT)
    // ===============================

    public void delete(ID id) {
        T entity = findById(id);
        repository.softDelete(entity);
    }

    // ===============================
    // EXISTS
    // ===============================

    public boolean exists(ID id) {
        return repository.existsByIdAndIsDeletedFalse(id);
    }
}