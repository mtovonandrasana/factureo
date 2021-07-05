package mg.mtovonandrasana.factureo;

import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
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
                                               
    /* CLIENT */
    private static final String NIF = "5005456192";
    private static final String STAT = "62021 11 2021 0 10213";
    private static final String RAISON_SOCIAL = "NOVITY MADAGASCAR SARL";
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
        RestAssured.given()
                    .body(inital_client_)
                    .contentType(ContentType.JSON)
                    .when()
                        .post()
                    .then()
                        .statusCode(Status.CREATED.getStatusCode());
    }
}
