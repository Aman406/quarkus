package com.amadeus.pau.india.resource;

import com.amadeus.pau.india.resource.client.WeatherRestClient;
import io.vertx.core.json.JsonArray;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class IndiaWeatherResource {
    public static final Logger LOGGER = Logger.getLogger(WeatherRestClient.class);

    @Inject
    @RestClient
    WeatherRestClient weatherRestClient;

    @GET
    @Path("/weather/{country}")
    @Fallback(fallbackMethod ="getWeatherByCountryFallback" )
    @Retry(maxRetries = 3)
    @Timeout(1000)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeatherByCountry(@PathParam("country") String country){
        LOGGER.info("Calling...3rd party API for weather forecast....");

        Long startTime = System.currentTimeMillis();
        String response= weatherRestClient.weather_by_country(country);
        Long endTime = System.currentTimeMillis();

        long responseTime = endTime - startTime; // Calculate response time
        LOGGER.info("API Response Time: " + responseTime + " ms");


        JsonObject jsonResponse = Json.createObjectBuilder()
                .add("weather_info", response)
                .add("response_time_ms", responseTime)
                .build();

        return Response.ok(jsonResponse.toString()).build();
    }


    public Response getWeatherByCountryFallback(String country) {
        return Response.ok("{\"message\": \"Website is under maintenance\"}").build();
    }


}
