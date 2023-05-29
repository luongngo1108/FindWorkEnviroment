package com.oose.jobportal.auth;

import com.oose.jobportal.models.entities.Role;
import com.oose.jobportal.models.entities.User;
import com.oose.jobportal.models.entities.UserDetail;
import com.oose.jobportal.models.mappers.UserDetailMapper;
import com.oose.jobportal.repositories.UserRepo;
import com.oose.jobportal.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var userDetail = UserDetail.builder()
                .name(request.getName())
                .gmail(request.getGmail())
//                Hash password
                .password(passwordEncoder.encode(request.getPassword()))
//                Need to change
                .role(Role.USER)
                .build();

//        Mapping from user detail --> user
        User user = UserDetailMapper.mappingToUser(userDetail);
        user.setAccountnumber(request.getPhonenumber());
        user.setPhonenumber(request.getPhonenumber());
        user.setLocation(request.getLocation());
//        Save to database
        userRepo.save(user);
//        Create Token from user detail was create
        var jwtToken = jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        Bug in this line
//        What is this feature
//        Call to AuthenticationManager Bean in ApplicationConfig
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getGmail(),
//                            request.getPassword()
//                    )
//            );
//        } catch (BadCredentialsException e) {
//            logger.error(e.getMessage());
//        }

//        Get user and mapper to user detail
        var user = userRepo.findByGmail(request.getGmail());
        var userDetail = UserDetailMapper.mappingToUserDetail(user);

//        Create token from user detail
        var jwtToken = jwtService.generateToken(userDetail);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
