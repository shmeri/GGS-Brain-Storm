package com.example.digitallibrary.model;

import javax.persistence.*;

@Entity
@Table(name = "readers")
public class Reader
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "username", unique = true, nullable = false)
  private String  username;

  @Column(name = "full_name", nullable = false)
  private String  fullName;

  @Column(name = "password", nullable = false)
  private String  password;

  @Column(name = "is_enabled", nullable = false)
  private boolean isEnabled;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getFullName()
  {
    return fullName;
  }

  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public boolean isEnabled()
  {
    return isEnabled;
  }

  public void setEnabled(boolean enabled)
  {
    isEnabled = enabled;
  }
}
