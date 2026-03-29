package com.udea.usermembershipservice.aplication.useCase.dto.mermberHome;

public record MemberHomeDto(
    String homeId,
    String personId,
    String name,
    String last_name,
    String email, 
    Boolean active
) {
} 
