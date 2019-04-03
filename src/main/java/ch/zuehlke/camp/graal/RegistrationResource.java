package ch.zuehlke.camp.graal;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.zuehlke.camp.graal.entity.Registration;

@Path("/registration")
public class RegistrationResource {

    @Inject
    private TelegramStore telegramStore;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getRegistration(@PathParam("id") String id) {
        Registration registration = telegramStore.get(id);
        return Response.ok(registration).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegistrations() {
        Collection<Registration> registrations = telegramStore.getRegistrations();
        return Response.ok(registrations).build();
    }
}
