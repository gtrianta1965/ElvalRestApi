package v1_0;


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
    public NotificationType getMsg() throws SQLException{
        
        QueryMessage qm = new QueryMessage();
        NotificationType nt = new NotificationType();
        nt = qm.queryMsgToUsers();
        return nt;

//        return Response.status(200)
//                       .entity(output)
//                       .build();

    }
}
