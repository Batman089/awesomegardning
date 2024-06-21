package com.company.project.services;

import com.company.project.entity.Plant;
import com.company.project.entity.PlantDto;
import com.company.project.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantRepository plantRepository;


    @Transactional()
    public List<PlantDto> findAllPlants() {
        return plantRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PlantDto createPlant(PlantDto plantDto) {
        Plant plant = convertToEntity(plantDto);
        Plant savedPlant = plantRepository.save(plant);
        return convertToDto(savedPlant);
    }

    public PlantDto getPlant(UUID id) {
        Plant plant = plantRepository.findById(id).orElse(null);
        return plant != null ? convertToDto(plant) : null;
    }

    public PlantDto updatePlant(PlantDto plantDto) {
        Plant plant = convertToEntity(plantDto);
        Plant updatedPlant = plantRepository.save(plant);
        return convertToDto(updatedPlant);
    }

    public void deletePlant(UUID id) {
        plantRepository.deleteById(id);
    }

    private PlantDto convertToDto(Plant plant) {
        return new PlantDto(plant.getId(), plant.getName(), plant.getDescription());
    }

    private Plant convertToEntity(PlantDto plantDto) {
        return new Plant(plantDto.getId(), plantDto.getName(), plantDto.getDescription());
    }
}
