package org.example.splab;

public class Image implements Element {
    private String url;

    public Image(String url) {
        this.url = url;
        try {
            // Simulăm timpul lung necesar încărcării imaginii reale
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print() {
        System.out.println("Image with name: " + url);
    }
}