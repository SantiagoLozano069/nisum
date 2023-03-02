package com.prueba.tecnica.nisum.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    private Integer id;

    @NotNull(message = "El nombre es obligatorio")
    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "El email es obligatorio")
    @NotEmpty(message = "El email es obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email invalido")
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    @NotEmpty(message = "La contraseña es obligatoria")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$", message = "Contraseña invalida")
    private String password;

    @NotNull(message = "La lista de telefono es obligatoria")
    private List<PhoneDto> phones;

    private Date created;

    private Date modified;

    private String token;

    private Boolean isActive;

    private Date lastLogin;


}
