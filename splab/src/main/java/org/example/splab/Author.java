package org.example.splab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String surname;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void print() {
        if (surname != null && !surname.isEmpty()) {
            System.out.println("Author: " + name + " " + surname);
        } else {
            System.out.println("Author: " + name);
        }
    }
}
