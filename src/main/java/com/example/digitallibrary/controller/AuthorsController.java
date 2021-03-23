package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.*;
import com.example.digitallibrary.model.Author;
import com.example.digitallibrary.service.AdminService;
import com.example.digitallibrary.service.AuthorsService;
import com.example.digitallibrary.service.ReadersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorsController
{
  private final AuthorsService authorsService;
  private final AdminService   adminService;
  private final ReadersService readersService;

  public AuthorsController(AuthorsService authorsService, AdminService adminService, ReadersService readersService)
  {
    this.authorsService = authorsService;
    this.adminService = adminService;
    this.readersService = readersService;
  }

  @GetMapping
  public List<AuthorDTO> getAuthors()
  {
    List<Author> authors = authorsService.getAuthors();
    List<AuthorDTO> authorDTOS;
    authorDTOS = new ArrayList<>();
    for (Author author : authors) {
      AuthorDTO authorDTO = new AuthorDTO();
      authorDTO.setUsername(author.getUsername());
      authorDTO.setFullName(author.getFullName());
      authorDTOS.add(authorDTO);
    }

    return authorDTOS;
  }

  @GetMapping(value = "/{author}")
  public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("author") String username)
  {
    Optional<Author> author = authorsService.getAuthor(username);
    if (author.isPresent()) {
      AuthorDTO authorDTO = new AuthorDTO();
      authorDTO.setUsername(username);
      return ResponseEntity.ok(authorDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping(value = "/{author}")
  public ResponseEntity<?> disableAuthor(@PathVariable("author") String userName)
  {
    if (authorsService.disableAuthor(userName)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value = "/{author}/password")
  public @ResponseBody
  ResponseEntity<?> changePassword(@PathVariable("author") String userName,
                                   @Valid @RequestBody ChangePasswordDTO changePasswordDto,
                                   Principal principal)
  {
    if (!userName.equals(principal.getName()) || !authorsService.changePassword(userName, changePasswordDto.oldPassword, changePasswordDto.newPassword)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong password or user name");
    }
    return ResponseEntity.ok().build();
  }
}
