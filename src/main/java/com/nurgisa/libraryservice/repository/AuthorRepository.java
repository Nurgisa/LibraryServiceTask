package com.nurgisa.libraryservice.repository;

import com.nurgisa.libraryservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by User on 10.05.2019.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

}

