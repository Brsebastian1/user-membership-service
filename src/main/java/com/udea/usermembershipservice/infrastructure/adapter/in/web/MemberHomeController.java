package com.udea.usermembershipservice.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.RestController;

import com.udea.usermembershipservice.aplication.port.in.ICreatedMemberHome;
import com.udea.usermembershipservice.aplication.useCase.dto.mermberHome.CreatedMemberHomeDto;
import com.udea.usermembershipservice.aplication.useCase.dto.mermberHome.MemberHomeDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@Tag(name = "Miembros de Hogar", description = "Operaciones para asociar personas a un hogar, consultar su vinculacion y eliminarla.")
public class MemberHomeController {

    private final ICreatedMemberHome createdMemberHome;

    public MemberHomeController(ICreatedMemberHome createdMemberHome) {
        this.createdMemberHome = createdMemberHome;
    }

    @Operation(summary = "Asociar miembro a hogar", description = "Relaciona una persona con un hogar y un rol determinado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Miembro asociado al hogar correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos para asociar el miembro"),
        @ApiResponse(responseCode = "404", description = "No se encontro la persona, el hogar o el rol indicado")
    })
    @PostMapping("save/memberHome")
    public void saveMemberHome(@RequestBody CreatedMemberHomeDto createdMemberHomeDto) {
        try {
            createdMemberHome.createdMemberHome(createdMemberHomeDto.gmail(), createdMemberHomeDto.rol(), createdMemberHomeDto.nameHogar());
        } catch (Exception e) {
            throw new RuntimeException("Error saving member home", e);
        }
        
    }

    @Operation(summary = "Eliminar miembro de hogar", description = "Elimina la asociacion entre una persona y un hogar.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Asociacion eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "No se encontro la asociacion solicitada")
    })
    @GetMapping("delete/memberHome")
    public void deleteMemberHome(@RequestParam String nameHome, @RequestParam String gmail) {
        try {
            createdMemberHome.deleteMemberHome(nameHome, gmail);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting member home", e);
        }
    }

    @Operation(summary = "Consultar hogar de un miembro", description = "Obtiene la vinculacion de un usuario con su hogar a partir del correo electronico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vinculacion consultada correctamente"),
        @ApiResponse(responseCode = "404", description = "No se encontro informacion del miembro en un hogar")
    })
    @GetMapping("get/memberHome")
    public MemberHomeDto getMemberHome(@RequestParam String gmail) {
        try {
            return createdMemberHome.getMemberHome(gmail);
        } catch (Exception e) {
            throw new RuntimeException("Error getting member home", e);
        }
    }
        
}
