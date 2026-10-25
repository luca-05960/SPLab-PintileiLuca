package org.example.splab.commands;

import org.example.splab.Book;
import org.example.splab.services.BooksService;

public class GetBookCommand implements Command<Book> {
    private final BooksService booksService;
    private final int id;

    public GetBookCommand(BooksService booksService, int id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public Book execute() {
        return booksService.getBook(id);
    }
}