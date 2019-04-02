package ch.zuehlke.camp.graal;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.zuehlke.camp.graal.entity.Registration;

@Path("/registration")
public class RegistrationResource {

    @Inject
    private RegistrationService registrationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response register(Registration registration) {
        String uuid = registrationService.register(registration);
        return Response.accepted(uuid).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRegistration(@PathParam("id") String uuid, Registration registration) {
        Registration updatedRegistration = registrationService.update(uuid, registration);

        return Response.ok(updatedRegistration).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getRegistration(@PathParam("id") String id) {
        Registration registration = registrationService.getRegistration(id);
        return Response.ok(registration).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegistrations() {
        System.out.println("getRegistration was called");
        Collection<Registration> registrations = registrationService.getRegistrations();
        return Response.ok(registrations).build();

    }
}
