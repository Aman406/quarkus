package com.amadeus.pau.mkt_exp.resource;

import com.amadeus.pau.mkt_exp.pojo.Market;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/mkt_exp")
public class MarketExpansionResource {

    List<Market> mktList = new ArrayList<>();



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarketList() {
        return Response.ok(mktList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMarket(Market market) {
        mktList.add(market);
        return Response.ok(market).build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMarket(@PathParam("id") int id, Market marketToUpdate) {
        mktList=mktList.stream().map(
                market ->{
                    if (market.getId() ==id){
                        return marketToUpdate;
                    }else{
                        return  market;
                    }
                }).collect(Collectors.toList());

        return Response.ok(mktList).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteMarket(@PathParam("id") int marketToDelete) {
        Optional<Market> optionalMarket =
                mktList.stream()
                        .filter(market -> market.getId() == marketToDelete).findFirst();
        if (optionalMarket.isPresent()) {
            mktList.remove(optionalMarket.get());
            return Response.ok(mktList).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getMarketById(@PathParam("id") int id) {
          Optional<Market> optionalMarket=mktList.stream().filter(market ->
                  market.getId()==id).findFirst();
            if (optionalMarket.isPresent()) {
                return Response.ok(optionalMarket.get()).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }



}

