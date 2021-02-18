package org.example.app.repository;

import java.util.List;

public interface UserRepository<T> {
    List<T> retreiveAll();

    void store(T entity);

    boolean removeItemById(Integer entityId);

    boolean existsItem(T entity);

    boolean checkByName(T entity);
}
