package org.example.splab.commands;

import org.example.splab.Book;
import org.example.splab.persistence.BooksCrudRepository;
import org.example.splab.services.BooksService;

public class AddBookCommand implements Command<Book> {
    private final CommandContext context;
    private final Book book;
    private BooksService booksService;

    public AddBookCommand(CommandContext context, Book book) {
        this.context = context;
        this.book = book;
    }

    public AddBookCommand(BooksCrudRepository repository, Book book) {
        this.context = new CommandContext(repository);
        this.book = book;
    }

    public AddBookCommand(BooksService booksService, Book book) {
        this.context = null;
        this.booksService = booksService;
        this.book = book;
    }

    @Override
    public Book execute() {
        if (context != null && context.getBooksRepository() != null) {
            return context.getBooksRepository().save(book);
        }
        if (booksService != null) {
            return booksService.addBook(book);
        }
        return null;
    }
}
