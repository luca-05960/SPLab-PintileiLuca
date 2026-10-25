package org.example.splab.commands;

import org.example.splab.Book;
import org.example.splab.services.BooksService;
import java.util.Collection;

public class GetAllBooksCommand implements Command<Collection<Book>> {
    private final BooksService booksService;

    public GetAllBooksCommand(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public Collection<Book> execute() {
        return booksService.getAllBooks();
    }
}