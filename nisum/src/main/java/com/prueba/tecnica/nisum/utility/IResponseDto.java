package com.prueba.tecnica.nisum.utility;

import com.prueba.tecnica.nisum.dto.GenericResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IResponseDto {
    ResponseEntity responseCreated(GenericResponseObject responseDto);

    ResponseEntity responseInternalServerError(String errorMessage);

    ResponseEntity responseCostomizerException(IException exception);

    ResponseEntity responseBadRequest(List<String> errorList);

    ResponseEntity responseOk(GenericResponseObject responseDto);

}
