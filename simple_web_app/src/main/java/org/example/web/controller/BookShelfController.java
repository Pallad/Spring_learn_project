package org.example.web.controller;

import org.example.app.service.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "books")
public class BookShelfController {
    private final Logger logger = Logger.getLogger(BookShelfController.class);

    BookService<Book> bookService;

    @Autowired
    public BookShelfController(BookService<Book> bookService){
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model){
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBook());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book){
        bookService.saveBook(book);
        logger.info("Current repository size:" + bookService.getAllBook().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove", defaultValue = "0") Integer bookIdToRemove){
        bookService.removeBookByID(bookIdToRemove);
        return "redirect:/books/shelf";
    }
}
