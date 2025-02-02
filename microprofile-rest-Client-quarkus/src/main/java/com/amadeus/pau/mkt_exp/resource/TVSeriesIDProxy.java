package com.amadeus.pau.mkt_exp.resource;


import com.amadeus.pau.mkt_exp.pojo.TVSeries;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/shows")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TVSeriesIDProxy {

    //https://api.tvmaze.com/shows/1

    @GET
    @Path("/{id}")
    TVSeries getTVSeriesById(@PathParam("id") int id);
}
