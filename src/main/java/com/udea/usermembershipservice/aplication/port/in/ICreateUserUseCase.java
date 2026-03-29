package com.udea.usermembershipservice.aplication.port.in;

import java.util.List;

import com.udea.usermembershipservice.aplication.useCase.dto.person.CreatePersonDto;
import com.udea.usermembershipservice.aplication.useCase.dto.person.PersonDto;

public interface ICreateUserUseCase {
    public void createdUser(CreatePersonDto createPersonDto);
    public List<PersonDto> geatAllUsers();
    public PersonDto getUserByEmail(String email);
    public void deleteUser(String email);
}
