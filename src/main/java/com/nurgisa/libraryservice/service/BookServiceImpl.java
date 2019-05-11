package com.nurgisa.libraryservice.service;

import com.nurgisa.libraryservice.model.Author;
import com.nurgisa.libraryservice.model.Book;
import lombok.extern.slf4j.Slf4j;
import com.nurgisa.libraryservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;


@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository BookRepository;

    @Override
    public Book getById(Long id) {
        //log.info("IN BookServiceImpl getById {}", id);
        return BookRepository.findOne(id);
    }

    @Override
    public void save(Book Book) {
        //log.info("IN BookServiceImpl save {}", Book);
        BookRepository.save(Book);
    }

    @Override
    public void delete(Long id) {
        //log.info("IN BookServiceImpl delete {}", id);
        BookRepository.delete(id);
    }

    @Override
    public List<Book> getAll() {
        //log.info("IN BookServiceImpl getAll");
        return BookRepository.findAll();
    }

    @Override
    public List<Book> findByDatePublishedOrder(){
        return BookRepository.findByDatePublishedOrder();
    }

    @Override
    public List<Book> findByGenre(String genre){
        return BookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> getByAuthor(Author author){
        return BookRepository.findByAuthor(author);
    }
}
