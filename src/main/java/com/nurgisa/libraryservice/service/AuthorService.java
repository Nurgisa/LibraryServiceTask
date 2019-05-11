package com.nurgisa.libraryservice.service;

import com.nurgisa.libraryservice.model.Author;

import java.util.List;

/**
 * Created by User on 10.05.2019.
 */
public interface AuthorService {

    Author getById(Long id);

    void save(Author author);

    void delete(Long id);

    List<Author> getAll();
}
