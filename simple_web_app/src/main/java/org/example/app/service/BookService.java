package org.example.app.service;

import java.util.List;

public interface BookService<T> {

    List<T> getAllBook();

    void saveBook(T book);

    boolean removeBookByID(Integer bookIdToRemove);
}
