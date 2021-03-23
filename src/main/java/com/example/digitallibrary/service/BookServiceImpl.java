package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService
{
  @Override
  public Optional<Book> getBook(Integer id)
  {
    return Optional.empty();
  }

  @Override
  public Optional<Book> getBook(String title, String author)
  {
    return Optional.empty();
  }

  @Override
  public void addRating(Book book, Integer rating)
  {

  }

  @Override
  public void removeRating(Book book, Integer rating)
  {

  }

  @Override
  public Object addBook(Book book)
  {
    return null;
  }

  @Override
  public void deleteBook(Book book)
  {

  }

  @Override
  public List<Book> findBookByCriteria(String title, String author)
  {
    return null;
  }
}
