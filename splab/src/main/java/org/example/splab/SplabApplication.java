package org.example.splab;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplabApplication {

    public static void main(String[] args) throws Exception {
        Book myBook = new Book("My Book");

        Section cap1 = new Section("Capitolul 1");
        cap1.add(new Paragraph("Some text in cap 1"));

        Section cap2 = new Section("Capitolul 2");
        cap2.add(new Paragraph("Some text in cap 2"));

        Section subcap2_1 = new Section("Subcapitolul 2.1");
        cap2.add(subcap2_1); // Punem un subcapitol în capitolul 2

        myBook.addContent(cap1);
        myBook.addContent(cap2);

        // Generăm Cuprinsul
        GenerateToC generateToC = new GenerateToC();
        myBook.accept(generateToC);

        // Luăm cuprinsul generat de vizitator
        TableOfContents toc = generateToC.getToC();

        // Îl afișăm pe ecran!
        toc.print();
    }
}