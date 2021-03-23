package com.example.digitallibrary.dto;

public class BookDTO extends CreateBookDTO
{
  private Integer id;
  private String  author;
  private Integer countComments;
  private Double  rating;
  private Integer numberOfLibraries;

  public Integer getNumberOfLibraries()
  {
    return numberOfLibraries;
  }

  public void setNumberOfLibraries(Integer numberOfLibraries)
  {
    this.numberOfLibraries = numberOfLibraries;
  }

  public Integer getCountComments()
  {
    return countComments;
  }

  public void setCountComments(Integer countComments)
  {
    this.countComments = countComments;
  }

  public Double getRating()
  {
    return rating;
  }

  public void setRating(Double rating)
  {
    this.rating = rating;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }
}
