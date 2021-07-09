package mg.mtovonandrasana.factureo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import mg.mtovonandrasana.factureo.domain.client.Client;
import mg.mtovonandrasana.factureo.web.ClientResource;

@QuarkusTest
@TestHTTPEndpoint(ClientResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientResourceTest {
    
    /* ADDRESS */
    private static final String ADDRESS = "Lot SIAG32 Ambondrona";
    private static final String VILLE = "ANTANANARIVO ";
    private static final Integer CODE_POSTAL = 101;
    private static final String PAYS = "MADAGASCAR";
    private static final String UPDATED_ADDRESS = "Lot IR Rova";
    private static final String UPDATED_VILLE = "FIANANTSOA ";
    private static final Integer UPDATED_CODE_POSTAL = 301;
                                               
    /* CLIENT */
    private static final String NIF = "5005456192";
    private static final String STAT = "62021 11 2021 0 10213";
    private static final String RAISON_SOCIAL = "CASE MADAGASCAR SARL";
    private static final String UPDATED_RAISON_SOCIAL = "NOVITY MADAGASCAR SARL";
    private final Client  inital_client_ = new Client().nif(NIF)
                                                        .stat(STAT)
                                                        .raisonSocial(RAISON_SOCIAL)
                                                        .headOfficeAddress(ADDRESS)
                                                        .city(VILLE)
                                                        .postalCode(CODE_POSTAL)
                                                        .country(PAYS);
    private static Client CLIENT = null;


    @Test
    @Order(1)
    void shouldSaveANewClient() {
        CLIENT = RestAssured.given()
                    .body(inital_client_)
                    .contentType(ContentType.JSON)
                    .when()
                        .post()
                    .then()
                        .statusCode(Status.CREATED.getStatusCode())
                        .extract()
                        .body()
                        .as(new TypeRef<Client>(){});

        assertNotNull(CLIENT);
        assertEquals(NIF,CLIENT.getNif());
        assertEquals(STAT, CLIENT.getStat());
        assertEquals(RAISON_SOCIAL, CLIENT.getRaisonSocial());
        assertEquals(ADDRESS, CLIENT.getHeadOfficeAddress());
        assertEquals(VILLE, CLIENT.getCity());
        assertEquals(CODE_POSTAL, CLIENT.getPostalCode());
        assertEquals(PAYS, CLIENT.getCountry());

    }

    @Test
    @Order(2)
    void shouldUpdateClient() {
        assertNotNull(CLIENT);
        Client updatedClient = CLIENT.headOfficeAddress(UPDATED_ADDRESS)
                                    .city(UPDATED_VILLE)
                                    .postalCode(UPDATED_CODE_POSTAL)
                                    .raisonSocial(UPDATED_RAISON_SOCIAL);

        CLIENT = RestAssured.given()
                    .body(updatedClient)
                    .contentType(ContentType.JSON)
                    .when()
                        .pathParam("nif", CLIENT.getNif())
                        .put("{nif}") 
                    .then()
                        .statusCode(Status.OK.getStatusCode())
                        .extract()
                        .body()
                        .as(new TypeRef<Client>(){});

        assertNotNull(CLIENT);
        assertEquals(NIF, CLIENT.getNif());
        assertEquals(STAT, CLIENT.getStat());
        assertEquals(UPDATED_RAISON_SOCIAL, CLIENT.getRaisonSocial());
        assertEquals(UPDATED_ADDRESS, CLIENT.getHeadOfficeAddress());
        assertEquals(UPDATED_VILLE, CLIENT.getCity());
        assertEquals(UPDATED_CODE_POSTAL, CLIENT.getPostalCode());
        assertEquals(PAYS, CLIENT.getCountry());
    }

    @Test
    @Order(3)
    void shouldReadClient() {
        CLIENT = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                        .pathParam("nif", CLIENT.getNif())
                        .get("{nif}") 
                    .then()
                        .statusCode(Status.OK.getStatusCode())
                        .extract()
                        .body()
                        .as(new TypeRef<Client>(){});

        assertNotNull(CLIENT);
        assertEquals(NIF, CLIENT.getNif());
        assertEquals(STAT, CLIENT.getStat());
        assertEquals(UPDATED_RAISON_SOCIAL, CLIENT.getRaisonSocial());
        assertEquals(UPDATED_ADDRESS, CLIENT.getHeadOfficeAddress());
        assertEquals(UPDATED_VILLE, CLIENT.getCity());
        assertEquals(UPDATED_CODE_POSTAL, CLIENT.getPostalCode());
        assertEquals(PAYS, CLIENT.getCountry());
    }

    @Test
    @Order(4)
    void shouldDeleteClient() {
        boolean isDeleted = RestAssured.given()
            .contentType(ContentType.JSON)
            .when()
                .pathParam("nif", CLIENT.getNif())
                .delete("{nif}") 
            .then()
                .statusCode(Status.OK.getStatusCode())
                .extract()
                .body()
                .as(new TypeRef<Boolean>(){});

        assertTrue(isDeleted);
    }
}
