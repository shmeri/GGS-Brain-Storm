package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.*;
import com.example.digitallibrary.model.Reader;
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
@RequestMapping("/api/v1/reader")
public class ReadersController
{
  private final ReadersService readersService;
  private final AdminService adminService;
  private final AuthorsService authorsService;

  public ReadersController(ReadersService readersService, AdminService adminService, AuthorsService authorsService)
  {
    this.readersService = readersService;
    this.adminService = adminService;
    this.authorsService = authorsService;
  }

  @GetMapping
  public List<ReaderDTO> getReaders()
  {
    List<Reader> readers = readersService.getReaders();
    List<ReaderDTO> readerDTOS;
    readerDTOS = new ArrayList<>();
    for (Reader reader : readers) {
      ReaderDTO readerDTO = new ReaderDTO();
      readerDTO.setUsername(reader.getUsername());
      readerDTO.setFullName(reader.getFullName());
      readerDTOS.add(readerDTO);
    }

    return readerDTOS;
  }

  @GetMapping(value = "/{reader}")
  public ResponseEntity<ReaderDTO> getReader(@PathVariable("reader") String username)
  {
    Optional<Reader> reader = readersService.getReader(username);
    if (reader.isPresent()) {
      ReaderDTO readerDTO = new ReaderDTO();
      readerDTO.setUsername(username);
      return ResponseEntity.ok(readerDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<?> createReader(@Valid @RequestBody() RegistrationDTO registrationDTO) throws URISyntaxException
  {
    if (readersService.getReader(registrationDTO.getUsername()).isPresent()
        ||adminService.getAdmin(registrationDTO.getUsername()).isPresent()
        ||authorsService.getAuthor(registrationDTO.getUsername()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    readersService.createReader(registrationDTO.getUsername(), registrationDTO.getFullName(), registrationDTO.getPassword(), registrationDTO.getPasswordConfirmation());
    return ResponseEntity.created(new URI("/readers/" + registrationDTO.getUsername())).build();
  }

  @DeleteMapping(value = "/{reader}")
  public ResponseEntity<?> disableReader(@PathVariable("reader") String userName)
  {
    if (readersService.disableReader(userName)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value = "/{reader}/password")
  public @ResponseBody
  ResponseEntity<?> changePassword(@PathVariable("reader") String userName,
                                   @Valid @RequestBody ChangePasswordDTO changePasswordDto,
                                   Principal principal)
  {
    if (!userName.equals(principal.getName()) || !readersService.changePassword(userName, changePasswordDto.oldPassword, changePasswordDto.newPassword)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong password or user name");
    }
    return ResponseEntity.ok().build();
  }
}
