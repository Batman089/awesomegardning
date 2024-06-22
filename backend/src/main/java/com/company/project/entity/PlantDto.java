package com.company.project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantDto {

    @Size(min=1, max = 255, message = "Name must be between 1 and 255 characters")
    @Schema(description = "Name of the plant",
            defaultValue = "No name available",
            example = "Monstera")
    private String name;

    @Size(max = 4096, message = "Description must be less than 4096 characters")
    @Schema(description = "Description of the plant",
            defaultValue = "No description available",
            example = "This plant is a tropical plant that requires a lot of sunlight and water. It is a great plant for beginners.")
    private String description;
}