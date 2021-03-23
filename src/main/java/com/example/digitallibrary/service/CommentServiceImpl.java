package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService
{
  @Override
  public List<Comment> getAllComments(Integer id)
  {
    return null;
  }

  @Override
  public void deleteComments(Integer bookId)
  {

  }

  @Override
  public void addComment(Comment comment)
  {

  }

  @Override
  public Optional<Comment> getComment(Integer commentId)
  {
    return Optional.empty();
  }

  @Override
  public void deleteComment(Integer commentId)
  {

  }
}
