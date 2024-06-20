package com.company.project.rest;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RestApi {
    public static final String BASE_PATH = "/api";

    public static final String PLANTS = BASE_PATH + "/plants";
    public static final String PLANT_DATAILS = PLANTS + "/{PLANT_ID}";
}
