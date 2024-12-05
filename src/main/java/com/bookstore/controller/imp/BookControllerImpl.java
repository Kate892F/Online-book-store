package com.bookstore.controller.imp;

import com.bookstore.controller.BookController;
import com.bookstore.dto.BookDto;
import com.bookstore.dto.CreateBookRequestDto;
import com.bookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable(name = "id") Long id) {
        return bookService.getBookById(id);
    }

    @Override
    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }
}