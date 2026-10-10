package org.example.splab;

public class AlignLeft implements AlignStrategy {
    @Override
    public void render(String paragraphText) {
        System.out.println("***** " + paragraphText);
    }
}