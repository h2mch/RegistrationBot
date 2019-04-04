package ch.zuehlke.camp.graal.api;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.zuehlke.camp.graal.PostgresStore;
import ch.zuehlke.camp.graal.entity.orm.Event;
import ch.zuehlke.camp.graal.entity.orm.Person;
import ch.zuehlke.camp.graal.entity.orm.Registration;

@Path("/api")
public class Resource {

    @Inject
    PostgresStore postgresStore;

    @POST
    @Path("person")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(Person person) {
        try {
            person.created = LocalDateTime.now();
            postgresStore.store(person);
            return Response.ok(person).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

    @POST
    @Path("event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(Event event) {
        try {
            event.created = LocalDateTime.now();
            postgresStore.store(event);
            return Response.ok(event).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

    @POST
    @Path("registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(Registration registration) {
        try {
            registration.created = LocalDateTime.now();
            postgresStore.store(registration);
            return Response.ok(registration).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

}
