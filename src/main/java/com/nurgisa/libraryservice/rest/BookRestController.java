package com.nurgisa.libraryservice.rest;

import com.nurgisa.libraryservice.model.Author;
import com.nurgisa.libraryservice.service.AuthorService;
import com.nurgisa.libraryservice.service.BookService;
import com.nurgisa.libraryservice.model.Book;
import com.nurgisa.libraryservice.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/library/books/")
public class BookRestController {

    @Autowired
    private BookService BookService;

    @Autowired
    private AuthorService authorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getBook(@PathVariable("id") Long BookId) {
        if (BookId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Book Book = this.BookService.getById(BookId);


        if (Book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Book, HttpStatus.OK);
    }

    @RequestMapping(value = "author/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getBookByAuthor(@PathVariable("id") Long authorId) {
        if (authorId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Author author = this.authorService.getById(authorId);

        List<Book> books = this.BookService.getByAuthor(author);

        if (books == null) {
            String message = CustomException.getMessage(CustomException.EXCEPTION_NO_DATA_FOUND);
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> updateBook(@RequestBody @Valid Book Book) {
        HttpHeaders headers = new HttpHeaders();

        if (Book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        this.BookService.save(Book);
        return new ResponseEntity<>(Book, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book, @PathVariable("id") Long author_id) {
        HttpHeaders headers = new HttpHeaders();

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Author author = authorService.getById(author_id);

        if(author !=null){
            book.setAuthor(author);
        }

        this.BookService.save(book);

        return new ResponseEntity<>(book, headers, HttpStatus.OK);
     }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        Book Book = this.BookService.getById(id);

        if (Book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.BookService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> Books = this.BookService.getAll();

        if (Books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Books, HttpStatus.OK);
    }

    @RequestMapping(value = "/orderbyDate",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getByDatePublishedOrder(){
        List<Book> books = this.BookService.findByDatePublishedOrder();

        if (books.isEmpty()) {
            String message = CustomException.getMessage(CustomException.EXCEPTION_NO_DATA_FOUND);
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @RequestMapping(value = "/genre",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getByGenreList(@RequestParam String genre){
        List<Book> books = BookService.findByGenre(genre);

        if (books.isEmpty()) {
            String message = CustomException.getMessage(CustomException.EXCEPTION_NO_DATA_FOUND);
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books,HttpStatus.OK);
    }


}
