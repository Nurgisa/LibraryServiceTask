package com.nurgisa.libraryservice.service;

import com.nurgisa.libraryservice.model.Author;
import com.nurgisa.libraryservice.model.Book;

import java.math.BigInteger;
import java.util.List;


public interface BookService {

    Book getById(Long id);

    void save(Book book);

    void delete(Long id);

    List<Book> getAll();

    List<Book> findByGenre(String genre);

    List<Book> getByAuthor(Author author);

    List<Book> findByDatePublishedOrder();
}
