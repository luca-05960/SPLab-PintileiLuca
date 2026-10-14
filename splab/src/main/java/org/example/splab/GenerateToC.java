package org.example.splab;

public class GenerateToC implements Visitor {
    private TableOfContents toc = new TableOfContents();
    private int currentPage = 1; // Contorul nostru virtual de pagini, începe de la 1

    public TableOfContents getToC() {
        return toc;
    }

    @Override
    public void visit(Book book) {
    }

    @Override
    public void visit(Section section) {
        if (section instanceof Book) {
            return;
        }
        toc.addEntry(section.getTitle() + " .................... Page " + currentPage);
    }

    @Override
    public void visit(TableOfContents tableOfContents) {
    }

    @Override
    public void visit(Paragraph paragraph) {
        // Considerăm că fiecare paragraf printat ocupă o pagină
        currentPage++;
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        currentPage++;
    }

    @Override
    public void visit(Image image) {
        currentPage++;
    }

    @Override
    public void visit(Table table) {
        currentPage++;
    }
}