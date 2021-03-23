package com.example.digitallibrary.dto;

import com.example.digitallibrary.model.Reader;

public class RegistrationDTO extends ReaderDTO
{
  private String password;
  private String passwordConfirmation;

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPasswordConfirmation()
  {
    return passwordConfirmation;
  }

  public void setPasswordConfirmation(String passwordConfirmation)
  {
    this.passwordConfirmation = passwordConfirmation;
  }
}
