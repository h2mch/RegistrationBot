package ch.zuehlke.camp.graal.api;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
    @Path("events")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEvent(Event event) {
        try {
            //Event event = map(new Event(), json);
            postgresStore.store(event);
            return Response.ok(event).status(201).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

    @DELETE
    @Path("events/{id}")
    @Transactional
    public Response delete(@PathParam("id") String id) {
        long numberOfDeletedEvents = Event.delete("id", id);
        if (numberOfDeletedEvents > 0) {
            return Response.noContent().build();
        }
        throw new WebApplicationException("Event with id '" + id + "' does not exist.", 404);
    }

    @GET
    @Path("events")
    public List<Event> get() {
        return Event.findAll().list();
    }

    @GET
    @Path("events/{name}")
    public Event getSingle(@PathParam("name") String name) {
        Event event = Event.find("name", name).firstResult();

        if (event == null) {
            throw new WebApplicationException("Event with name '" + name + "' does not exist.", 404);
        }
        return event;
    }

    private Event map(Event event, HashMap json) {
        return event;
        /*
        event.name = (String) json.get("name");
        event.duration = Duration.of(((BigDecimal) json.get("duration")).intValue(), ChronoUnit.MINUTES);
        event.start = LocalDateTime.ofInstant(Instant.parse(json.get("start").toString()), ZoneOffset.systemDefault());
        event.created = LocalDateTime.now();
        return event;

         */
    }

    @PUT
    @Path("events/{id}")
    @Transactional
    public Event update(@PathParam("id") String id, Event event) {
        Event storedEvent = Event.find("id", id).firstResult();

        if (storedEvent == null) {
            throw new WebApplicationException("Event with id '" + id + "' does not exist.", 404);
        }

        storedEvent.name = event.name;
        storedEvent.start = event.start;
        storedEvent.duration = event.duration;
        return event;
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
