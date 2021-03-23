package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.*;
import com.example.digitallibrary.model.Author;
import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Comment;
import com.example.digitallibrary.service.AuthorsService;
import com.example.digitallibrary.service.BookService;
import com.example.digitallibrary.service.CommentService;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
public class BookController
{
  private final BookService    bookService;
  private final CommentService commentService;
  private final AuthorsService authorsService;

  public BookController(BookService bookService,
                        CommentService commentService,
                        AuthorsService authorsService)
  {
    this.bookService = bookService;
    this.commentService = commentService;
    this.authorsService = authorsService;
  }

  @GetMapping()
  public ResponseEntity<List<BookDTO>> findAllBooks(
      @RequestParam(required = false, defaultValue = "*") String title,
      @RequestParam(required = false, defaultValue = "*") String author)
  {
    List<BookDTO> books = new ArrayList<>();
    if ("*".equals(author) && "*".equals(title)) {
      author = title = "";
    }
    List<Book> originBooks = bookService.findBookByCriteria(title, author);
    for (Book book : originBooks) {
      books.add(convert(book));
    }
    return ResponseEntity.ok(books);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTOWithComments> getBook(@PathVariable @NotNull Integer id)
  {
    Optional<Book> b = bookService.getBook(id);
    if (!b.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
      BookDTOWithComments bookForClient = new BookDTOWithComments();
      List<CreateCommentDTO> comments = new ArrayList<>();
      List<Comment> c = commentService.getAllComments(b.get().getId());
      for (Comment comment : c) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUserName(comment.getReader().getUsername())
            .setContent(comment.getText())
            .setTime(comment.getDate());
        comments.add(commentDTO);
      }
      Book book1 = b.get();
      bookForClient.setId(book1.getId());
      bookForClient.setYear(book1.getYear());
      bookForClient.setAuthor(book1.getAuthor().getFullName());
      bookForClient.setTitle(book1.getTitle());
      bookForClient.setRating(book1.getRating());
      bookForClient.setCommentList(comments);
      bookForClient.setCountComments(comments.size());
      return ResponseEntity.ok(bookForClient);
    }
  }

  @PostMapping
  public ResponseEntity<BookDTO> addBook(@RequestBody CreateBookDTO createBookDTO, Principal principal)  //adding a object Book to the repo
  {
    Optional<Author> author = authorsService.getAuthor(principal.getName());
    if (author.isPresent()) {
      Optional<Book> existing = bookService.getBook(createBookDTO.getTitle(), principal.getName());
      if (existing.isPresent()) {
        return ResponseEntity.ok(convert(existing.get()));
      }
      Book book = new Book();
      book.setAuthor(author.get());
      book.setGenre(createBookDTO.getGenre());
      book.setTitle(createBookDTO.getTitle());
      book.setYear(createBookDTO.getYear());
      bookService.addBook(book);
      return ResponseEntity.status(HttpStatus.CREATED).body(convert(book));
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable @NotNull Integer id)
  {
    Optional<Book> book = bookService.getBook(id);
    if (book.isPresent()) {
      commentService.deleteComments(id);
      bookService.deleteBook(book.get());
      return ResponseEntity.ok().build();
    }
    else {
     return ResponseEntity.notFound().build();
    }
  }

  private BookDTO convert(Book book)
  {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setAuthor(book.getAuthor().getFullName());
    bookDTO.setGenre(book.getGenre());
    bookDTO.setId(book.getId());
    bookDTO.setTitle(book.getTitle());
    bookDTO.setYear(book.getYear());
    bookDTO.setRating(book.getRating());
    bookDTO.setCountComments(book.getNumOfRatings());
    bookDTO.setNumberOfLibraries(book.getNumOfLibraries());
    return bookDTO;
  }
}
