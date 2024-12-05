package com.bookstore;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookstoreApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book effectiveJava = new Book();
            effectiveJava.setTitle("Effective Java");
            effectiveJava.setAuthor("Joshua Bloch");
            effectiveJava.setIsbn("978-0134685991");
            effectiveJava.setPrice(BigDecimal.valueOf(45.00));
            effectiveJava.setDescription(
                    "Comprehensive guide to best practices in Java programming.");
            bookService.save(effectiveJava);

            Book cleanCode = new Book();
            cleanCode.setTitle("Clean Code");
            cleanCode.setAuthor("Robert C. Martin");
            cleanCode.setIsbn("978-0132350884");
            cleanCode.setPrice(BigDecimal.valueOf(40.00));
            cleanCode.setDescription("A handbook of agile software craftsmanship.");
            bookService.save(cleanCode);

            System.out.println(bookService.findAll());
        };
    }
}
