package com.udea.usermembershipservice.infrastructure.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udea.usermembershipservice.aplication.port.in.ICreateHomeUseCase;
import com.udea.usermembershipservice.aplication.useCase.dto.home.CreateHomeDto;
import com.udea.usermembershipservice.aplication.useCase.dto.home.HomeDto;
import com.udea.usermembershipservice.aplication.useCase.dto.home.UpdateHomeDto;

@RestController
public class HomeController {

    private final ICreateHomeUseCase createHomeUseCase;

    public HomeController(ICreateHomeUseCase createHomeUseCase) {
        this.createHomeUseCase = createHomeUseCase;
    }

    @PostMapping("registerHome")
    public ResponseEntity<Void> registerHome(@RequestBody CreateHomeDto createHomeDto) {
        createHomeUseCase.createdHome(createHomeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getHomes")
    public ResponseEntity<List<HomeDto>> getAllHomes() {
        return ResponseEntity.ok(createHomeUseCase.geatAllHomes());
    }

    @GetMapping("getHomeByName")
    public ResponseEntity<HomeDto> getHomeByName(@RequestParam String name) {
        return ResponseEntity.ok(createHomeUseCase.getHomeByName(name));
    }

    @PostMapping("updateHome")
    public ResponseEntity<Void> updateHome(@RequestBody UpdateHomeDto updateHomeDto) {
        createHomeUseCase.updateHome(updateHomeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("deleteHome")
    public ResponseEntity<Void> deleteHome(@RequestParam String name) {
        createHomeUseCase.deleteHome(name);
        return ResponseEntity.ok().build();
    }
}
