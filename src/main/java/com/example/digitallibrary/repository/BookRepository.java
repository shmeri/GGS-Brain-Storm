package com.example.digitallibrary.repository;

import  com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends JpaRepository<Book, Integer>
{
  Optional<List<Book>> findByTitleContainingOrAuthorContaining(String title, String author);
  Optional<List<Book>> findByTitleAndAuthor(String title, String author);
  Optional<List<Book>> findByGenre(Genre genre);
}
