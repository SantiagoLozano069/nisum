package com.prueba.tecnica.nisum.utility;

import com.prueba.tecnica.nisum.dto.GenericResponseObject;
import com.prueba.tecnica.nisum.dto.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseDto implements IResponseDto {

    @Override
    public ResponseEntity responseCreated(GenericResponseObject responseDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ServiceResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .detail(HttpStatus.CREATED.getReasonPhrase())
                        .responseObject(responseDto)
                        .build());
    }
    @Override
    public ResponseEntity responseInternalServerError(String errorMessage){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ServiceResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .detail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message(errorMessage)
                        .build());
    }

    @Override
    public ResponseEntity responseCostomizerException(IException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(ServiceResponse.builder()
                        .code(exception.getStatus().value())
                        .detail(exception.getStatus().getReasonPhrase())
                        .message(exception.getMessage())
                        .build());
    }

    @Override
    public ResponseEntity responseBadRequest(List<String> errorList) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .detail(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .errorList(errorList)
                        .build());
    }

    @Override
    public ResponseEntity responseOk(GenericResponseObject responseDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ServiceResponse.builder()
                        .code(HttpStatus.OK.value())
                        .detail(HttpStatus.OK.getReasonPhrase())
                        .responseObject(responseDto)
                        .build());
    }
}
