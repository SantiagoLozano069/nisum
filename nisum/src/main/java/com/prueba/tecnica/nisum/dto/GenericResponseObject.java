package com.prueba.tecnica.nisum.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponseObject<T>{
    private T objectDto;

    public GenericResponseObject(T objectDto){
        this.objectDto = objectDto;
    }

    public T getResponse(){return this.objectDto;}
}
