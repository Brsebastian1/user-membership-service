package com.udea.usermembershipservice.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.RestController;

import com.udea.usermembershipservice.aplication.port.in.ICreateUserUseCase;
import com.udea.usermembershipservice.aplication.port.in.ILoginUserCase;
import com.udea.usermembershipservice.aplication.useCase.dto.CreatePersonDto;
import com.udea.usermembershipservice.aplication.useCase.dto.PersonDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RegisterPersonController {

    public ICreateUserUseCase createUserUseCase;
    public ILoginUserCase loginUsercase;

    public RegisterPersonController(ICreateUserUseCase createUserUseCase, ILoginUserCase loginUsercase) {
        this.createUserUseCase = createUserUseCase;
        this.loginUsercase = loginUsercase;
    }

    @PostMapping("register")
    public ResponseEntity<Void> registerPerson(@RequestBody CreatePersonDto createPersonDto) {
        try {
            createUserUseCase.createdUser(createPersonDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("Error registering person", e);
        }
    }

    @GetMapping("getUsers")
    public ResponseEntity<List<PersonDto>> getAllUsers() {
        try {
            return ResponseEntity.ok(createUserUseCase.geatAllUsers());
        } catch (Exception e) {
            throw new RuntimeException("Error getting all users", e);
        }
    }

    @GetMapping("GetUserByEmail")
    public ResponseEntity<PersonDto> getUserByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(createUserUseCase.getUserByEmail(email));
        } catch (Exception e) {
            throw new RuntimeException("Error getting user by email", e);
        }
    }

    @PostMapping("updateUser")
    public ResponseEntity<Void> updateUser(@RequestBody CreatePersonDto createPersonDto) {
        try {
            createUserUseCase.updateUser(createPersonDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("Error updating user", e);
        }
    }


    @PostMapping("deleteUser")   
    public ResponseEntity<Void> deleteUser(@RequestParam String email) {
        try {
            createUserUseCase.deleteUser(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }    
}
