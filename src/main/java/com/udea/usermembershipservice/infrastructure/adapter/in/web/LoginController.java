package com.udea.usermembershipservice.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.RestController;

import com.udea.usermembershipservice.aplication.port.in.ILoginUserCase;
import com.udea.usermembershipservice.aplication.useCase.dto.login.LoginDto;
import com.udea.usermembershipservice.aplication.useCase.dto.login.LoginResultDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LoginController {

    public ILoginUserCase loginUsercase;
    public LoginController(ILoginUserCase loginUsercase) {
        this.loginUsercase = loginUsercase;
    }



    @PostMapping("login")
    public ResponseEntity<LoginResultDto> login(@RequestBody LoginDto loginDto) {
        try {
            return ResponseEntity.ok(loginUsercase.login(loginDto));
        } catch (Exception e) {
            throw new RuntimeException("Error logging in", e);
        }
    }
    
}
