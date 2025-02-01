package com.amadeus.pau.mkt_exp;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;


@Path("/mkt_expansion")
public class MktResource {

    List<String> mktList = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> getMarketList() {
        return mktList;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void addMarketList(String MktName) {
        mktList.add(MktName);
    }
}
