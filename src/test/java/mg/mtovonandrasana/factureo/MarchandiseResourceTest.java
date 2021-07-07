package mg.mtovonandrasana.factureo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.core.Response.Status;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import mg.mtovonandrasana.factureo.domain.prestation.Marchandise;
import mg.mtovonandrasana.factureo.web.MarchandiseResource;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(MarchandiseResource.class)
class MarchandiseResourceTest {
    private static final String REFERENCE = "Cons";
    private static final String DESCRIPTION = "Consultance du 01/06/2021 à 30/06/2021";
    private static final long PRIX_UNITAIRE = 200000;
    private static final String UNITE = "Jour";

    private static final String U_DESCRIPTION = "Consultance du 01/07/2021 à 31/07/2021";
    private static final long U_PRIX_UNITAIRE = 60000;
    private static final String U_UNITE = "Heure";

    private static Marchandise MARCHANDISE = null;

    @Test
    @Order(1)
    void testCreateSaveNewMarchandiseEndPoint() {

        MARCHANDISE = given().body(new Marchandise()
                            .reference(REFERENCE)
                            .description(DESCRIPTION)
                            .prixUnitaire(PRIX_UNITAIRE)
                            .unite(UNITE)
                        )
                        .contentType(ContentType.JSON)
                        .when()
                            .post()
                        .then()
                            .statusCode(Status.CREATED.getStatusCode())
                            .body("reference", is(REFERENCE))
                            .body("description", is(DESCRIPTION))
                            .body("unite", is(UNITE))
                            .extract()
                            .body()
                            .as(new TypeRef<Marchandise>(){});  
        
        assertNotNull(MARCHANDISE);
        assertEquals(PRIX_UNITAIRE, MARCHANDISE.getPrixUnitaire());
    }

    @Test
    @Order(2)
    void testReadMarchandiseEndPoint() {
        Marchandise readed_marchandise = given()
                        .when()
                            .pathParam("reference", MARCHANDISE.getReference())
                            .get("{reference}")
                        .then()
                            .statusCode(Status.OK.getStatusCode())
                            .body("reference", is(REFERENCE))
                            .body("description", is(DESCRIPTION))
                            .body("unite", is(UNITE))
                            .extract()
                            .body()
                            .as(new TypeRef<Marchandise>(){});  
        assertNotNull(readed_marchandise);
        assertEquals(REFERENCE,readed_marchandise.getReference());
        assertEquals(MARCHANDISE.getReference(),readed_marchandise.getReference());
        assertEquals(PRIX_UNITAIRE, readed_marchandise.getPrixUnitaire());
    }

    @Test
    @Order(3)
    void testUpdateMarchandiseEndPoint() {
        Marchandise updated_marchandise = given().body(new Marchandise()
                                            .description(U_DESCRIPTION)
                                            .unite(U_UNITE)
                                            .prixUnitaire(U_PRIX_UNITAIRE)
                        )
                        .contentType(ContentType.JSON)
                        .when()
                            .pathParam("reference", MARCHANDISE.getReference())
                            .put("{reference}")
                        .then()
                            .statusCode(Status.OK.getStatusCode())
                            .body("reference", is(REFERENCE))
                            .body("description", is(U_DESCRIPTION))
                            .body("unite", is(U_UNITE))
                            .extract()
                            .body()
                            .as(new TypeRef<Marchandise>(){});

        assertNotNull(updated_marchandise);
        assertEquals(REFERENCE,updated_marchandise.getReference());
        assertEquals(MARCHANDISE.getReference(),updated_marchandise.getReference());
        assertEquals(U_PRIX_UNITAIRE, updated_marchandise.getPrixUnitaire());
    }

    @Test
    @Order(4)
    void testDeleteMarchandiseEndPoint() {
        Boolean isDeleted = given()
                            .when()
                                .pathParam("reference", MARCHANDISE.getReference())
                                .delete("{reference}")
                            .then()
                                .statusCode(Status.OK.getStatusCode())
                                .extract()
                                .body()
                                .as(new TypeRef<Boolean>(){});
        
        assertNotNull(isDeleted);
        assertTrue(isDeleted);
    }
}
