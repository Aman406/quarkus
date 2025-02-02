package com.amadeus.pau.mkt_exp.resource;


import com.amadeus.pau.mkt_exp.pojo.TVSeries;
import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TVSeriesIDProxy {

    //https://api.tvmaze.com/shows/1
    @GET
    @Path("/{id}")
    TVSeries getTVSeriesById(@PathParam("id") int id);

    //https://api.tvmaze.com/search/people?q=lauren
    @GET
    @Path("search/people")
    JsonArray getTVSeriesByPersonName(@QueryParam("q") String personName);
}
