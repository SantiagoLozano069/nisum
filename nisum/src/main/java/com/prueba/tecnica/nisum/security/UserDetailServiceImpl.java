package com.prueba.tecnica.nisum.security;

import com.prueba.tecnica.nisum.entity.UserEntity;
import com.prueba.tecnica.nisum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity adminEntity = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("El usuario con el email " + email +" no existe"));
        return new UserDetailsImpl(adminEntity);
    }
}
