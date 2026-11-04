package org.example.splab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseElement implements Element {
    @Id
    @GeneratedValue
    private Integer id;

    public BaseElement() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void print() {}

    @Override
    public void accept(Visitor visitor) {}
}
