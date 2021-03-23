package com.example.digitallibrary.dto;

public class CommentDTO extends CreateCommentDTO
{
  public String getUserName()
  {
    return userName;
  }

  public CommentDTO setUserName(String userName)
  {
    this.userName = userName;
    return this;
  }

  private String userName;
}
