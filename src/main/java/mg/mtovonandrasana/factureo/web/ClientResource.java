package mg.mtovonandrasana.factureo.web;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import mg.mtovonandrasana.factureo.domain.client.Client;
import mg.mtovonandrasana.factureo.domain.client.ClientService;

@Path("/client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {
    
    @Inject
    ClientService clientService;


    @POST
    public Response createAndSaveClient(Client clientPojo) {
        var client = clientService.creaClient(clientPojo);
        var uri = UriBuilder.fromResource(ClientResource.class)
                            .path(client.getNif())
                            .build();
        return Response.created(uri).entity(client).build();        
    }
}
