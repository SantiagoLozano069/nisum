package com.prueba.tecnica.nisum.service.impl;

import com.prueba.tecnica.nisum.dto.UserDto;
import com.prueba.tecnica.nisum.entity.UserEntity;
import com.prueba.tecnica.nisum.exception.NisumException;
import com.prueba.tecnica.nisum.repository.PhoneRepository;
import com.prueba.tecnica.nisum.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserDto userDto;
    private UserEntity userEntity;
    private String token;


    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setName("Santiago Lozano");
        userDto.setEmail("slozanomu@gmail.com");
        userDto.setPassword("1aAAs123@");
        userDto.setIsActive(true);
        userDto.setLastLogin(new Date());

        userEntity = new UserEntity();
        userEntity.setId(100);
        userEntity.setName("Santiago Lozano");
        userEntity.setEmail("slozanomu@gmail.com");
        userEntity.setPassword("nisum");

        token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXN1bUBuaXN1bS5jb20iLCJleHAiOjE2Nzc3ODYxOTEsImxhc3RMb2dpbiI6MTY3Nzc4NjAxMTM2OH0.H_HNz36IncTIpFiBgPtSNxEWoKzwBcZcac2man7jvPM";
    }

    @Test
    void createUser_when_throw_exception() {
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(userEntity));
        assertThrows(NisumException.class, () ->{
            UserDto user =  userServiceImpl.createUser(userDto, token);
        });
    }
}