package com.udea.usermembershipservice.aplication.useCase;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import com.udea.usermembershipservice.aplication.port.in.ICreateHomeUseCase;
import com.udea.usermembershipservice.aplication.port.out.IHomeRepositoryPort;
import com.udea.usermembershipservice.aplication.useCase.dto.home.CreateHomeDto;
import com.udea.usermembershipservice.aplication.useCase.dto.home.HomeDto;
import com.udea.usermembershipservice.aplication.useCase.dto.home.UpdateHomeDto;
import com.udea.usermembershipservice.domain.model.Home;

public class CreatedHomeUseCase implements ICreateHomeUseCase {

    private final IHomeRepositoryPort homeRepositoryPort;

    public CreatedHomeUseCase(IHomeRepositoryPort homeRepositoryPort) {
        this.homeRepositoryPort = homeRepositoryPort;
    }

    @Override
    public void createdHome(CreateHomeDto createHomeDto) {
        try {
            if (homeRepositoryPort.getHomeByName(createHomeDto.name()).isPresent()) {
                throw new RuntimeException("Home with this name already exists");
            }

            Home home = Home.create(
                UUID.randomUUID(),
                createHomeDto.name(),
                LocalDateTime.now(ZoneId.of("America/Bogota"))
            );

            homeRepositoryPort.saveHome(home);
        } catch (Exception e) {
            throw new RuntimeException("Error saving home", e);
        }
    }

    @Override
    public List<HomeDto> geatAllHomes() {
        try {
            return homeRepositoryPort.getAllHomes().stream()
                .map(home -> new HomeDto(home.getIdHome(), home.getName(), home.getCreatedAt()))
                .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error getting all homes", e);
        }
    }

    @Override
    public HomeDto getHomeByName(String name) {
        try {
            Home home = homeRepositoryPort.getHomeByName(name)
                .orElseThrow(() -> new RuntimeException("Home not found"));

            return new HomeDto(home.getIdHome(), home.getName(), home.getCreatedAt());
        } catch (Exception e) {
            throw new RuntimeException("Error getting home by name", e);
        }
    }

    @Override
    public void updateHome(UpdateHomeDto updateHomeDto) {
        try {
            Home home = homeRepositoryPort.getHomeById(updateHomeDto.idHome())
                .orElseThrow(() -> new RuntimeException("Home not found"));

            homeRepositoryPort.getHomeByName(updateHomeDto.name())
                .filter(existingHome -> !existingHome.getIdHome().equals(updateHomeDto.idHome()))
                .ifPresent(existingHome -> {
                    throw new RuntimeException("Home with this name already exists");
                });

            home.changeName(updateHomeDto.name());
            homeRepositoryPort.updateHome(home);
        } catch (Exception e) {
            throw new RuntimeException("Error updating home", e);
        }
    }

    @Override
    public void deleteHome(String name) {
        try {
            homeRepositoryPort.deleteHome(name);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting home", e);
        }
    }
}
