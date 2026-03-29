package com.udea.usermembershipservice.aplication.useCase.dto;

import java.util.UUID;

public record PersonDto(
    String name,
    String lastName,
    String email
) {
}
