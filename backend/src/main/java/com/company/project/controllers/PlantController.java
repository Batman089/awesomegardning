package com.company.project.controllers;

import com.company.project.entity.PlantDto;
import com.company.project.rest.RestApi;
import com.company.project.services.PlantService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.spring.common.MediaTypes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class PlantController {

    private final PlantService plantService;

    @GetMapping(RestApi.PLANTS)
    @Operation(description = "View a list of available Plants", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of plants"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "404", description = "The requested Plant could not be found", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),}, extensions = {
            @Extension(name = "awesomegardening", properties = {
                    @ExtensionProperty(name = "resoource-id-type", value = "dynamic"),
                    @ExtensionProperty(name = "resource-id", value = "plantsId"),
                    @ExtensionProperty(name = "resource-id-description", value = "The unique identifier for a plant"),
                    @ExtensionProperty(name = "resource-type", value = "plant"),
                    @ExtensionProperty(name = "resource-type-description", value = "The type of object plant"),
                    @ExtensionProperty(name = "action", value = "[\"project.settings.read\"]", parseValue = true)
            })
    }
    )
    public ResponseEntity<List<PlantDto>> getPlants() {
        List<PlantDto> plantList = plantService.findAllPlants();
        return ResponseEntity.ok(plantList);
    }

    @PostMapping(RestApi.PLANTS)
    public ResponseEntity<PlantDto> createPlant(@RequestBody @Valid PlantDto plantDto) {
        PlantDto createdPlant = plantService.createPlant(plantDto);
        return ResponseEntity.ok(createdPlant);
    }
    /*
    @ApiOperation(value = "View a list of available plants", response = List.class)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "View a list of available Plants", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of plants"),
            @ApiResponse(responseCode = "400", description = "Please provide a valid request body and check the request method", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "The requested Plant could not be found", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE))}, extensions = {
            @Extension(name = "awesomegardening", properties = {
                    @ExtensionProperty(name = "resoource-id-type", value = "dynamic"),
                    @ExtensionProperty(name = "resource-id", value = "plantsId"),
                    @ExtensionProperty(name = "resource-id-description", value = "The unique identifier for a plant"),
                    @ExtensionProperty(name = "resource-type", value = "plant"),
                    @ExtensionProperty(name = "resource-type-description", value = "The type of object plant"),
                    @ExtensionProperty(name = "action", value = "[\"project.settings.read\"]", parseValue = true)
            })
    }
    )

     */
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
