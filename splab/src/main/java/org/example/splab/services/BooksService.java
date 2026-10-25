package org.example.splab.services;

import org.example.splab.Book;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BooksService {
    private final Map<Integer, Book> books = new HashMap<>();

    public Collection<Book> getAllBooks() {
        return books.values();
    }

    public Book getBook(int id) {
        return books.get(id);
    }

    public Book addBook(Book book) {
        int newId = books.size() + 1;
        books.put(newId, book);
        return book;
    }

    public Book updateBook(int id, Book book) {
        if (books.containsKey(id)) {
            books.put(id, book);
            return book;
        }
        return null;
    }

    public String deleteBook(int id) {
        if (books.remove(id) != null) {
            return "Book " + id + " deleted successfully.";
        }
        return "Book not found.";
    }
}