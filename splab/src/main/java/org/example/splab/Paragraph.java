package org.example.splab;

public class Paragraph implements Element {
    private String text;
    private AlignStrategy textAlignment; // Atributul pentru strategie

    public Paragraph(String text) {
        this.text = text;
    }

    // Metoda cerută în main pentru a schimba strategia
    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.textAlignment = alignStrategy;
    }

    @Override
    public void print() {
        if (textAlignment != null) {
            // Dacă avem o strategie setată, o folosim pentru a printa textul
            textAlignment.render(text);
        } else {
            // Comportamentul default dacă nu s-a setat nicio strategie
            System.out.println("Paragraph: " + text);
        }
    }
}