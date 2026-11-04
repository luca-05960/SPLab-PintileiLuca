package org.example.splab.commands;

import org.example.splab.persistence.BooksCrudRepository;
import org.example.splab.services.BooksService;

public class DeleteBookCommand implements Command<String> {
    private final CommandContext context;
    private final int id;
    private BooksService booksService;

    public DeleteBookCommand(CommandContext context, int id) {
        this.context = context;
        this.id = id;
    }

    public DeleteBookCommand(BooksCrudRepository repository, int id) {
        this.context = new CommandContext(repository);
        this.id = id;
    }

    public DeleteBookCommand(BooksService booksService, int id) {
        this.context = null;
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public String execute() {
        if (context != null && context.getBooksRepository() != null) {
            context.getBooksRepository().deleteById(id);
            return "Book " + id + " deleted successfully.";
        }
        if (booksService != null) {
            return booksService.deleteBook(id);
        }
        return "Book not found.";
    }
}
