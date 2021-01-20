package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class NibssTSARevenueCollectionService {
		
		@POST
	    @Path("/notification")
	    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML ,MediaType.TEXT_PLAIN })
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN})
	    public void tsaCollectionNotification(String inputString) {
	    }
		
		
}
