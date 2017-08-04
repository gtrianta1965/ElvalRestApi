package com.elval.alert;


import java.sql.SQLException;

import java.util.logging.Logger;

import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/alerts")
public class AlertService {
    private static final Logger LOGGER = Logger.getLogger( AlertService.class.getName() );
    
    public AlertService() {
        super();
    }

    //public NotificationType getMsg(){
    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getActiveNotifications() {
        Response r;
        QueryMessage qm = new QueryMessage();
        NotificationType nt = new NotificationType();
        nt = qm.queryActiveNotifications();
        
        LOGGER.info("getActiveNotifications called");
        if (nt.getMessage() != null) {
            r = Response.status(200).entity(nt).build();
        } else {
            r = Response.status(400).entity(nt).build();
        }
        return r;

    }
}
