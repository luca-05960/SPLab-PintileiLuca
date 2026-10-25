package org.example.splab.commands;

import org.example.splab.Book;
import org.example.splab.services.BooksService;

public class AddBookCommand implements Command<Book> {
    private final BooksService booksService;
    private final Book book;

    public AddBookCommand(BooksService booksService, Book book) {
        this.booksService = booksService;
        this.book = book;
    }

    @Override
    public Book execute() {
        return booksService.addBook(book);
    }
}