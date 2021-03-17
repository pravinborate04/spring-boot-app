package com.borate.pravin.pim.controller;

import com.borate.pravin.pim.config.AuthUserDetailService;
import com.borate.pravin.pim.config.AuthUserDetails;
import com.borate.pravin.pim.dto.AuthenticationRequest;
import com.borate.pravin.pim.dto.AuthenticationResponse;
import com.borate.pravin.pim.helper.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Pravin Borate
 * 14/03/21
 */
@RestController
public class AuthController implements Constants {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthUserDetailService userDetailService;

    @PostMapping(SEPARATOR + LOGIN)
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest req) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        AuthUserDetails myUserDetail = userDetailService.loadUserByUsername(req.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = Base64.getEncoder().encodeToString((req.getUsername() + ":" + req.getPassword()).getBytes(StandardCharsets.UTF_8));
        return ResponseEntity.ok(new AuthenticationResponse(token, myUserDetail.getUsername(),myUserDetail.getUser().getRole()));
    }

}
