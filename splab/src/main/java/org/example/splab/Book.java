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

        // Parcurgem și printăm manual conținutul (fără a folosi super.print()
        // deoarece super.print() ar afișa doar titlul simplu, nu "Book: Titlu")
        for (Element child : children) {
            child.print();
        }
    }
}