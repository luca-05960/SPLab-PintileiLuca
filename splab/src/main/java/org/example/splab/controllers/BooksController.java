package org.example.splab.controllers;

import org.example.splab.Book;
import org.example.splab.commands.*;
import org.example.splab.observer.AllBooksSubject;
import org.example.splab.persistence.BooksCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksCrudRepository booksRepository;
    private final CommandContext commandContext;
    private final AllBooksSubject allBooksSubject;

    @Autowired
    public BooksController(BooksCrudRepository booksRepository,
                           CommandContext commandContext,
                           @Autowired(required = false) AllBooksSubject allBooksSubject) {
        this.booksRepository = booksRepository;
        this.commandContext = commandContext != null ? commandContext : new CommandContext(booksRepository);
        this.allBooksSubject = allBooksSubject;
    }

    public BooksController(BooksCrudRepository booksRepository, CommandContext commandContext) {
        this(booksRepository, commandContext, null);
    }

    public BooksController(BooksCrudRepository booksRepository) {
        this(booksRepository, new CommandContext(booksRepository), null);
    }

    @GetMapping
    public Collection<Book> getAllBooks() {
        GetAllBooksCommand command = new GetAllBooksCommand(commandContext);
        return command.execute();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        GetBookCommand command = new GetBookCommand(commandContext, id);
        return command.execute();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        CreateBookCommand command = new CreateBookCommand(commandContext, book);
        Book savedBook = command.execute();
        if (allBooksSubject != null) {
            allBooksSubject.add(savedBook);
        }
        return savedBook;
    }

    public Book newBook(Book book) {
        return createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        UpdateBookCommand command = new UpdateBookCommand(commandContext, id, book);
        return command.execute();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        DeleteBookCommand command = new DeleteBookCommand(commandContext, id);
        return command.execute();
    }
}
