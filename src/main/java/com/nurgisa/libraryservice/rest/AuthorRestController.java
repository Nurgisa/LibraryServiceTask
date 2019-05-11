package com.nurgisa.libraryservice.rest;

import com.nurgisa.libraryservice.model.Author;
import com.nurgisa.libraryservice.model.Book;
import com.nurgisa.libraryservice.repository.AuthorRepository;
import com.nurgisa.libraryservice.service.AuthorService;
import com.nurgisa.libraryservice.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 10.05.2019.
 */

@RestController
@RequestMapping("/api/library/authors/")
public class AuthorRestController {

    @Autowired
    AuthorService authorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRestController.class);


    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAuthor(@PathVariable("id") Long AuthorId) {
        if (AuthorId == null) {
            String message = CustomException.getMessage(CustomException.EXCEPTION_ID_IS_NULL);
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }

        Author author = this.authorService.getById(AuthorId);

        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllAuthors()throws CustomException {
        List<Author>  authors = this.authorService.getAll();

        if (authors.isEmpty()) {
            String message = CustomException.getMessage(CustomException.EXCEPTION_NO_DATA_FOUND);
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }



    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateAuthors(@RequestBody @Valid Author author, UriComponentsBuilder builder)throws CustomException {
        HttpHeaders headers = new HttpHeaders();

        if (author.getName() == null) {
            String message = CustomException.getMessage(CustomException.EXCEPTION_EMPTY_DATA);
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }

        this.authorService.save(author);

        return new ResponseEntity<>(author, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id) {
        Author author = this.authorService.getById(id);

        if (author == null) {
            String message = CustomException.getMessage(CustomException.EXCEPTION_NO_DATA_FOUND);
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        this.authorService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
