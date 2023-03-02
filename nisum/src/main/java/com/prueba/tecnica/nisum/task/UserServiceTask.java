package com.prueba.tecnica.nisum.task;

import com.prueba.tecnica.nisum.dto.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface UserServiceTask {

    ResponseEntity createUser(UserDto userDto, HttpHeaders headers);

    ResponseEntity login(String email, String password);

}
