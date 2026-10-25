package org.example.splab.commands;

public interface Command<T> {
    T execute();
}