package org.example.splab.observer;

import org.example.splab.Book;

public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers();
    void notifyObservers(Book book);

    default void attach(Observer observer) {
        registerObserver(observer);
    }

    default void detach(Observer observer) {
        unregisterObserver(observer);
    }
}
