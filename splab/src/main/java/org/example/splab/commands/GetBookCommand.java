package org.example.splab.commands;

import org.example.splab.Book;
import org.example.splab.persistence.BooksCrudRepository;
import org.example.splab.services.BooksService;

public class GetBookCommand implements Command<Book> {
    private final CommandContext context;
    private final int id;
    private BooksService booksService;

    public GetBookCommand(CommandContext context, int id) {
        this.context = context;
        this.id = id;
    }

    public GetBookCommand(BooksCrudRepository repository, int id) {
        this.context = new CommandContext(repository);
        this.id = id;
    }

    public GetBookCommand(BooksService booksService, int id) {
        this.context = null;
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public Book execute() {
        if (context != null && context.getBooksRepository() != null) {
            return context.getBooksRepository().findById(id);
        }
        if (booksService != null) {
            return booksService.getBook(id);
        }
        return null;
    }
}
