package com.amadeus.pau.mkt_exp.resource;

import com.amadeus.pau.mkt_exp.pojo.TVSeries;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("tvseries")
public class TVSeriesResource {

    @RestClient
    @Inject
    TVSeriesIDProxy tvSeriesIDProxy;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TVSeries getTVSeriesById(@PathParam("id") int id){
        return tvSeriesIDProxy.getTVSeriesById(id);
    }
}
