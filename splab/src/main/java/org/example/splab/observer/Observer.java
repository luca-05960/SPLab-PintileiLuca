package org.example.splab.observer;

import org.example.splab.Book;

public interface Observer {
    void update(Book book);

    default void update() {
        update(null);
    }
}
