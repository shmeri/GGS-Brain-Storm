package com.example.digitallibrary.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "libraries")
public class Library
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @ManyToOne(targetEntity = Reader.class)
  @JoinColumn(name = "reader_id", referencedColumnName = "id")
  private Reader reader;

  @OneToMany
  @JoinTable(name = "libraries",
      joinColumns = @JoinColumn(name = "reader_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  private List<Book> books;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public Reader getReader()
  {
    return reader;
  }

  public void setReader(Reader reader)
  {
    this.reader = reader;
  }

  public List<Book> getBooks()
  {
    return books;
  }

  public void setBooks(List<Book> books)
  {
    this.books = books;
  }
}
