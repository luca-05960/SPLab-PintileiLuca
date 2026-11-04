package org.example.splab.commands;

import org.example.splab.Book;
import org.example.splab.persistence.BooksCrudRepository;

public class CreateBookCommand implements Command<Book> {
    private final CommandContext context;
    private Book book;

    public CreateBookCommand(CommandContext context) {
        this.context = context;
    }

    public CreateBookCommand(CommandContext context, Book book) {
        this.context = context;
        this.book = book;
    }

    public CreateBookCommand(BooksCrudRepository booksRepository) {
        this.context = new CommandContext(booksRepository);
    }

    public CreateBookCommand(BooksCrudRepository booksRepository, Book book) {
        this.context = new CommandContext(booksRepository);
        this.book = book;
    }

    @Override
    public Book execute() {
        return context.getBooksRepository().save(book);
    }

    public Book execute(Book book) {
        this.book = book;
        return execute();
    }
}
