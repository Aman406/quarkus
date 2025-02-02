package com.amadeus.pau.mkt_exp.resource;

import com.amadeus.pau.mkt_exp.pojo.TVSeries;
import io.vertx.core.json.JsonArray;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("tvseries")
public class TVSeriesResource {


    @Inject
    @RestClient
    TVSeriesIDProxy tvSeriesIDProxy;


    //http://localhost:8080/microprofile-rest-client-fault-tolerance/tvseries/1
    @GET
    @Path("/{id}")
    @Fallback(fallbackMethod = "getTVSeriesByIdFallback")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTVSeriesById(@PathParam("id") int id){

        return Response.ok(tvSeriesIDProxy.getTVSeriesById(id)).build();
    }



    public Response getTVSeriesByIdFallback(int id){
        return Response.ok("{\"message\": \"Website is under maintenance\"}").build();
    }


    //http://localhost:8080/microprofile-rest-client-fault-tolerance/tvseries/person?q=lauren
    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getTVSeriesByPerson(@QueryParam("q") String personName){
        return tvSeriesIDProxy.getTVSeriesByPersonName(personName);
    }
}
