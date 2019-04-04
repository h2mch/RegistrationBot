package ch.zuehlke.camp.graal;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/forecast")
@RegisterRestClient
public interface DarkSkyClient {

    @GET
    @Path("_TOKEN_/{lon},{lat}")
    @Produces(MediaType.APPLICATION_JSON)
    HashMap getWeather(@PathParam("lon") String lon, @PathParam("lat") String lat );

}
