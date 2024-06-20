package com.company.project.repository;

import com.company.project.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlantRepository extends JpaRepository<Plant, UUID> {
}
