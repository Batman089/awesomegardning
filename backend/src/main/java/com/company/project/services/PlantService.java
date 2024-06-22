package com.company.project.services;

import com.company.project.entity.Plant;
import com.company.project.entity.PlantDto;
import com.company.project.exceptions.ObjectNotFoundException;
import com.company.project.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public List<Plant> findAllPlants() {
        return plantRepository.findAll();
    }

    public Plant createPlant(PlantDto plantDto) {
        Plant plant = new Plant();
        convertToEntity(plantDto, plant);
        return plantRepository.save(plant);
    }

    public Plant getPlant(UUID plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);
        if (plant.isPresent()) {
            return plant.get();
        }else {
            throw new ObjectNotFoundException(plantId, Plant.class);
        }
    }

    public Plant updatePlant(PlantDto plantDto, UUID plantId) {
        Optional<Plant> plantOptional = plantRepository.findById(plantId);
        if (plantOptional.isPresent()){
            convertToEntity(plantDto, plantOptional.get());
            return plantOptional.get();
        } else {
            throw new ObjectNotFoundException(plantId, Plant.class);
        }
    }

    public void deletePlant(UUID plantId) {
        try {
            plantRepository.deleteById(plantId);
        }  catch (Exception e) {
            throw new ObjectNotFoundException(plantId, Plant.class);
        }

    }

    private void convertToEntity(PlantDto plantDto, Plant plant) {
        if (plantDto.getName() != null) {
            plant.setName(plantDto.getName());
        }
        if (plantDto.getDescription() != null) {
            plant.setDescription(plantDto.getDescription());
        }
    }
}
