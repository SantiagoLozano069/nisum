package com.prueba.tecnica.nisum.utility;

import org.springframework.http.HttpStatus;

public interface IException {

    HttpStatus getStatus();
    String getMessage();
}
