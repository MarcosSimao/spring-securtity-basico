package com.app.seguranca.service;

import com.app.seguranca.model.CustomUsersDetails;
import com.app.seguranca.model.Users;
import com.app.seguranca.repository.UsersRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostumUsersService implements UserDetailsService {
    @Autowired
    private  UsersRespository usersRespository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers= usersRespository.findByName(username);
        optionalUsers
                .orElseThrow(()->new UsernameNotFoundException("Nao encontrado"));
        return optionalUsers
                .map(CustomUsersDetails::new).get();
    }
}
