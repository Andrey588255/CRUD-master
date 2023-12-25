package org.example.repository;

import org.example.model.Label;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {
    Optional<Label> getById( Long id);
    void deleteById(ID id);
    T save (T entity);
    List<Label> findAll();
    boolean checkIfExists(ID id);
}

