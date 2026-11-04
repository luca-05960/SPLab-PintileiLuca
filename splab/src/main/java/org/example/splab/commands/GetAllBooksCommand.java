package org.example.splab.commands;

import org.example.splab.Book;
import org.example.splab.persistence.BooksCrudRepository;
import org.example.splab.services.BooksService;

import java.util.Collection;
import java.util.Collections;

public class GetAllBooksCommand implements Command<Collection<Book>> {
    private final CommandContext context;
    private BooksService booksService;

    public GetAllBooksCommand(CommandContext context) {
        this.context = context;
    }

    public GetAllBooksCommand(BooksCrudRepository repository) {
        this.context = new CommandContext(repository);
    }

    public GetAllBooksCommand(BooksService booksService) {
        this.context = null;
        this.booksService = booksService;
    }

    @Override
    public Collection<Book> execute() {
        if (context != null && context.getBooksRepository() != null) {
            return context.getBooksRepository().findAll();
        }
        if (booksService != null) {
            return booksService.getAllBooks();
        }
        return Collections.emptyList();
    }
}
