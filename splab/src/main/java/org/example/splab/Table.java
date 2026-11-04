package org.example.splab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

@Entity
@jakarta.persistence.Table(name = "book_tables")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Table extends BaseElement {
    private String something;

    public Table() {}

    public Table(String something) {
        this.something = something;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    @Override
    public void print() {
        System.out.println("Table: " + something);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
