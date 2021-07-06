package mg.mtovonandrasana.factureo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import mg.mtovonandrasana.factureo.domain.prestataire.Prestataire;
import mg.mtovonandrasana.factureo.web.PrestataireResource;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(PrestataireResource.class)
class PrestataireResourceTest {
    private static final String NIF = "5003809271";
    private static final String STAT = "74908 11 2019 0 07585";
    private static final String NOM = "TOVONANDRASANA";
    private static final String PRENOM = "Michaël";
    private static final String CIN = "203011028420 ";
    private static final String ACTIVITE = "Consultant en Software Engineering";
    private static final String OFFICE_ADDRESS ="lot VO25 AF Manakambahiny";
    private static final Integer POSTAL_CODE = 101;
    private static final String CITY = "Antananarivo ";
    private static final String COUNTRY = "Madagascar";
    private static final String EMAIL = "ndrasananamike@gmail.com";
    private static final String PHONE1 = "+261346185144";
    private static final String PHONE2 = "+261326259261";

    private static final String U_NOM = "HERINANDRASANA";
    private static final String U_PRENOM = "Luka Maël";
    private static final String U_CIN = "201011028420 ";
    private static final String U_ACTIVITE = "Consultant en Genie Civil";
    private static final String U_OFFICE_ADDRESS ="lot IR 05 ROVA";
    private static final Integer U_POSTAL_CODE = 301;
    private static final String U_CITY = "Fianarantsoa ";
    private static final String U_EMAIL = "luka.mael@gmail.com";
    private static final String U_PHONE1 = "+261346185155";
    private static final String U_PHONE2 = "+261326259255";

    private Set<String> phoneNumbers = new HashSet<>();

    private static Prestataire PRESTATAIRE = null;

    @Test
    @Order(1)
    void testCreateSaveNewPrestataireEndPoint() {
        phoneNumbers.add(PHONE1);
        phoneNumbers.add(PHONE2);

        PRESTATAIRE = given().body(new Prestataire()
                                    .nif(NIF)
                                    .stat(STAT)
                                    .nom(NOM)
                                    .prenom(PRENOM)
                                    .cin(CIN)
                                    .activite(ACTIVITE)
                                    .headOfficeAdresse(OFFICE_ADDRESS)
                                    .postalCode(POSTAL_CODE)
                                    .city(CITY)
                                    .country(COUNTRY)
                                    .email(EMAIL)
                                    .phoneNumbers(phoneNumbers)
                                    .isIndividuel(true)
                        )
                        .contentType(ContentType.JSON)
                        .when()
                            .post()
                        .then()
                            .statusCode(Status.CREATED.getStatusCode())
                            .body("nif", is(NIF))
                            .body("stat", is(STAT))
                            .body("nom", is(NOM))
                            .body("prenom", is(PRENOM))
                            .body("cin", is(CIN))
                            .body("email", is(EMAIL))
                            .body("activite", is(ACTIVITE))
                            .body("headOfficeAdresse", is(OFFICE_ADDRESS))
                            .body("postalCode", is(POSTAL_CODE))
                            .body("city", is(CITY))
                            .body("country", is(COUNTRY))
                            .extract()
                            .body()
                            .as(new TypeRef<Prestataire>(){});  
        
        assertNotNull(PRESTATAIRE);
        assertTrue(PRESTATAIRE.isIsIndividuel());
    }

    @Test
    @Order(2)
    void testReadPrestataireEndPoint() {
        Prestataire readed_prestataire = given()
                        .when()
                            .pathParam("nif", PRESTATAIRE.getNif())
                            .get("{nif}")
                        .then()
                            .statusCode(Status.OK.getStatusCode())
                            .body("nif", is(NIF))
                            .body("stat", is(STAT))
                            .body("nom", is(NOM))
                            .body("prenom", is(PRENOM))
                            .body("cin", is(CIN))
                            .body("email", is(EMAIL))
                            .body("activite", is(ACTIVITE))
                            .body("headOfficeAdresse", is(OFFICE_ADDRESS))
                            .body("postalCode", is(POSTAL_CODE))
                            .body("city", is(CITY))
                            .body("country", is(COUNTRY))
                            .extract()
                            .body()
                            .as(new TypeRef<Prestataire>(){});
        assertNotNull(readed_prestataire);
        assertEquals(NIF,readed_prestataire.getNif());
        assertEquals(PRESTATAIRE.getNif(),readed_prestataire.getNif());
        assertTrue(readed_prestataire.getPhoneNumbers().contains(PHONE1));
        assertTrue(readed_prestataire.getPhoneNumbers().contains(PHONE2));
    }

    @Test
    @Order(3)
    void testUpdatePrestataireEndPoint() {
        Set<String> phoneNumbersUpdated = new HashSet<>();
        phoneNumbersUpdated.add(U_PHONE1);
        phoneNumbersUpdated.add(U_PHONE2);
        Prestataire updated_prestataire = given().body(new Prestataire()
                                .nom(U_NOM)
                                .prenom(U_PRENOM)
                                .cin(U_CIN)
                                .activite(U_ACTIVITE)
                                .headOfficeAdresse(U_OFFICE_ADDRESS)
                                .postalCode(U_POSTAL_CODE)
                                .city(U_CITY)
                                .email(U_EMAIL)
                                .phoneNumbers(phoneNumbersUpdated)
                        )
                        .contentType(ContentType.JSON)
                        .when()
                            .pathParam("nif", PRESTATAIRE.getNif())
                            .put("{nif}")
                        .then()
                            .statusCode(Status.OK.getStatusCode())
                            .body("nif", is(NIF))
                            .body("stat", is(STAT))
                            .body("nom", is(U_NOM))
                            .body("prenom", is(U_PRENOM))
                            .body("cin", is(U_CIN))
                            .body("email", is(U_EMAIL))
                            .body("activite", is(U_ACTIVITE))
                            .body("headOfficeAdresse", is(U_OFFICE_ADDRESS))
                            .body("postalCode", is(U_POSTAL_CODE))
                            .body("city", is(U_CITY))
                            .body("country", is(COUNTRY))
                            .extract()
                            .body()
                            .as(new TypeRef<Prestataire>(){});

        assertNotNull(updated_prestataire);
        assertEquals(NIF,updated_prestataire.getNif());
        assertEquals(PRESTATAIRE.getNif(),updated_prestataire.getNif());
        assertTrue(updated_prestataire.getPhoneNumbers().contains(U_PHONE1));
        assertTrue(updated_prestataire.getPhoneNumbers().contains(U_PHONE2));
        assertFalse(updated_prestataire.getPhoneNumbers().contains(PHONE1));
        assertFalse(updated_prestataire.getPhoneNumbers().contains(PHONE2));
    }

    @Test
    @Order(4)
    void testDeletePrestataireEndPoint() {
        Boolean isDeleted = given()
                            .when()
                                .pathParam("nif", PRESTATAIRE.getNif())
                                .delete("{nif}")
                            .then()
                                .statusCode(Status.OK.getStatusCode())
                                .extract()
                                .body()
                                .as(new TypeRef<Boolean>(){});
        
        assertNotNull(isDeleted);
        assertTrue(isDeleted);
    }
    
}
