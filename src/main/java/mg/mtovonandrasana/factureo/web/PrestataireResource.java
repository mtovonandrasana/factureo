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

import mg.mtovonandrasana.factureo.domain.prestataire.Prestataire;
import mg.mtovonandrasana.factureo.domain.prestataire.PrestataireService;

@Path("/prestataire")
@Tag(name = "Prestataire")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrestataireResource {
    
    @Inject
    PrestataireService prestataireService;

    @POST
    public Response createPrestataire(Prestataire prestatairePojo) {
        var prestataire = prestataireService.createPrestataire(prestatairePojo);
        var uri = UriBuilder.fromResource(PrestataireResource.class)
                            .path(prestataire.getNif())
                            .build();
        return Response.created(uri).entity(prestataire).build();
    }

    @GET
    @Path("{nif}")
    public Response readPrestatire(@PathParam("nif") String nif) {
        var client = prestataireService.getPrestataire(nif);
        return Response.ok(client).build();
    }

    @PUT
    @Path("{nif}")
    public Response updateClient(@PathParam("nif") String nif, Prestataire prestatairePojo) {
        var client = prestataireService.updatePresataire(nif, prestatairePojo);
        return Response.ok(client).build();
    }

    @DELETE
    @Path("{nif}")
    public Response deletePrestataire(@PathParam("nif") String nif) {
        var isDeleted = prestataireService.deletePrestataire(nif);
        return Response.ok(isDeleted).build();
    }

    @GET
    public Response listAllPrestataires() {
        return Response.ok(prestataireService.listAllPrestataires()).build();
    }
}
