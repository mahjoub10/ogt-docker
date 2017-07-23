package com.show.car.service;

import com.show.car.domain.jpa.Authority;
import com.show.car.domain.jpa.User;
import com.show.car.dto.UserDTO;
import com.show.car.mapper.CarMapper;
import com.show.car.repository.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository ;

    private CarMapper mapper ;

    private MailService mailService ;

    public UserService(UserRepository userRepository, CarMapper mapper, MailService mailService ) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.mailService = mailService ;
    }

    public UserDTO getAccount() throws  Exception {
        log.info("Getting current user");
        User currentUser = getCurrentUser();
        return  mapper.fromUserToUserDTO(currentUser);
    }

    public User getCurrentUser() throws  Exception{

        String login = getCurrentlogin();
        return  userRepository.findByLogin(login)
                .orElseThrow(() -> new Exception("no user ha been found"));

    }

    public  void register(UserDTO user) throws  Exception {
        log.info("Registering new user");
        this.checkLogin(user.getLogin());
        User newUser = mapper.fromUserDtoToUser(user);
        newUser.setAuthority(Authority.ROLE_USER);
        mailService.sendActivationEMail(user);
        userRepository.save(newUser);

    }

    private void checkLogin(String login)throws  Exception{

       boolean exist = userRepository.findByLogin(login).isPresent();
       if(exist){
           throw  new Exception("User with login "+login+" already exists");
       }
    }

    private  String getCurrentlogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }


}
