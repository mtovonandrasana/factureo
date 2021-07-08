package mg.mtovonandrasana.factureo.web;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import mg.mtovonandrasana.factureo.domain.client.Client;
import mg.mtovonandrasana.factureo.domain.client.ClientService;

@Path("/client")
@Tag(name = "Client")
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

    @GET
    @Path("{nif}")
    public Response readClient(@PathParam("nif") String nif) {
        var client = clientService.getClientByNif(nif);
        return Response.ok(client).build();
    }

    @PUT
    @Path("{nif}")
    public Response updateClient(@PathParam("nif") String nif, Client clientPojo) {
        var client = clientService.updatClient(nif, clientPojo);
        return Response.ok(client).build();
    }

    @DELETE
    @Path("{nif}")
    public Response deleteClient(@PathParam("nif") String nif) {
        var isDeleted = clientService.deleteClient(nif);
        return Response.ok(isDeleted).build();
    }

    @GET
    public Response listAllClients() {
        return Response.ok(clientService.listAllClients()).build();
    }
}
