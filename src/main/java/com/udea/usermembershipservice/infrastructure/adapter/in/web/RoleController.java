package com.udea.usermembershipservice.infrastructure.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udea.usermembershipservice.aplication.port.in.ICreateRoleUseCase;
import com.udea.usermembershipservice.aplication.useCase.dto.role.CreateRoleDto;
import com.udea.usermembershipservice.aplication.useCase.dto.role.RoleDto;
import com.udea.usermembershipservice.aplication.useCase.dto.role.UpdateRoleDto;

@RestController
public class RoleController {

    private final ICreateRoleUseCase createRoleUseCase;

    public RoleController(ICreateRoleUseCase createRoleUseCase) {
        this.createRoleUseCase = createRoleUseCase;
    }

    @PostMapping("registerRole")
    public ResponseEntity<Void> registerRole(@RequestBody CreateRoleDto createRoleDto) {
        createRoleUseCase.createdRole(createRoleDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getRoles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(createRoleUseCase.geatAllRoles());
    }

    @GetMapping("getRoleByName")
    public ResponseEntity<RoleDto> getRoleByName(@RequestParam String name) {
        return ResponseEntity.ok(createRoleUseCase.getRoleByName(name));
    }

    @PostMapping("updateRole")
    public ResponseEntity<Void> updateRole(@RequestBody UpdateRoleDto updateRoleDto) {
        createRoleUseCase.updateRole(updateRoleDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("deleteRole")
    public ResponseEntity<Void> deleteRole(@RequestParam String name) {
        createRoleUseCase.deleteRole(name);
        return ResponseEntity.ok().build();
    }
}
