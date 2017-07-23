package com.show.car.service;

import com.show.car.repository.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CustomUserDetailService implements UserDetailsService {

    private static Logger log = LoggerFactory.getLogger(CustomUserDetailService.class) ;

    private UserRepository repository ;

    public CustomUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login)  {

        log.info("Authenticating the user having email :  '{}'", login);
        return  repository.findByLoginAndActivatedIsTrue(login)
        .orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
    }
}
