package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {
    Optional<T> getById( Long id);
    void deleteById(ID id);
    T save (T entity);
    List<T> findAll();
    boolean checkIfExists(ID id);
}

