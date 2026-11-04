package org.example.splab.controllers;

import org.example.splab.Book;
import org.example.splab.commands.*;
import org.example.splab.persistence.BooksCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksCrudRepository booksRepository;
    private final CommandContext commandContext;

    @Autowired
    public BooksController(BooksCrudRepository booksRepository, CommandContext commandContext) {
        this.booksRepository = booksRepository;
        this.commandContext = commandContext != null ? commandContext : new CommandContext(booksRepository);
    }

    public BooksController(BooksCrudRepository booksRepository) {
        this(booksRepository, new CommandContext(booksRepository));
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
        return command.execute();
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
