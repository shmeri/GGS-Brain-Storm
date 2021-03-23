package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Admin;
import com.example.digitallibrary.model.Reader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadersServiceImpl implements ReadersService
{
  @Override
  public List<Reader> getReaders()
  {
    return null;
  }

  @Override
  public Optional<Reader> getReader(String username)
  {
    return Optional.empty();
  }

  @Override
  public Reader createReader(String username, String fullName, String password, String passwordConfirmation)
  {
    return null;
  }

  @Override
  public boolean disableReader(String userName)
  {
    return false;
  }

  @Override
  public boolean changePassword(String userName, String oldPassword, String newPassword)
  {
    return false;
  }
}
