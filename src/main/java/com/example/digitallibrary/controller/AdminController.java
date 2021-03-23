package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.AdminCreationDTO;
import com.example.digitallibrary.dto.AdminDTO;
import com.example.digitallibrary.dto.ChangePasswordDTO;
import com.example.digitallibrary.model.Admin;
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
@RequestMapping("/api/v1/admin")
public class AdminController
{
  private final AdminService adminService;
  private final AuthorsService authorsService;
  private final ReadersService readersService;

  public AdminController(AdminService adminService, AuthorsService authorsService, ReadersService readersService)
  {
    this.adminService = adminService;
    this.authorsService = authorsService;
    this.readersService = readersService;
  }

  @GetMapping
  public List<AdminDTO> getAdmins()
  {
    List<Admin> admins = adminService.getAdmins();
    List<AdminDTO> adminDTOS = new ArrayList<>();
    for (Admin admin : admins) {
      AdminDTO adminDto = new AdminDTO();
      adminDto.setUsername(admin.getUsername());
      adminDto.setFullName(admin.getFullName());
      adminDTOS.add(adminDto);
    }

    return adminDTOS;
  }

  @GetMapping(value = "/{admin}")
  public ResponseEntity<AdminDTO> getAdmin(@PathVariable("admin") String username)
  {
    Optional<Admin> user = adminService.getAdmin(username);
    if (user.isPresent()) {
      AdminDTO adminDTO = new AdminDTO();
      adminDTO.setUsername(username);
      return ResponseEntity.ok(adminDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<?> createAdmin(@Valid @RequestBody() AdminCreationDTO adminCreationDTO) throws URISyntaxException
  {
    if (adminService.getAdmin(adminCreationDTO.getUsername()).isPresent()
        ||authorsService.getAuthor(adminCreationDTO.getUsername()).isPresent()
        ||readersService.getReader(adminCreationDTO.getUsername()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    adminService.createAdmin(adminCreationDTO.getUsername(), adminCreationDTO.getFullName(), adminCreationDTO.getPassword(), adminCreationDTO.getPasswordConfirmation());
    return ResponseEntity.created(new URI("/admins/" + adminCreationDTO.getUsername())).build();
  }

  @DeleteMapping(value = "/{admin}")
  public ResponseEntity<?> disableAdmin(@PathVariable("admin") String userName)
  {
    if (adminService.disableAdmin(userName)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value = "/{admin}/password")
  public @ResponseBody
  ResponseEntity<?> changePassword(@PathVariable("admin") String userName,
                                   @Valid @RequestBody ChangePasswordDTO changePasswordDto,
                                   Principal principal)
  {
    if (!userName.equals(principal.getName()) || !adminService.changePassword(userName, changePasswordDto.oldPassword, changePasswordDto.newPassword)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong password or user name");
    }
    return ResponseEntity.ok().build();
  }

}
