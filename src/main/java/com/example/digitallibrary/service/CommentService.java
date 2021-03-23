package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService
{
  List<Comment> getAllComments(Integer id);
  void deleteComments(Integer bookId);

  void addComment(Comment comment);

  Optional<Comment> getComment(Integer commentId);

  void deleteComment(Integer commentId);
}
