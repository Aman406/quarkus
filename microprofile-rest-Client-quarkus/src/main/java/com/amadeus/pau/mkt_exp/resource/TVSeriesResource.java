package com.amadeus.pau.mkt_exp.resource;

import com.amadeus.pau.mkt_exp.pojo.TVSeries;
import io.vertx.core.json.JsonArray;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("tvseries")
public class TVSeriesResource {


    @Inject
    @RestClient
    TVSeriesIDProxy tvSeriesIDProxy;


    @Inject
    @RestClient
    TVSeriesPersonProxy tvSeriesPersonProxy;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TVSeries getTVSeriesById(@PathParam("id") int id){
        return tvSeriesIDProxy.getTVSeriesById(id);
    }


    @GET
    @Path("/person")  //   /tvseries/person?q=lauren
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getTVSeriesByPerson(@QueryParam("q") String personName){
        return tvSeriesPersonProxy.getTVSeriesByPerson(personName);
    }
}
