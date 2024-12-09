package com.bookstore.service.impl;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.CreateBookRequestDto;
import com.bookstore.exception.EntityNotFoundException;
import com.bookstore.mapper.BookMapper;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
            .map(bookMapper::toDto)
            .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find book by id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete book by id: "
                + id + " because it does not exist");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot update book by id: "
                + id + " because it does not exist"));

        bookMapper.updateBookFromDto(bookDto, book);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }
}
