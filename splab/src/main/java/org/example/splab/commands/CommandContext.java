package org.example.splab.commands;

import org.example.splab.persistence.BooksCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandContext {
    private BooksCrudRepository booksRepository;

    public CommandContext() {}

    @Autowired
    public CommandContext(BooksCrudRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public BooksCrudRepository getBooksRepository() {
        return booksRepository;
    }

    public void setBooksRepository(BooksCrudRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
}
