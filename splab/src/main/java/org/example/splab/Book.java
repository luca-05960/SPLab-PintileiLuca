package org.example.splab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book extends Section {
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    public Book() {}

    public Book(String title) {
        super(title);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addContent(Element element) {
        super.add(element);
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
        super.accept(visitor);
    }
}
