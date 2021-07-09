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

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import mg.mtovonandrasana.factureo.domain.prestation.Marchandise;
import mg.mtovonandrasana.factureo.domain.prestation.Panier;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PanierResourceTest {
    
    private static final int QUANTITY = 22;
    private static final int U_QUANTITY = 20;
    /* MARCHANDISE */
    private static final String REFERENCE = "Cons";
    private static final String DESCRIPTION = "Consultance du 01/06/2021 à 30/06/2021";
    private static final long PRIX_UNITAIRE = 200000;
    private static final String UNITE = "Jour";

    private static Panier PANIER = null;

    @Test
    @Order(1)
    void testCreatePanierEndPoint() {
        Marchandise marchandise = given()
                .body(new Marchandise().reference(REFERENCE).description(DESCRIPTION).prixUnitaire(PRIX_UNITAIRE)
                        .unite(UNITE))
                .contentType(ContentType.JSON).when().post("/marchandise").then().statusCode(Status.CREATED.getStatusCode()).extract()
                .body().as(new TypeRef<Marchandise>() {
                });
        assertNotNull(marchandise);
        PANIER = given()
                    .body(new Panier()
                        .quantity(QUANTITY)
                        .marchandise(marchandise)
                    )
                    .contentType(ContentType.JSON)
                    .when()
                        .post("/panier")
                    .then()
                        .statusCode(Status.CREATED.getStatusCode())
                        .body("quantity", is(QUANTITY))
                        .body("marchandise.reference", is(REFERENCE))
                        .body("marchandise.description", is(DESCRIPTION))
                        .body("marchandise.unite", is(UNITE))
                        .extract()
                        .body()
                        .as(new TypeRef<Panier>(){});

        assertNotNull(PANIER);
        assertNotNull(PANIER.getId());
        assertNotNull(PANIER.getMarchandise());
        assertEquals(PANIER.getMarchandise(), marchandise);
        assertEquals(PRIX_UNITAIRE, PANIER.getMarchandise().getPrixUnitaire());
                                    
    }
    
    @Test
    @Order(2)
    void testReadPanierEndPoint() {
        assertNotNull(PANIER);
        Panier readed_panier = given()
                    .contentType(ContentType.JSON)
                    .when()
                        .pathParam("panierId", PANIER.getId())
                        .get("/panier/{panierId}")
                    .then()
                        .statusCode(Status.OK.getStatusCode())
                        .body("quantity", is(QUANTITY))
                        .body("marchandise.reference", is(REFERENCE))
                        .body("marchandise.description", is(DESCRIPTION))
                        .body("marchandise.unite", is(UNITE))
                        .extract()
                        .body()
                        .as(new TypeRef<Panier>(){});

        assertNotNull(readed_panier);
        assertNotNull(readed_panier.getId());
        assertNotNull(readed_panier.getMarchandise());
        assertEquals(readed_panier.getId(), PANIER.getId());
        assertEquals(PRIX_UNITAIRE, readed_panier.getMarchandise().getPrixUnitaire());
      
    }
    
    @Test
    @Order(3)
    void testUpdatePanierEndPoint() {
        assertNotNull(PANIER);
        Panier updated_panier = given()
                    .body(PANIER.quantity(U_QUANTITY))
                    .contentType(ContentType.JSON)
                    .when()
                        .pathParam("panierId", PANIER.getId())
                        .put("/panier/{panierId}")
                    .then()
                        .statusCode(Status.OK.getStatusCode())
                        .body("quantity", is(U_QUANTITY))
                        .body("marchandise.reference", is(REFERENCE))
                        .body("marchandise.description", is(DESCRIPTION))
                        .body("marchandise.unite", is(UNITE))
                        .extract()
                        .body()
                        .as(new TypeRef<Panier>(){});

        assertNotNull(updated_panier);
        assertNotNull(updated_panier.getId());
        assertNotNull(updated_panier.getMarchandise());
        assertEquals(updated_panier.getId(), PANIER.getId());
        assertEquals(PRIX_UNITAIRE, updated_panier.getMarchandise().getPrixUnitaire());
      
    }  

    @Test
    @Order(4)
    void testDeletePanierEndPoint() {
        assertNotNull(PANIER);
        Boolean is_deleted__panier = given()
                    .contentType(ContentType.JSON)
                    .when()
                        .pathParam("panierId", PANIER.getId())
                        .delete("/panier/{panierId}")
                    .then()
                        .statusCode(Status.OK.getStatusCode())
                        .extract()
                        .body()
                        .as(new TypeRef<Boolean>(){});

        assertNotNull(is_deleted__panier);
        assertTrue(is_deleted__panier);
    }  
}
