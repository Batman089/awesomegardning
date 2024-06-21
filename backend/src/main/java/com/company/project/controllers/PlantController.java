package com.company.project.controllers;

import com.company.project.entity.PlantDto;
import com.company.project.rest.RestApi;
import com.company.project.services.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class PlantController {

    private final PlantService plantService;


    @GetMapping(RestApi.PLANTS)
    public ResponseEntity<List<PlantDto>> getPlants() {
        List<PlantDto> plantList = plantService.findAllPlants();
        return ResponseEntity.ok(plantList);
    }

    @PostMapping(RestApi.PLANTS)
    public ResponseEntity<PlantDto> createPlant(@RequestBody @Valid PlantDto plantDto) {
        PlantDto createdPlant = plantService.createPlant(plantDto);
        return ResponseEntity.ok(createdPlant);
    }

    @GetMapping(RestApi.PLANTS + "/{id}")
    public ResponseEntity<PlantDto> getPlant(@PathVariable UUID id) {
        PlantDto plantDto = plantService.getPlant(id);
        if (plantDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plantDto);
    }

    @PutMapping(RestApi.PLANTS)
    public ResponseEntity<PlantDto> updatePlant(@RequestBody @Valid PlantDto plantDto) {
        PlantDto updatedPlant = plantService.updatePlant(plantDto);
        return ResponseEntity.ok(updatedPlant);
    }

    @DeleteMapping(RestApi.PLANTS + "/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable UUID id) {
        plantService.deletePlant(id);
        return ResponseEntity.noContent().build();
    }
}
