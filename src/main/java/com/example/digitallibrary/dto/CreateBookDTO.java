package com.example.digitallibrary.dto;

import com.example.digitallibrary.model.Genre;
import org.springframework.web.bind.annotation.GetMapping;

public class CreateBookDTO
{
  private String  title;
  private Integer year;

  public Genre getGenre()
  {
    return genre;
  }

  public void setGenre(Genre genre)
  {
    this.genre = genre;
  }

  private Genre genre;

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public Integer getYear()
  {
    return year;
  }

  public void setYear(Integer year)
  {
    this.year = year;
  }
}
