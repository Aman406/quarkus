package com.amadeus.pau.india.resource;

import com.amadeus.pau.india.resource.client.WeatherRestClient;
import io.vertx.core.json.JsonArray;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.time.temporal.ChronoUnit;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class IndiaWeatherResource {
    public static final Logger LOGGER = Logger.getLogger(WeatherRestClient.class);

    @Inject
    @RestClient
    WeatherRestClient weatherRestClient;

    @GET
    @Path("/weather/{country}")
    @Fallback(fallbackMethod = "getWeatherByCountryFallback")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 10,
            delayUnit = ChronoUnit.SECONDS
    )
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeatherByCountry(@PathParam("country") String country) {
        LOGGER.info("Calling...3rd party API for weather forecast....");

        return weatherRestClient.weather_by_country(country);

    }


    public Response getWeatherByCountryFallback(String country) {
        return Response.ok("Website is under maintenance").build();
    }


}
