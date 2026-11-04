package org.example.splab.services;

import org.example.splab.Book;
import org.example.splab.persistence.BooksCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BooksService {
    private final BooksCrudRepository booksRepository;

    @Autowired
    public BooksService(BooksCrudRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Collection<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Book getBook(int id) {
        return booksRepository.findById(id);
    }

    public Book addBook(Book book) {
        return booksRepository.save(book);
    }

    public Book updateBook(int id, Book book) {
        Book existing = booksRepository.findById(id);
        if (existing != null) {
            book.setId(id);
            return booksRepository.save(book);
        }
        return null;
    }

    public String deleteBook(int id) {
        booksRepository.deleteById(id);
        return "Book " + id + " deleted successfully.";
    }
}
