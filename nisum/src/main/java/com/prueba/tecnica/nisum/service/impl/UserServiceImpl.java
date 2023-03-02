package com.prueba.tecnica.nisum.service.impl;

import com.prueba.tecnica.nisum.dto.PhoneDto;
import com.prueba.tecnica.nisum.dto.UserDto;
import com.prueba.tecnica.nisum.entity.PhoneEntity;
import com.prueba.tecnica.nisum.entity.UserEntity;
import com.prueba.tecnica.nisum.enums.MensajeError;
import com.prueba.tecnica.nisum.exception.NisumException;
import com.prueba.tecnica.nisum.repository.PhoneRepository;
import com.prueba.tecnica.nisum.repository.UserRepository;
import com.prueba.tecnica.nisum.security.TokenUtils;
import com.prueba.tecnica.nisum.security.UserDetailsImpl;
import com.prueba.tecnica.nisum.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto, String token) throws NisumException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new NisumException(MensajeError.CORRE_YA_REGISTRADO.getMensaje(), HttpStatus.OK);
        } else {
            userDto.setToken(token);
            UserEntity userPersisted = userRepository.save(this.mapUserDtoToUserEntity(userDto));
            List<PhoneEntity> phoneEntityListPersisted = phoneRepository.saveAll(this.mapPhoneListEntity(userDto, userPersisted));
            return this.mapResponseUserDto(userDto, userPersisted, phoneEntityListPersisted);
        }
    }

    private UserEntity mapUserDtoToUserEntity(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return  userEntity;
    }

    private List<PhoneEntity> mapPhoneListEntity(UserDto userDto, UserEntity userPersisted) {
        return userDto.getPhones().stream().map(phoneDto -> modelMapper.map(phoneDto, PhoneEntity.class))
                .map(phoneEntity -> {
                    phoneEntity.setUser(userPersisted);
                    return phoneEntity;
                })
                .collect(Collectors.toList());
    }

    private UserDto mapResponseUserDto(UserDto userDto, UserEntity userPersisted, List<PhoneEntity> phoneEntityListPersited){
        UserDto userDtoResponse = modelMapper.map(userPersisted, UserDto.class);
        userDtoResponse.setPassword((userDto.getPassword()));
        userDtoResponse.setIsActive(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        userDtoResponse.setLastLogin(new Date((Long) TokenUtils.getClaimsToken(userDto.getToken().replace("Bearer ", "")).get("lastLogin")));
        userDtoResponse.setPhones(phoneEntityListPersited.stream().map(phoneEntity -> modelMapper.map(phoneEntity, PhoneDto.class))
                .collect(Collectors.toList()));
        return userDtoResponse;
    }

    @Override
    public UserDto login(String email, String password) throws NisumException {
        BCryptPasswordEncoder  passwordEncoder = new BCryptPasswordEncoder();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);
        if(passwordEncoder.matches(password,userDetails.getPassword())){
            String token = "Bearer " + TokenUtils.createToken(userDetails.getUsername(), userDetails.getEmail());
            return UserDto.builder()
                    .email(userDetails.getEmail())
                    .token(token)
                    .build();
        }else{
            throw new NisumException(MensajeError.CREDENCIALES_INVALIDAS.getMensaje(), HttpStatus.OK);
        }
    }
}
