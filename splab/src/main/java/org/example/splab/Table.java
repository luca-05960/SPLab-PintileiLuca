package org.example.splab;

public class Table implements Element {
    private String something;

    public Table(String something) {
        this.something = something;
    }

    @Override
    public void print() {
        System.out.println("Table: " + something);
    }
}