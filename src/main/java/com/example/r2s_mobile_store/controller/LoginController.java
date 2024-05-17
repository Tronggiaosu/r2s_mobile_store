package com.example.r2s_mobile_store.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.r2s_mobile_store.constant.ApiUrlConstant;
import com.example.r2s_mobile_store.dto.LoginDTO;
import com.example.r2s_mobile_store.dto.ResponseLoginDTO;
import com.example.r2s_mobile_store.util.JwtUtils;

@RestController
@RequestMapping(ApiUrlConstant.LOGIN)
public class LoginController {
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();
                responseLoginDTO.setUsername(loginDTO.getUsername());
                responseLoginDTO.setToken("Bearer " + JwtUtils.generateToken(loginDTO.getUsername()));
                List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .toList();
                responseLoginDTO.setRole(roles.get(0));
                return new ResponseEntity<>(responseLoginDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Đăng nhập thất bại", HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
