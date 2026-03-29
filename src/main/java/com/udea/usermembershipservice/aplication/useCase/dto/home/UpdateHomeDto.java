package com.udea.usermembershipservice.aplication.useCase.dto.home;

import java.util.UUID;

public record UpdateHomeDto(
    UUID idHome,
    String name
) {
}
