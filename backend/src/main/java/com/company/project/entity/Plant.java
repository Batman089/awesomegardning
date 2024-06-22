package com.company.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@Table(name = "PLANTS")
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    @Schema(description = "Unique identifier of the plant", example = "123e4567-e89b-12d3-a456-426614174000")
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @JsonProperty("plantId")

    private UUID id;

    @Schema(description = "Name of the plant", example = "Monstera")
    @Column(nullable = false)
    @Getter
    @Setter
    @Size(min=1, max = 255, message = "Name must be between 1 and 255 characters")
    @JsonProperty("plantName")
    private String name = "No name available";

    @Schema(description = "Description of the plant", example = "This plant is a tropical plant that requires a lot of sunlight and water. It is a great plant for beginners.")
    @Column(nullable = true)
    @Getter
    @Setter
    @Size(max = 4096, message = "Description must be less than 4096 characters")
    @JsonProperty("plantDescription")
    private String description = "No description available";
}