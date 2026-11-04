package org.example.splab.commands;

import org.example.splab.Book;
import org.example.splab.persistence.BooksCrudRepository;
import org.example.splab.services.BooksService;

public class UpdateBookCommand implements Command<Book> {
    private final CommandContext context;
    private final int id;
    private final Book book;
    private BooksService booksService;

    public UpdateBookCommand(CommandContext context, int id, Book book) {
        this.context = context;
        this.id = id;
        this.book = book;
    }

    public UpdateBookCommand(BooksCrudRepository repository, int id, Book book) {
        this.context = new CommandContext(repository);
        this.id = id;
        this.book = book;
    }

    public UpdateBookCommand(BooksService booksService, int id, Book book) {
        this.context = null;
        this.booksService = booksService;
        this.id = id;
        this.book = book;
    }

    @Override
    public Book execute() {
        if (context != null && context.getBooksRepository() != null) {
            Book existing = context.getBooksRepository().findById(id);
            if (existing != null) {
                book.setId(id);
                return context.getBooksRepository().save(book);
            }
            return null;
        }
        if (booksService != null) {
            return booksService.updateBook(id, book);
        }
        return null;
    }
}
