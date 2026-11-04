package org.example.splab.persistence;

import org.example.splab.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksRepositoryAdapter implements BooksCrudRepository {
    private final BooksRepository springDataRepository;

    public BooksRepositoryAdapter(BooksRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Book save(Book book) {
        return springDataRepository.save(book);
    }

    @Override
    public Book findById(Integer id) {
        return springDataRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return springDataRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        springDataRepository.deleteById(id);
    }
}
