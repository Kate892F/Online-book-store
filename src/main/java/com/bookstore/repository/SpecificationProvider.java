package com.bookstore.repository;

import com.bookstore.model.Book;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {

    Specification<Book> getSpecification(String[] params);

    String getKey();
}
