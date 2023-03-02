package com.prueba.tecnica.nisum.enums;

import lombok.Getter;

@Getter
public enum MensajeError {

    CORRE_YA_REGISTRADO("¡Error! Correo ya registrado."),
    NO_EXISTE_USUARIO("¡Error! No existe usuario"),
    CREDENCIALES_INVALIDAS("¡Error! Credenciales invalidas, por favor rectifique sus credenciales.");

    String mensaje;
    MensajeError(String mensaje){
        this.mensaje = mensaje;
    }


}
