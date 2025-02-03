package com.amadeus.pau;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/market")
public class MarketResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMarket() {
        List<Market> marketList = Market.listAll();
        return Response.ok(marketList).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveMarket(Market market) {
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
    public Response updateMarket(@PathParam("id") Long id, Market updateMarket) {
        Optional<Market> marketOptional = Market.findByIdOptional(id);
        if (marketOptional.isPresent()) {
            Market dbmarket = marketOptional.get();
            if (Objects.nonNull(updateMarket.getName())) {
                dbmarket.setName(updateMarket.getName());
            }
            if (Objects.nonNull(updateMarket.getCode())) {
                dbmarket.setCode(updateMarket.getCode());
            }
            if (Objects.nonNull(updateMarket.getPin_code())) {
                dbmarket.setPin_code(updateMarket.getPin_code());
            }
            dbmarket.persist();
            if (dbmarket.isPersistent()) {
                return Response.created(URI.create("/market/" + id)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMarketById(@PathParam("id") Long id) {
        boolean isDeleted = Market.deleteById(id);
        if (!isDeleted)
        return Response.noContent().build();
        else
            return  Response.status(Response.Status.BAD_REQUEST).build();

    }

}