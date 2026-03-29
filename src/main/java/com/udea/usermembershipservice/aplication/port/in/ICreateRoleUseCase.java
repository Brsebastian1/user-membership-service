package com.udea.usermembershipservice.aplication.port.in;

import java.util.List;

import com.udea.usermembershipservice.aplication.useCase.dto.role.CreateRoleDto;
import com.udea.usermembershipservice.aplication.useCase.dto.role.RoleDto;
import com.udea.usermembershipservice.aplication.useCase.dto.role.UpdateRoleDto;

public interface ICreateRoleUseCase {

    void createdRole(CreateRoleDto createRoleDto);

    List<RoleDto> geatAllRoles();

    RoleDto getRoleByName(String name);

    void updateRole(UpdateRoleDto updateRoleDto);

    void deleteRole(String name);
}
