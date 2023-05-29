package com.oose.jobportal.controllers.client;

import com.oose.jobportal.auth.AuthenticationRequest;
import com.oose.jobportal.auth.AuthenticationResponse;
import com.oose.jobportal.auth.AuthenticationService;
import com.oose.jobportal.constants.Domain;
import com.oose.jobportal.models.entities.User;
import com.oose.jobportal.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client/account")
@CrossOrigin(Domain.CROSS_ORIGIN)
@RequiredArgsConstructor
public class CAccountController {
    private final UserService userService;
    private final AuthenticationService service;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestBody AuthenticationRequest request) {
        if (userService.checkAccount(request.getGmail(), request.getPassword())) {
            return ResponseEntity.ok(service.authenticate(request));
        } else {
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token("")
                    .build());
        }
    }

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(userService.findUserbyGmail(user.getGmail())) {
            return new ResponseEntity<String>("email already", HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            if(userService.saveUser(user)) {
                return new ResponseEntity<String>("success", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("false", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
