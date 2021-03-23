package com.example.digitallibrary.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class Author
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @NotNull
  @Column(name = "username", unique = true, nullable = false)
  private String  username;

  @NotNull
  @Column(name = "full_name", nullable = false)
  private String  fullName;

  @NotNull
  @Column(name = "password", nullable = false)
  private String  password;

  @Column(name = "rating", nullable = false)
  private Double rating;

  @Column(name = "book_count", nullable = false)
  private Integer numberOfBooks;

  @Column(name = "is_enabled", nullable = false)
  private boolean isEnabled;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getFullName()
  {
    return fullName;
  }

  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public Double getRating()
  {
    return rating;
  }

  public void setRating(Double rating)
  {
    this.rating = rating;
  }

  public Integer getNumberOfBooks()
  {
    return numberOfBooks;
  }

  public void setNumberOfBooks(Integer numberOfBooks)
  {
    this.numberOfBooks = numberOfBooks;
  }

  public boolean isEnabled()
  {
    return isEnabled;
  }

  public void setEnabled(boolean enabled)
  {
    isEnabled = enabled;
  }
}
