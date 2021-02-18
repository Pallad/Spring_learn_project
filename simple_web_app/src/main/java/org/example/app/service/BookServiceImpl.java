package org.example.app.service;

import org.apache.log4j.Logger;
import org.example.app.repository.BookRepository;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService<Book> {
    Logger logger = Logger.getLogger(this.getClass());

    private final BookRepository<Book> bookRepo;

    @Autowired
    public BookServiceImpl(BookRepository repo) {this.bookRepo = repo;}

    public List<Book> getAllBook(){
        return bookRepo.retreiveAll();
    }

    public void saveBook(Book book) {
        if (book.getAuthor().equals("")||book.getTitle().equals("")||(book.getSize() == null)||(book.getSize() <= 0)){
            logger.info("book don't store because it has empty fields:" + book);
        }else{
            logger.info("store new book:" + book);
            book.setId(book.hashCode());
            bookRepo.store(book);
        }
    }

    public boolean removeBookByID(Integer bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }
}
