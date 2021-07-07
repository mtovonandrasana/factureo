package mg.mtovonandrasana.factureo.web;

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

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import mg.mtovonandrasana.factureo.domain.prestation.Panier;

@Path("/panier")
@Tag(name = "Panier")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PanierResource {
    
    @POST
    public Response createPanier(Panier panier) {
        return null;
    }

    @GET
    @Path("{panierId}")
    public Response readPanier(@PathParam("panierId") Long panierId) {
        return null;
    }

    @PUT
    @Path("{panierId}")
    public Response updatePanier(@PathParam("panierId") Long panierId, Panier panier) {
        return null;
    }

    @DELETE
    @Path("{panierId}")
    public Response deletePanier(@PathParam("panierId") Long panierId) {
        return null;
    }

    @GET
    public Response listAllPaniers() {
        return null;
    }
}
