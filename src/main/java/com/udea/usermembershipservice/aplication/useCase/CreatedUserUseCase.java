package com.udea.usermembershipservice.aplication.useCase;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.udea.usermembershipservice.aplication.port.in.ICreateUserUseCase;
import com.udea.usermembershipservice.aplication.port.out.IPasswordEncoderPort;
import com.udea.usermembershipservice.aplication.port.out.IPersonRepositoryPort;
import com.udea.usermembershipservice.aplication.useCase.dto.person.CreatePersonDto;
import com.udea.usermembershipservice.aplication.useCase.dto.person.PersonDto;
import com.udea.usermembershipservice.domain.model.Person;
import com.udea.usermembershipservice.aplication.useCase.exception.PersistenceException;
import com.udea.usermembershipservice.aplication.useCase.exception.SearchException;

public class CreatedUserUseCase implements ICreateUserUseCase{

    IPasswordEncoderPort passwordEncoderport;
    IPersonRepositoryPort userRepositoryPort;

    LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("America/Bogota"));
    
    public CreatedUserUseCase(IPersonRepositoryPort userRepository, IPasswordEncoderPort passwordEncoder) {
        this.passwordEncoderport = passwordEncoder;
        this.userRepositoryPort = userRepository;
    }

    @Override
    public void createdUser(CreatePersonDto createUserDto) {
        try {
            if(userRepositoryPort.getUserByEmail(createUserDto.email()) == null) {
                
            String passwordEncoder = passwordEncoderport.encode(createUserDto.password());
            Person person = Person.create(createUserDto.name(), createUserDto.lastName(), createUserDto.email(), passwordEncoder, createdAt, true);
            userRepositoryPort.saveUser(person);
            }else {
                throw new RuntimeException("Person with this email already exists");
            }
        } catch (Exception e) {
            throw new PersistenceException("Error saving person", e);
        }

    }

    @Override
    public List<PersonDto> geatAllUsers() {
        try {
            List<Person> persons = userRepositoryPort.getAllUsers();
            List<PersonDto> personDtos = persons.stream().map(person -> new PersonDto(
                person.getName(),
                person.getLastName(),
                person.getEmail()
            )).toList();
            return personDtos;
        } catch (Exception e) {
            throw new SearchException("Error getting all users", e);
        }
    }

    @Override
    public PersonDto getUserByEmail(String email) {
        try {
            Person person = userRepositoryPort.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            PersonDto personDto = new PersonDto(person.getName(), person.getLastName(), person.getEmail());
            return personDto;
        } catch (Exception e) {
            throw new SearchException("Error getting user by email", e);
        }
    }

    @Override
    public void updateUser(CreatePersonDto createUserDto) {
        /*try {
            if(userRepositoryPort.getUserByEmail(createUserDto.email()) == null) {
                Person existingPerson = userRepositoryPort.getUserById(createUserDto.idPerson()).orElseThrow(() -> new RuntimeException("User not found"));
                String passwordEncoder = passwordEncoderport.encode(createUserDto.password());
                Person person = Person.create(existingPerson.getIdPerson(), createUserDto.name(), createUserDto.lastName(), createUserDto.email(), passwordEncoder, existingPerson.getcreatedAt(), true);

                userRepositoryPort.updateUser(person);
            }else {
                throw new RuntimeException("Person with this email already exists");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error updating person", e);
        }*/
    }

    @Override
    public void deleteUser(String email){
        try {
            userRepositoryPort.deleteUser(email);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting person", e);
        }
    }

}
