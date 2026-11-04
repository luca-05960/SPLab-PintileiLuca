package org.example.splab;

import org.example.splab.commands.*;
import org.example.splab.controllers.BooksController;
import org.example.splab.persistence.AuthorsRepository;
import org.example.splab.persistence.BooksCrudRepository;
import org.example.splab.persistence.BooksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@SpringBootTest
class SplabApplicationTests {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private BooksCrudRepository booksCrudRepository;

    @Autowired
    private CommandContext commandContext;

    @Autowired
    private BooksController booksController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(booksRepository);
        Assertions.assertNotNull(authorsRepository);
        Assertions.assertNotNull(booksCrudRepository);
        Assertions.assertNotNull(commandContext);
        Assertions.assertNotNull(booksController);
    }

    @Test
    @Transactional
    void testBookPersistenceWithElementsAndAuthors() {
        Book book = new Book("Design Patterns: Elements of Reusable Object-Oriented Software");
        Author author1 = new Author("Erich", "Gamma");
        Author author2 = new Author("Richard", "Helm");
        book.addAuthor(author1);
        book.addAuthor(author2);

        Section chapter1 = new Section("Chapter 1: Introduction");
        Paragraph p1 = new Paragraph("Designing object-oriented software is hard.");
        chapter1.add(p1);

        ImageProxy imageProxy = new ImageProxy("diagram1.png");
        chapter1.add(imageProxy);

        book.addContent(chapter1);

        // Save via booksCrudRepository
        Book savedBook = booksCrudRepository.save(book);

        Assertions.assertNotNull(savedBook.getId(), "Book ID should be generated");
        Assertions.assertNotNull(author1.getId(), "Author ID should be generated");
        Assertions.assertNotNull(chapter1.getId(), "Section ID should be generated");
        Assertions.assertNotNull(p1.getId(), "Paragraph ID should be generated");
        Assertions.assertNotNull(imageProxy.getId(), "ImageProxy ID should be generated");

        // Retrieve book
        Book retrievedBook = booksCrudRepository.findById(savedBook.getId());
        Assertions.assertNotNull(retrievedBook);
        Assertions.assertEquals("Design Patterns: Elements of Reusable Object-Oriented Software", retrievedBook.getTitle());
        Assertions.assertEquals(2, retrievedBook.getAuthors().size());
        Assertions.assertEquals(1, retrievedBook.getChildren().size());
    }

    @Test
    void testCommandsAndContext() {
        Book book = new Book("Clean Code");
        Author author = new Author("Robert", "Martin");
        book.addAuthor(author);

        // Test CreateBookCommand
        CreateBookCommand createCommand = new CreateBookCommand(commandContext, book);
        Book created = createCommand.execute();
        Assertions.assertNotNull(created.getId());

        // Test GetBookCommand
        GetBookCommand getCommand = new GetBookCommand(commandContext, created.getId());
        Book fetched = getCommand.execute();
        Assertions.assertNotNull(fetched);
        Assertions.assertEquals("Clean Code", fetched.getTitle());

        // Test GetAllBooksCommand
        GetAllBooksCommand getAllCommand = new GetAllBooksCommand(commandContext);
        Collection<Book> allBooks = getAllCommand.execute();
        Assertions.assertFalse(allBooks.isEmpty());

        // Test UpdateBookCommand
        Book updatedData = new Book("Clean Code - Second Edition");
        UpdateBookCommand updateCommand = new UpdateBookCommand(commandContext, created.getId(), updatedData);
        Book updated = updateCommand.execute();
        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Clean Code - Second Edition", updated.getTitle());

        // Test DeleteBookCommand
        DeleteBookCommand deleteCommand = new DeleteBookCommand(commandContext, created.getId());
        String deleteResult = deleteCommand.execute();
        Assertions.assertNotNull(deleteResult);

        // Verify deletion
        Book afterDelete = booksCrudRepository.findById(created.getId());
        Assertions.assertNull(afterDelete);
    }

    @Test
    void testBooksControllerEndpoints() {
        Book book = new Book("Refactoring");
        Author author = new Author("Martin", "Fowler");
        book.addAuthor(author);

        // POST /books
        Book created = booksController.createBook(book);
        Assertions.assertNotNull(created.getId());

        // GET /books/{id}
        Book fetched = booksController.getBookById(created.getId());
        Assertions.assertNotNull(fetched);
        Assertions.assertEquals("Refactoring", fetched.getTitle());

        // GET /books
        Collection<Book> all = booksController.getAllBooks();
        Assertions.assertTrue(all.stream().anyMatch(b -> b.getId().equals(created.getId())));

        // PUT /books/{id}
        Book updatedData = new Book("Refactoring 2nd Edition");
        Book updated = booksController.updateBook(created.getId(), updatedData);
        Assertions.assertEquals("Refactoring 2nd Edition", updated.getTitle());

        // DELETE /books/{id}
        booksController.deleteBook(created.getId());
        Assertions.assertNull(booksController.getBookById(created.getId()));
    }
}
