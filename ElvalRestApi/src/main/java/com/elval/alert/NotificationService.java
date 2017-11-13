package com.elval.alert;


import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/notification")
public class NotificationService {
    private static final Logger LOGGER = Logger.getLogger( NotificationService.class.getName() );
    
    public NotificationService() {
        super();
    }

    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getActiveNotifications() {
        Response r;
        CacheControl cc = new CacheControl();
        cc.setMaxAge(1);
        QueryNotifications qm = new QueryNotifications();
        NotificationMessage nt = new NotificationMessage();
        nt = qm.queryActiveNotifications();
        
        LOGGER.info("getActiveNotifications called");
        if (nt.getMessage() != null) {
            long current = new Date().getTime();
            Date expires = new Date(current + 10);
            r = Response.status(200).cacheControl(cc).entity(nt).build();
        } else {
            if (nt.getError() != null) {
                r = Response.status(400).entity(nt).build();
            }
            else {
                r = Response.status(404).entity(nt).build();
            }
        }
        return r;

    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNotification(NotificationMessage notification) {
        System.out.println("Call createNotification (POST)" + notification.getMessage());
        NotificationMessage nt = new NotificationMessage();
        nt = notification;
        return Response.status(200).entity(nt).build();
        
    }
}
