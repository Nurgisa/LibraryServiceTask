package com.nurgisa.libraryservice.repository;

import com.nurgisa.libraryservice.model.Author;
import com.nurgisa.libraryservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(Author author);

    List<Book> findByGenre(String genre);

    @Query(value = "select * from books order by date_published",nativeQuery = true)
    List<Book> findByDatePublishedOrder();
}
