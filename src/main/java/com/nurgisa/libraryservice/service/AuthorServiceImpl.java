package com.nurgisa.libraryservice.service;

import com.nurgisa.libraryservice.model.Author;
import com.nurgisa.libraryservice.model.Book;
import com.nurgisa.libraryservice.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 10.05.2019.
 */


@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService{


    @Autowired
    AuthorRepository authorRepository;


    @Override
    public Author getById(Long id) {
        //log.info("IN BookServiceImpl getById {}", id);
        return authorRepository.findOne(id);
    }

    @Override
    public void save(Author author) {
        //log.info("IN BookServiceImpl save {}", Book);
        authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        //log.info("IN BookServiceImpl delete {}", id);
        authorRepository.delete(id);
    }

    @Override
    public List<Author> getAll() {
        //log.info("IN BookServiceImpl getAll");
        return authorRepository.findAll();
    }

}
