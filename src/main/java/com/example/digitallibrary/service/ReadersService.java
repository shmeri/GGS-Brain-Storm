package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Admin;
import com.example.digitallibrary.model.Reader;

import java.util.List;
import java.util.Optional;

public interface ReadersService
{
  List<Reader> getReaders();

  Optional<Reader> getReader(String username);

  Reader createReader(String username, String fullName, String password, String passwordConfirmation);

  boolean disableReader(String userName);

  boolean changePassword(String userName, String oldPassword, String newPassword);
}
