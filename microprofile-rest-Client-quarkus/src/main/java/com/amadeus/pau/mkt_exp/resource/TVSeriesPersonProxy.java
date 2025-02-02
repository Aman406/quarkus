package com.amadeus.pau.mkt_exp.resource;

import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/search/people")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TVSeriesPersonProxy {
    //https://api.tvmaze.com/search/people?q=lauren


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    JsonArray getTVSeriesByPerson(@QueryParam("q") String personName);
}
