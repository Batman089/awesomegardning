package com.company.project.repository;

import com.company.project.entity.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends CrudRepository<Plant, Integer> {
}
