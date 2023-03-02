package com.prueba.tecnica.nisum.service;

import com.prueba.tecnica.nisum.dto.UserDto;
import com.prueba.tecnica.nisum.exception.NisumException;

public interface UserService {

    UserDto createUser(UserDto userDto, String token)  throws NisumException;

    UserDto login(String email, String password)  throws NisumException;
}
