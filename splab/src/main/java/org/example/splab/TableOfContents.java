package org.example.splab;

public class TableOfContents implements Element {
    private String something;

    public TableOfContents(String something) {
        this.something = something;
    }

    @Override
    public void print() {
        System.out.println("TableOfContents: " + something);
    }
}