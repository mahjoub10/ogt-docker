package com.show.car.rest;

import com.show.car.dto.UserDTO;
import com.show.car.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class UserResource {

    private Logger log = LoggerFactory.getLogger(UserResource.class);

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public ResponseEntity<UserDTO> account() throws  Exception{
        log.info("Request to get the account of current user");

        UserDTO user = userService.getAccount();
        return  ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDTO) throws Exception {
        log.info("Request to register new user");
        userService.register(userDTO);

    }
}
