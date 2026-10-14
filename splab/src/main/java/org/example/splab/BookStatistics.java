package org.example.splab;

public class BookStatistics implements Visitor {
    private int imageCount = 0;
    private int paragraphCount = 0;
    private int tableCount = 0;

    @Override
    public void visit(Book book) {}

    @Override
    public void visit(Section section) {}

    @Override
    public void visit(TableOfContents tableOfContents) {}

    @Override
    public void visit(Paragraph paragraph) {
        paragraphCount++;
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        imageCount++;
    }

    @Override
    public void visit(Image image) {
        imageCount++;
    }

    @Override
    public void visit(Table table) {
        tableCount++;
    }

    public void printStatistics() {
        System.out.println("Book Statistics:");
        System.out.println("*** Number of images: " + imageCount);
        System.out.println("*** Number of tables: " + tableCount);
        System.out.println("*** Number of paragraphs: " + paragraphCount);
    }
}