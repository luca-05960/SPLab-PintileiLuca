package org.example.splab.persistence;

import org.example.splab.Book;

import java.util.List;

public interface BooksCrudRepository extends CrudRepository<Book, Integer> {
    @Override
    Book save(Book book);

    @Override
    Book findById(Integer id);

    @Override
    List<Book> findAll();

    @Override
    void deleteById(Integer id);
}
