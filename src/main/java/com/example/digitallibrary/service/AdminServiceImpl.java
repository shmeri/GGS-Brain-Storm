package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Admin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService
{
  @Override
  public List<Admin> getAdmins()
  {
    return Collections.emptyList();
  }

  @Override
  public Optional<Admin> getAdmin(String username)
  {
    return Optional.empty();
  }

  @Override
  public Admin createAdmin(String username, String fullName, String password, String passwordConfirmation)
  {
    return null;
  }

  @Override
  public boolean disableAdmin(String userName)
  {
    return false;
  }

  @Override
  public boolean changePassword(String userName, String oldPassword, String newPassword)
  {
    return false;
  }

}
