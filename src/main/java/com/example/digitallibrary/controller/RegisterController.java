package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.AuthorCreationDTO;
import com.example.digitallibrary.service.AdminService;
import com.example.digitallibrary.service.AuthorsService;
import com.example.digitallibrary.service.ReadersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController
{
  private final AuthorsService authorsService;
  private final AdminService   adminService;
  private final ReadersService readersService;


  public RegisterController(AuthorsService authorsService, AdminService adminService, ReadersService readersService)
  {
    this.authorsService = authorsService;
    this.adminService = adminService;
    this.readersService = readersService;
  }

  @GetMapping("/registerAuth")
  private String registerAuth()
  {
    return "/registerAuth";
  }

  @PostMapping("/registerAuth")
  public String createAuthor(@ModelAttribute() AuthorCreationDTO authorCreationDTO)
  {
    if (!authorCreationDTO.getPassword().equals(authorCreationDTO.getPasswordConfirmation())) {
      return "redirect:/error/registerAuthPassNotMatch.html";
    }
    if (authorsService.getAuthor(authorCreationDTO.getUsername()).isPresent()
        || adminService.getAdmin(authorCreationDTO.getUsername()).isPresent()
        || readersService.getReader(authorCreationDTO.getUsername()).isPresent()) {
      return "redirect:/error/registerUserIsPresent.html";
    }
    this.authorsService.createAuthor(authorCreationDTO.getUsername(), authorCreationDTO.getFullName(),
        authorCreationDTO.getPassword());
    return "redirect:/login";
  }
  @GetMapping("/registerReader")
  private String registerReader()
  {
    return "/registerReader";
  }

  @GetMapping("/login")
  public String getLoginPage() {
    return "/login";
  }
}