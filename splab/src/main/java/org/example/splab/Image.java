package org.example.splab;

public class Image implements Element {
    private String url;

    public Image(String url) {
        this.url = url;
    }

    @Override
    public void print() {
        System.out.println("Image with name:" + url); // Exact formatul din output
    }
}