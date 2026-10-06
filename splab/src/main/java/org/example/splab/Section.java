package org.example.splab;

import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
    protected String title; // Protected pentru a putea fi accesat de clasa copil Book
    protected List<Element> children = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println(title);
        // Iterează prin toți copiii și le apelează propria metodă print()
        for (Element child : children) {
            child.print();
        }
    }

    @Override
    public void add(Element element) {
        children.add(element);
    }

    @Override
    public void remove(Element element) {
        children.remove(element);
    }

    @Override
    public Element get(int index) {
        return children.get(index);
    }
}
