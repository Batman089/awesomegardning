package com.company.project.controllers;

import com.company.project.entity.Plant;
import com.company.project.entity.PlantDto;
import com.company.project.rest.RestApi;
import com.company.project.services.PlantService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
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
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),}, extensions = {
            @Extension(name = "awesomegardening-authorization", properties = {
                    @ExtensionProperty(name = "resoource-id-type", value = "dynamic"),
                    @ExtensionProperty(name = "resource-id", value = "plantsId"),
                    @ExtensionProperty(name = "resource-id-description", value = "The unique identifier for a plant"),
                    @ExtensionProperty(name = "resource-type", value = "plant"),
                    @ExtensionProperty(name = "resource-type-description", value = "The type of object plant"),
                    @ExtensionProperty(name = "action", value = "[\"project.settings.read\"]", parseValue = true)
            }),
            @Extension(name = "awesomegardening-signer", properties = {
                    @ExtensionProperty(name = "audience", value = "[\"plant-servie\"]")
            })
    })
    public List<Plant> getPlants() {
        return plantService.findAllPlants();
    }

    @GetMapping(RestApi.PLANTS + "/{id}")
    @Operation(description = "View information for specific Plant", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the information of specific plant"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "404", description = "The requested Plant could not be found", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE))}, extensions = {
            @Extension(name = "awesomegardening-authorization", properties = {
                    @ExtensionProperty(name = "resoource-id-type", value = "dynamic"),
                    @ExtensionProperty(name = "resource-id", value = "plantsId"),
                    @ExtensionProperty(name = "resource-id-description", value = "The unique identifier for a plant"),
                    @ExtensionProperty(name = "resource-type", value = "plant"),
                    @ExtensionProperty(name = "resource-type-description", value = "The type of object plant"),
                    @ExtensionProperty(name = "action", value = "[\"project.settings.read\"]", parseValue = true)
            }),
            @Extension(name = "awesomegardening-signer", properties = {
                    @ExtensionProperty(name = "audience", value = "[\"plant-servie\"]")
            })
    })
    public Plant getPlant(
            @Parameter(description = "This is in form of UUID") @PathVariable UUID id) {
        return plantService.getPlant(id);
    }

    @PostMapping(RestApi.PLANTS)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a specific Plant", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created a plant"),
            @ApiResponse(responseCode = "400", description = "Please provide a valid request body and check the request method for creating a plant", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE))}, extensions = {
            @Extension(name = "awesomegardening-authorization", properties = {
                    @ExtensionProperty(name = "resoource-id-type", value = "dynamic"),
                    @ExtensionProperty(name = "resource-id", value = "plantsId"),
                    @ExtensionProperty(name = "resource-id-description", value = "The unique identifier for a plant"),
                    @ExtensionProperty(name = "resource-type", value = "plant"),
                    @ExtensionProperty(name = "resource-type-description", value = "The type of object plant"),
                    @ExtensionProperty(name = "action", value = "[\"project.settings.read\"]", parseValue = true)
            }),
            @Extension(name = "awesomegardening-signer", properties = {
                    @ExtensionProperty(name = "audience", value = "[\"plant-servie\"]")
            })
    })
    public Plant createPlant(
            @Parameter (description= "This is a PlantDto (without Id).") @RequestBody @Valid PlantDto plantDto) {
        return plantService.createPlant(plantDto);
    }

    @PutMapping(RestApi.PLANTS+ "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Update an existed Plant", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully updated plant"),
            @ApiResponse(responseCode = "400", description = "Please provide a valid request body and check the request method for updating a plant", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "404", description = "The requested Plant could not be found", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE))}, extensions = {
            @Extension(name = "awesomegardening-authorization", properties = {
                    @ExtensionProperty(name = "resoource-id-type", value = "dynamic"),
                    @ExtensionProperty(name = "resource-id", value = "plantsId"),
                    @ExtensionProperty(name = "resource-id-description", value = "The unique identifier for a plant"),
                    @ExtensionProperty(name = "resource-type", value = "plant"),
                    @ExtensionProperty(name = "resource-type-description", value = "The type of object plant"),
                    @ExtensionProperty(name = "action", value = "[\"project.settings.read\"]", parseValue = true)
            }),
            @Extension(name = "awesomegardening-signer", properties = {
                    @ExtensionProperty(name = "audience", value = "[\"plant-servie\"]")
            })
    })
    public Plant updatePlant(
            @Parameter (description= "This is a PlantDto.") @RequestBody @Valid PlantDto plantDto,
            @Parameter (description= "This is in form of UUID.") @PathVariable UUID id){
        return plantService.updatePlant(plantDto, id);
    }

    @DeleteMapping(RestApi.PLANTS + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete specific Plant", responses = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the specific plant"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "404", description = "The requested Plant could not be found", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE)),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Problem.class), mediaType = MediaTypes.PROBLEM_VALUE))}, extensions = {
            @Extension(name = "awesomegardening-authorization", properties = {
                    @ExtensionProperty(name = "resoource-id-type", value = "dynamic"),
                    @ExtensionProperty(name = "resource-id", value = "plantsId"),
                    @ExtensionProperty(name = "resource-id-description", value = "The unique identifier for a plant"),
                    @ExtensionProperty(name = "resource-type", value = "plant"),
                    @ExtensionProperty(name = "resource-type-description", value = "The type of object plant"),
                    @ExtensionProperty(name = "action", value = "[\"project.settings.read\"]", parseValue = true)
            }),
            @Extension(name = "awesomegardening-signer", properties = {
                    @ExtensionProperty(name = "audience", value = "[\"plant-servie\"]")
            })
    })
    public void deletePlant(
            @Parameter (description= "This is in form of UUID.") @PathVariable UUID id) {
        plantService.deletePlant(id);
    }
}
