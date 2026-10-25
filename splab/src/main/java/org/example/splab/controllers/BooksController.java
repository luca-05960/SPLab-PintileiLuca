package org.example.splab.controllers;

import org.example.splab.Book;
import org.example.splab.commands.*;
import org.example.splab.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public Collection<Book> getAllBooks() {
        GetAllBooksCommand command = new GetAllBooksCommand(booksService);
        return command.execute();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        GetBookCommand command = new GetBookCommand(booksService, id);
        return command.execute();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        AddBookCommand command = new AddBookCommand(booksService, book);
        return command.execute();
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        UpdateBookCommand command = new UpdateBookCommand(booksService, id, book);
        return command.execute();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        DeleteBookCommand command = new DeleteBookCommand(booksService, id);
        return command.execute();
    }
}