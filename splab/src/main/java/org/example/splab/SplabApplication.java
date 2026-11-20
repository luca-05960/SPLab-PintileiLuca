package org.example.splab;

import org.example.splab.difexample.ClientComponent;
import org.example.splab.difexample.SingletonComponent;
import org.example.splab.difexample.TransientComponent;
import org.example.splab.persistence.BooksCrudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class SplabApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SplabApplication.class, args);

        TransientComponent transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        SingletonComponent singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        ClientComponent c = context.getBean(ClientComponent.class);
        c.operation();

        c = (ClientComponent) context.getBean("clientComponent");
        c.operation();
    }

    @Bean
    public CommandLineRunner initData(BooksCrudRepository booksRepository) {
        return args -> {
            List<Book> existing = booksRepository.findAll();

            boolean hasDesignPatterns = existing.stream().anyMatch(b -> b.getTitle() != null && b.getTitle().contains("Design Patterns"));
            if (!hasDesignPatterns) {
                Book book1 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software");
                book1.addAuthor(new Author("Erich", "Gamma"));
                book1.addAuthor(new Author("Richard", "Helm"));
                book1.addAuthor(new Author("Ralph", "Johnson"));
                book1.addAuthor(new Author("John", "Vlissides"));

                Section chapter1 = new Section("Chapter 1: Introduction");
                chapter1.add(new Paragraph("Designing object-oriented software is hard, and designing reusable object-oriented software is even harder."));
                chapter1.add(new ImageProxy("gof-diagram.png"));
                chapter1.add(new Table("Design Pattern Classification"));

                Section chapter2 = new Section("Chapter 2: Creational Patterns");
                chapter2.add(new Paragraph("Abstract Factory, Builder, Factory Method, Prototype, Singleton."));
                chapter2.add(new ImageProxy("factory-method.png"));

                book1.addContent(chapter1);
                book1.addContent(chapter2);
                booksRepository.save(book1);
            }

            boolean hasCleanCode = existing.stream().anyMatch(b -> b.getTitle() != null && b.getTitle().contains("Clean Code"));
            if (!hasCleanCode) {
                Book book2 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship");
                book2.addAuthor(new Author("Robert", "Martin"));

                Section ccSection = new Section("Chapter 1: Clean Code Principles");
                ccSection.add(new Paragraph("Even bad code can function. But if code isn't clean, it can bring a development organization to its knees."));
                ccSection.add(new Paragraph("Always leave the campground cleaner than you found it (The Boy Scout Rule)."));

                book2.addContent(ccSection);
                booksRepository.save(book2);
            }

            boolean hasRefactoring = existing.stream().anyMatch(b -> b.getTitle() != null && b.getTitle().contains("Refactoring"));
            if (!hasRefactoring) {
                Book book3 = new Book("Refactoring: Improving the Design of Existing Code");
                book3.addAuthor(new Author("Martin", "Fowler"));
                book3.addAuthor(new Author("Kent", "Beck"));

                Section refSection = new Section("Chapter 1: Refactoring: A First Example");
                refSection.add(new Paragraph("Any fool can write code that a computer can understand. Good programmers write code that humans can understand."));
                refSection.add(new Table("Common Code Smells & Refactorings"));

                book3.addContent(refSection);
                booksRepository.save(book3);
            }

            boolean hasPragmatic = existing.stream().anyMatch(b -> b.getTitle() != null && b.getTitle().contains("Pragmatic"));
            if (!hasPragmatic) {
                Book book4 = new Book("The Pragmatic Programmer: Your Journey to Mastery");
                book4.addAuthor(new Author("Andrew", "Hunt"));
                book4.addAuthor(new Author("David", "Thomas"));

                Section pragSection = new Section("Chapter 1: A Pragmatic Philosophy");
                pragSection.add(new Paragraph("Care About Your Craft. Why spend your life developing software unless you care about doing it well?"));

                book4.addContent(pragSection);
                booksRepository.save(book4);
            }
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
