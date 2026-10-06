package org.example.splab;

public interface Element {
    void print();

    default void add(Element element) {
        throw new UnsupportedOperationException("Nu se pot adăuga elemente într-o frunză!");
    }

    default void remove(Element element) {
        throw new UnsupportedOperationException("Operațiune invalidă pentru frunză!");
    }

    default Element get(int index) {
        throw new UnsupportedOperationException("Operațiune invalidă pentru frunză!");
    }
}