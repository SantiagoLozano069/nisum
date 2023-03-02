package com.prueba.tecnica.nisum.controller;

import com.prueba.tecnica.nisum.dto.UserDto;
import com.prueba.tecnica.nisum.exception.ArgumentNotValidException;
import com.prueba.tecnica.nisum.task.UserServiceTask;
import com.prueba.tecnica.nisum.utility.IResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceTask userServiceTask;

    @Autowired
    private IResponseDto iResponseDto;

    @PostMapping
    private ResponseEntity createUser(@RequestHeader HttpHeaders headers, @Valid @RequestBody UserDto userDto,
                                      BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return iResponseDto.responseBadRequest(ArgumentNotValidException.handleValidationExceptions(resultRequest));
        }
        return userServiceTask.createUser(userDto, headers);
    }

    @GetMapping("/login")
    private ResponseEntity login(@RequestParam(name = "email", defaultValue = "nisum@nisum.com") String email,
                                 @RequestParam(name = "password", defaultValue = "nisum") String password){
        return userServiceTask.login(email, password);
    }
}
