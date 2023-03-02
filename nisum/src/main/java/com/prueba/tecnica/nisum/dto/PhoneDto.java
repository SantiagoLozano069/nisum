package com.prueba.tecnica.nisum.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto implements Serializable {

    private String number;

    private Integer cityCode;

    private Integer countryCode;
}
