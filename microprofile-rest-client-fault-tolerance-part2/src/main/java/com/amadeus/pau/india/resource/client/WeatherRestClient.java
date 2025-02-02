package com.amadeus.pau.india.resource.client;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:5055")
@Path("/api")
public interface WeatherRestClient {


    //http://localhost:5055/api/weather_by_country/india
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("weather_by_country/{country}")
    String weather_by_country (@PathParam("country") String weather_by_country);

}
