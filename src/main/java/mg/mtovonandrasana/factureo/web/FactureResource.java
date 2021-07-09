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

import mg.mtovonandrasana.factureo.domain.facture.Facture;
import mg.mtovonandrasana.factureo.domain.facture.FactureService;
import mg.mtovonandrasana.factureo.web.adapter.FactureAdapter;
import mg.mtovonandrasana.factureo.web.dto.FactureDTO;

@Path("/facture")
@Tag(name = "Facture")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FactureResource {

    @Inject
    FactureService factureService;


    @POST
    public Response createAndSaveFacture(FactureDTO factureDTO) {
        var facture = factureService.createFacture(FactureAdapter.factureDTOtoTO(factureDTO));
        var uri = UriBuilder.fromResource(FactureResource.class)
                            .path(facture.getNumero())
                            .build();
        return Response.created(uri).entity(facture).build();        
    }

    @GET
    @Path("{numero}")
    public Response readFacture(@PathParam("numero") String numero) {
        var facture = factureService.getFacture(numero);
        return Response.ok(facture).build();
    }

    @PUT
    @Path("{numero}")
    public Response updateFacture(@PathParam("numero") String numero, FactureDTO factureDTO) {
        var facture = factureService.updateFacture(numero, FactureAdapter.factureDTOtoTO(factureDTO));
        return Response.ok(facture).build();
    }

    @DELETE
    @Path("{numero}")
    public Response deleteFacture(@PathParam("numero") String numero) {
        var isDeleted = factureService.deleteFacture(numero);
        return Response.ok(isDeleted).build();
    }

    @GET
    public Response listAllPrestataires() {
        return Response.ok(factureService.listAllFactures()).build();
    }
}