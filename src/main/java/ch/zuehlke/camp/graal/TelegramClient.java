package ch.zuehlke.camp.graal;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.zuehlke.camp.graal.entity.telegram.GetUpdates;
import ch.zuehlke.camp.graal.entity.telegram.Message;


@Path("/bot{TOKEN}")
@RegisterRestClient
public interface TelegramClient {

    @GET
    @Path("/getUpdates")
    @Produces(MediaType.APPLICATION_JSON)
    GetUpdates getUpdates(@PathParam("TOKEN") String token);


    @POST
    @Path("/sendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    HashMap sendMessage(@PathParam("TOKEN") String token, @QueryParam("chat_id") String chatId,  @QueryParam("text") String message);

}
