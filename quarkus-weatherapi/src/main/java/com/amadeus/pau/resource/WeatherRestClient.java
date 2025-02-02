package com.amadeus.pau.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Random;
import org.jboss.logging.Logger;



@Path("/api")
public class WeatherRestClient {
    public static final Logger LOGGER = Logger.getLogger(WeatherRestClient.class);


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("weather_by_country/{country}")
    public Response weatherByCountry(@PathParam("country") String country){
        LOGGER.info("calling Weather services for country: "+country);

        // enable disable for testing timeout features
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Response.ok("weather of "+country +": "+new Random().nextInt(50)).build();

    }
}
