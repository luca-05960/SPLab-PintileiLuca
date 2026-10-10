package org.example.splab;

public class AlignRight implements AlignStrategy {
    @Override
    public void render(String paragraphText) {
        System.out.println(paragraphText + " *****");
    }
}