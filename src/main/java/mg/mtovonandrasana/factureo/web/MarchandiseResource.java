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

import mg.mtovonandrasana.factureo.domain.prestation.Marchandise;
import mg.mtovonandrasana.factureo.domain.prestation.MarchandiseService;

@Path("/marchandise")
@Tag(name = "Marchandise")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarchandiseResource {
    @Inject
    MarchandiseService marchandiseService;

    @POST
    public Response createPrestataire(Marchandise marchandisePojo) {
        var marchandise = marchandiseService.createMarchandise(marchandisePojo);
        var uri = UriBuilder.fromResource(PrestataireResource.class)
                            .path(marchandise.getReference())
                            .build();
        return Response.created(uri).entity(marchandise).build();
    }

    @GET
    @Path("{reference}")
    public Response readMarchandise(@PathParam("reference") String reference) {
        var marchandise = marchandiseService.getMarchandise(reference);
        return Response.ok(marchandise).build();
    }

    @PUT
    @Path("{reference}")
    public Response updateMarchandise(@PathParam("reference") String reference, Marchandise marchandisePojo) {
        var marchandise = marchandiseService.updateMarchandise(marchandisePojo.reference(reference));
        return Response.ok(marchandise).build();
    }

    @DELETE
    @Path("{reference}")
    public Response deleteMarchandise(@PathParam("reference") String reference) {
        var isDeleted = marchandiseService.deleteMarchandise(reference);
        return Response.ok(isDeleted).build();
    }

    @GET
    public Response listAllMarchandises() {
        return Response.ok(marchandiseService.listAllMarchandises()).build();
    }
}
