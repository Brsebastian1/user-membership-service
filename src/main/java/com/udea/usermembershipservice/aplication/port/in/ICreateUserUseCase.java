package com.udea.usermembershipservice.aplication.port.in;

import java.util.List;

import com.udea.usermembershipservice.aplication.useCase.dto.CreatePersonDto;
import com.udea.usermembershipservice.aplication.useCase.dto.PersonDto;

public interface ICreateUserUseCase {
    public void createdUser(CreatePersonDto createPersonDto);
    public List<PersonDto> geatAllUsers();
    public PersonDto getUserByEmail(String email);
    public void updateUser(CreatePersonDto createPersonDto);
    public void deleteUser(String email);
}
