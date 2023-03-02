package com.prueba.tecnica.nisum.task.Impl;

import com.prueba.tecnica.nisum.dto.GenericResponseObject;
import com.prueba.tecnica.nisum.dto.UserDto;
import com.prueba.tecnica.nisum.exception.NisumException;
import com.prueba.tecnica.nisum.service.UserService;
import com.prueba.tecnica.nisum.task.UserServiceTask;
import com.prueba.tecnica.nisum.utility.IResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserServiceTaskImpl implements UserServiceTask {

    @Autowired
    private UserService userService;

    @Autowired
    private IResponseDto iResponseDto;


    @Override
    public ResponseEntity createUser(UserDto userDto, HttpHeaders headers) {
        try {
            UserDto userResponse = userService.createUser(userDto, headers.getFirst(HttpHeaders.AUTHORIZATION));
            return iResponseDto.responseCreated(new GenericResponseObject(userResponse));
        } catch (NisumException e) {
            return iResponseDto.responseCostomizerException(e);
        } catch (Exception e) {
            return iResponseDto.responseInternalServerError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity login(String email, String password) {
        try {
            UserDto userResponse = userService.login(email, password);
            return iResponseDto.responseOk(new GenericResponseObject(userResponse));
        } catch (NisumException e) {
            return iResponseDto.responseCostomizerException(e);
        } catch (Exception e) {
            return iResponseDto.responseInternalServerError(e.getMessage());
        }
    }
}
