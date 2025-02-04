package com.amadeus.pau;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Path("/market")
public class MarketResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMarket() {
        List<Market> marketList = Market.listAll();
        return Response.ok(marketList).build();
    }


    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveMarket(Market market) {
        //market.persist();;
        Market.persist(market);
        if (market.isPersistent()) {
            return Response.created(URI.create("/market/" + market.id)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateMarket(@PathParam("id") Long id, Market updateMarket) {
        Optional<Market> existingMarketOpt = Market.findByIdOptional(id);

        if (existingMarketOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Market with ID " + id + " not found")
                    .build();
        }

        Market existingMarket = existingMarketOpt.get();

        // Set the ID explicitly to ensure it's an update
        updateMarket.id = id;

        if (Objects.nonNull(updateMarket.getName())) {
            existingMarket.setName(updateMarket.getName());
        }
        if (Objects.nonNull(updateMarket.getCode())) {
            existingMarket.setCode(updateMarket.getCode());
        }
        if (updateMarket.getPin_code() > 0) {
            existingMarket.setPin_code(updateMarket.getPin_code());
        }

        existingMarket.persist();

        return existingMarket.isPersistent()
                ? Response.ok(existingMarket).build()
                : Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Failed to update Market with ID " + id)
                .build();
    }




    @DELETE
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMarketById(@PathParam("id") Long id) {
        boolean isDeleted = Market.deleteById(id);
        if (!isDeleted)
            return Response.noContent().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarketById(@PathParam("id") Long id) {
        Market market = Market.findById(id);
        return Response.ok(market).build();

    }

}