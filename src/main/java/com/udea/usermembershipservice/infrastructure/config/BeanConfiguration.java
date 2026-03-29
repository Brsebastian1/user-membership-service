package com.udea.usermembershipservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udea.usermembershipservice.aplication.port.in.ICreateUserUseCase;
import com.udea.usermembershipservice.aplication.port.in.ILoginUserCase;
import com.udea.usermembershipservice.aplication.port.out.IPasswordEncoderPort;
import com.udea.usermembershipservice.aplication.port.out.IPersonRepositoryPort;
import com.udea.usermembershipservice.aplication.useCase.CreatedUserUseCase;
import com.udea.usermembershipservice.aplication.useCase.LoginUserCase;
import com.udea.usermembershipservice.infrastructure.adapter.out.persistence.adapter.PersonPersistenceAdapter;
import com.udea.usermembershipservice.infrastructure.adapter.out.persistence.mapper.PersonPersistenceMapper;
import com.udea.usermembershipservice.infrastructure.adapter.out.persistence.repository.SpringDataJpaRepository;
import com.udea.usermembershipservice.infrastructure.adapter.out.security.PasswordEncoderAdapter;


@Configuration
public class BeanConfiguration {


    @Bean
    public PersonPersistenceMapper personPersistenceMapper() {
        return new PersonPersistenceMapper();
    }

    @Bean
    public IPersonRepositoryPort personRepositoryPort(
            SpringDataJpaRepository springDataJpaRepository,
            PersonPersistenceMapper personPersistenceMapper
    ) {
        return new PersonPersistenceAdapter(springDataJpaRepository, personPersistenceMapper);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort() {
        return new PasswordEncoderAdapter();
    }

    @Bean
    public ICreateUserUseCase createUserUseCase(
            IPersonRepositoryPort personRepositoryPort,
            IPasswordEncoderPort passwordEncoderPort
    ) {
        return new CreatedUserUseCase(personRepositoryPort, passwordEncoderPort);
    }

    @Bean
    public ILoginUserCase loginUserCase(
            IPersonRepositoryPort personRepositoryPort,
            IPasswordEncoderPort passwordEncoderPort
    ) {
        return new LoginUserCase(personRepositoryPort, passwordEncoderPort);
    }
}
