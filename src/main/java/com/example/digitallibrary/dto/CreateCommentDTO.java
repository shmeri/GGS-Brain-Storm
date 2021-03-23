package com.example.digitallibrary.dto;

import java.time.LocalDateTime;

public class CreateCommentDTO
{
  private String  content;
  private Integer rating;

  public Integer getRating()
  {
    return rating;
  }

  public void setRating(Integer rating)
  {
    this.rating = rating;
  }

  private LocalDateTime time;

  public CreateCommentDTO()
  {
  }

  public String getContent()
  {
    return content;
  }

  public CreateCommentDTO setContent(String content)
  {
    this.content = content;
    return this;
  }

  public LocalDateTime getTime()
  {
    return time;
  }

  public CreateCommentDTO setTime(LocalDateTime time)
  {
    this.time = time;
    return this;
  }
}
