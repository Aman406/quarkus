package com.amadeus.pau.mkt_exp;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/mkt_expansion")
public class MarketExpansionResource {

        List<String> mktList=new ArrayList<>();

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public Response getMarketList(){
            return Response.ok(mktList).build();
        }
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        public Response addMarketList(String mktName){
            mktList.add(mktName);
            return Response.ok(mktName).build();
        }

    @PUT
    @Path("/{oldMktName}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMarketName(@PathParam("oldMktName") String oldMktName,
                                     @QueryParam("newMktName") String newMktName){
        mktList=mktList.stream().map( mkt ->{
                if (mkt.equals(oldMktName)){
                    return newMktName;
                }else {
                    return mkt;
                }
            }).toList();
            return Response.ok(mktList).build();
    }

    /***
     *  @PUT
     *     @Path("/{oldMktName}")
     *     @Consumes(MediaType.TEXT_PLAIN)
     *     @Produces(MediaType.TEXT_PLAIN)
     *     public Response updateMarketName(@PathParam("oldMktName") String oldMktName,
     *                                      @QueryParam("newMktName") String newMktName) {
     *         // Ensure new name is provided
     *         if (newMktName == null || newMktName.trim().isEmpty()) {
     *             return Response.status(Response.Status.BAD_REQUEST)
     *                     .entity("New market name cannot be empty")
     *                     .build();
     *         }
     *
     *         // Update the market list
     *         boolean updated = false;
     *         for (int i = 0; i < mktList.size(); i++) {
     *             if (mktList.get(i).equals(oldMktName)) {
     *                 mktList.set(i, newMktName);
     *                 updated = true;
     *                 break;
     *             }
     *         }
     *
     *         if (!updated) {
     *             return Response.status(Response.Status.NOT_FOUND)
     *                     .entity("Market name not found")
     *                     .build();
     *         }
     *
     *         return Response.ok("Market name updated successfully").build();
     *     }*/

    @DELETE
    @Path("{marketToDelete}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMarket(@PathParam("marketToDelete") String marketName) {
        boolean isRemoved = mktList.remove(marketName);

        if (isRemoved) {
            return Response.ok("Market deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Market name not found")
                    .build();
        }
    }

}

