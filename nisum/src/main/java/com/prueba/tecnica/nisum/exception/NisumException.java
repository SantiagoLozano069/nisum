package com.prueba.tecnica.nisum.exception;

import com.prueba.tecnica.nisum.utility.IException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NisumException extends RuntimeException implements IException {

    private HttpStatus status;

    public NisumException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }



}
