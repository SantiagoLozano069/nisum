package com.prueba.tecnica.nisum.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ServiceResponse implements Serializable {
    private int code;
    private String detail;
    private List<String> errorList;
    private String message;
    private GenericResponseObject responseObject;
}
