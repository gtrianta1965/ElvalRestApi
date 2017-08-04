package alert;


import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alerts")
public class AlertService {
    public AlertService() {
        super();
    }
    
    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    //public NotificationType getMsg(){
	public Response getMsg(){
        
        Response r;
		QueryMessage qm = new QueryMessage();
        NotificationType nt = new NotificationType();
        nt = qm.queryMsgToUsers();
        //return nt;

        if (nt != null) {
            r = Response.status(200)
                        .entity(nt)
                        .build();
        } else {
            r = Response.status(400)
                        .entity(nt)
                        .build();
        }
        return r;

    }
}
