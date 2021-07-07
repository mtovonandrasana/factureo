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

import mg.mtovonandrasana.factureo.domain.prestation.Panier;
import mg.mtovonandrasana.factureo.domain.prestation.PanierService;

@Path("/panier")
@Tag(name = "Panier")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PanierResource {

    @Inject
    PanierService panierService;
    
    @POST
    public Response createPanier(Panier panierPojo) {
        var panier = panierService.createPanier(panierPojo);
        var uri = UriBuilder.fromResource(PanierResource.class)
                            .path(Long.toString(panier.getId()))
                            .build();
        return Response.created(uri).entity(panier).build();
    }

    @GET
    @Path("{panierId}")
    public Response readPanier(@PathParam("panierId") Long panierId) {
        var panier = panierService.readPanier(panierId);
        return Response.ok(panier).build();
    }

    @PUT
    @Path("{panierId}")
    public Response updatePanier(@PathParam("panierId") Long panierId, Panier updatedPanier) {
        var panier = panierService.updatePanier(panierId, updatedPanier);
        return Response.ok(panier).build();
    }

    @DELETE
    @Path("{panierId}")
    public Response deletePanier(@PathParam("panierId") Long panierId) {
        boolean isDeleted = panierService.deletePanier(panierId);
        return Response.ok(isDeleted).build();
    }

    @GET
    public Response listAllPaniers() {
        return Response.ok(panierService.listAllPaniers()).build();
    }
}
