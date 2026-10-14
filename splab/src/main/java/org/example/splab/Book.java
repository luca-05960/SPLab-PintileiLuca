package org.example.splab;

import java.util.ArrayList;
import java.util.List;

public class Book extends Section {
    private List<Author> authors = new ArrayList<>();

    public Book(String title) {
        super(title);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addContent(Element element) {
        super.add(element); // Refolosește logica din Section
    }

    @Override
    public void print() {
        System.out.println("Book: " + title + "\n");

        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }
        System.out.println();

        for (Element child : children) {
            child.print();
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        super.accept(visitor); // Va itera prin elementele adăugate în carte
    }

}