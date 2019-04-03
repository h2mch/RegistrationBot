package ch.zuehlke.camp.graal;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.zuehlke.camp.graal.entity.Registration;

@Path("/notification")
public class NotificationResource {

    @Inject
    private Store store;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response register(Registration registration) {
        try {
            store.putNotification(registration);
            return Response.accepted("https://t.me/ZuehlkeRegistrationBot").build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.serverError().build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification() {
        Collection<Registration> registrations = store.getNotifications();
        return Response.ok(registrations).build();

    }
}
