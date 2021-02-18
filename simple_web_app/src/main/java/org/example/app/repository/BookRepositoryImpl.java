package org.example.app.repository;

import org.example.web.dto.Book;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository<Book> {
    private final Logger logger = Logger.getLogger(this.getClass());

    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book entity){
        repo.add(entity);
    }

    @Override
    public boolean removeItemById(Integer entityId) {
        for (Book book: repo){
            if (book.getId().equals(entityId)){
                logger.info("remove book completed:"  + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean existsItem(Book entity) {
        for (Book book: repo){
            if (book.getAuthor().equalsIgnoreCase(entity.getAuthor())&&book.getTitle().equalsIgnoreCase(entity.getTitle())&&book.getSize().equals(entity.getSize())){
                logger.info("such book exists:"  + book);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkByName(Book entity) {
        return existsItem(entity);
    }
}
