package com.example.digitallibrary.config;

import com.example.digitallibrary.model.Author;
import com.example.digitallibrary.service.AuthorsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorDetailsServiceImpl implements UserDetailsService
{
  private AuthorsService authorsService;

  public AuthorDetailsServiceImpl(AuthorsService authorsService)
  {
    this.authorsService = authorsService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    Optional<Author> optionalUser = authorsService.getAuthor(username);

    if (!optionalUser.isPresent()) {
      throw new UsernameNotFoundException("User not found.");
    }
    Author author = optionalUser.get();
    return org.springframework.security.core.userdetails.User.withUsername(username)
        .password(author.getPassword())
        .roles("USER")
        .build();
  }
}
