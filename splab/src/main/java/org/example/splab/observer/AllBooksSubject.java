package org.example.splab.observer;

import org.example.splab.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class AllBooksSubject implements Subject {
    private final List<Observer> observerCollection = new CopyOnWriteArrayList<>();
    private final List<Book> books = new CopyOnWriteArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null && !observerCollection.contains(observer)) {
            observerCollection.add(observer);
        }
    }

    @Override
    public void unregisterObserver(Observer observer) {
        if (observer != null) {
            observerCollection.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        Book latest = books.isEmpty() ? null : books.get(books.size() - 1);
        notifyObservers(latest);
    }

    @Override
    public void notifyObservers(Book book) {
        for (Observer observer : observerCollection) {
            observer.update(book);
        }
    }

    public void add(Book book) {
        if (book != null) {
            books.add(book);
        }
        notifyObservers(book);
    }

    public List<Observer> getObserverCollection() {
        return Collections.unmodifiableList(new ArrayList<>(observerCollection));
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(new ArrayList<>(books));
    }
}
