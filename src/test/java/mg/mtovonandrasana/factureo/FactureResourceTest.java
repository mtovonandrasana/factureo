package mg.mtovonandrasana.factureo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import mg.mtovonandrasana.factureo.domain.client.Client;
import mg.mtovonandrasana.factureo.domain.facture.Facture;
import mg.mtovonandrasana.factureo.domain.prestation.Marchandise;
import mg.mtovonandrasana.factureo.domain.prestation.Panier;

import org.apache.commons.collections4.CollectionUtils;
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

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FactureResourceTest {

    /* FACTURE */
    private static final String FACTURE_NUMERO = "202107";
    private static final LocalDate FACTURE_DATE = LocalDate.of(2021, Month.JULY, 05);
    private static final String FACTURE_PAIEMENT_MODE = "Virement";
    private static final String FACTURE_DEVISE = "MGA";
    private static final String FACTURE_ECHEANCCE = "Payable à la reception du facture";
    private static final long FACTURE_DEJA_PAYER = 0;
    private static final long FACTURE_MONTANT = 2200000;
    private static final LocalDate FACTURE_UPDATED_DATE = LocalDate.of(2021, Month.JULY, 05);
    private static final String FACTURE_UPDATED_PAIEMENT_MODE = "Orange Money";
    private static final String FACTURE_UPDATED_ECHEANCCE = LocalDate.of(2021, Month.JULY, 10).toString();
    private static final long FACTURE_UPDATED_DEJA_PAYER = 2000000;
    private static final long FACTURE_UPDATED_MONTANT = 4400000;

    private static Facture FACTURE = null;

    /* ADDRESS */
    private static final String ADDRESS = "Lot SIAG32 Ambondrona";
    private static final String VILLE = "ANTANANARIVO ";
    private static final Integer CODE_POSTAL = 101;
    private static final String PAYS = "MADAGASCAR";

    /* CLIENT */
    private static final String NIF = "5005456192";
    private static final String STAT = "62021 11 2021 0 10213";
    private static final String RAISON_SOCIAL = "CASE MADAGASCAR SARL";
    private final Client clientPojo = new Client().nif(NIF).stat(STAT).raisonSocial(RAISON_SOCIAL)
                                                    .headOfficeAddress(ADDRESS).city(VILLE)
                                                    .postalCode(CODE_POSTAL)
                                                    .country(PAYS);
    /* MARCHANDISE */
    private static final String MARCHANDISE_REFERENCE = "CONS";
    private static final String MARCHANDISE_DESCRITPTION = "Consultance du 01/06/2021 au 30/06/2021";
    private static final long MARCHANDISE_PRIX_UNITAIRE = 100000;
    private static final String MARCHANDISE_UNITE = "jours";
    private final Marchandise marchandisePojo = new Marchandise().reference(MARCHANDISE_REFERENCE)
                                                                .description(MARCHANDISE_DESCRITPTION)
                                                                .prixUnitaire(MARCHANDISE_PRIX_UNITAIRE)
                                                                .unite(MARCHANDISE_UNITE);
    /* PANIER */
    private static final int PANIER_QUANTITY = 22;
    private final Panier panierPojo = new Panier().quantity(PANIER_QUANTITY);

    @Test
    @Order(1)
    void testSaveNewFactureEndpoint() {
        Client client = given().body(clientPojo).contentType(ContentType.JSON)
                            .when().post("/client")
                            .then().statusCode(Status.CREATED.getStatusCode())
                            .extract().body().as(new TypeRef<Client>() {});
        assertNotNull(client);
        Marchandise marchandise = given().body(marchandisePojo).contentType(ContentType.JSON)
                            .when().post("/marchandise")
                            .then().statusCode(Status.CREATED.getStatusCode())
                            .extract().body().as(new TypeRef<Marchandise>() {});
        assertNotNull(marchandise);
        Panier panier = given().body(panierPojo.marchandise(marchandise)).contentType(ContentType.JSON)
                            .when().post("/panier")
                            .then().statusCode(Status.CREATED.getStatusCode())
                            .extract().body().as(new TypeRef<Panier>() {});
        assertNotNull(panier);
        Set<Panier> paniers = new HashSet<>();
        paniers.add(panier);

        FACTURE =  given()
                        .body(new Facture()
                                .numero(FACTURE_NUMERO)
                                .date(FACTURE_DATE)
                                .montant(FACTURE_MONTANT)
                                .paiementMode(FACTURE_PAIEMENT_MODE)
                                .devise(FACTURE_DEVISE)
                                .echeance(FACTURE_ECHEANCCE)
                                .dejaPayer(FACTURE_DEJA_PAYER)
                                .client(client)
                                .paniers(paniers)
                        )
                        .contentType(ContentType.JSON)
                        .when()
                            .post("/facture")
                        .then()
                            .statusCode(Status.CREATED.getStatusCode())
                            .body("numero", is(FACTURE_NUMERO))
                            .body("date", is(FACTURE_DATE))
                            .body("paiementMode", is(FACTURE_PAIEMENT_MODE))
                            .body("devise", is(FACTURE_DEVISE))
                            .body("echeance", is(FACTURE_ECHEANCCE))
                            .body("dejaPayer", is(FACTURE_DEJA_PAYER))
                            .body("montant", is(FACTURE_MONTANT))
                            .body("client.nif", is(NIF))
                            .body("client.stat", is(STAT))
                            .body("client.raisonSocial", is(RAISON_SOCIAL))
                            .body("client.headOfficeAddress", is(ADDRESS))
                            .body("client.postalCode", is(CODE_POSTAL))
                            .body("client.city", is(VILLE))
                            .body("client.country", is(PAYS))
                            .body("paniers.[0].quantity", is(PANIER_QUANTITY))
                            .body("paniers.[0].marchandise.reference", is(MARCHANDISE_REFERENCE))
                            .body("paniers.[0].marchandise.description", is(MARCHANDISE_DESCRITPTION))
                            .body("paniers.[0].marchandise.prixUnitaire", is(MARCHANDISE_PRIX_UNITAIRE))
                            .body("paniers.[0].marchandise.unite", is(MARCHANDISE_UNITE))
                            .extract()
                            .body()
                            .as(new TypeRef<Facture>() {});
        assertNotNull(FACTURE);
        assertNotNull(FACTURE.getClient());
        assertTrue(CollectionUtils.isNotEmpty(FACTURE.getPaniers()));
        assertEquals(FACTURE.getMontant(), FACTURE.getPaniers().stream().mapToLong(p -> p.getQuantity()*p.getMarchandise().getPrixUnitaire()).sum());
    }

    @Test
    @Order(2)
    void testUpdateFactureEndpoint() {
        Facture updated_facture = given()
                    .body(new Facture()
                        .date(FACTURE_UPDATED_DATE)
                        .dejaPayer(FACTURE_UPDATED_DEJA_PAYER)
                        .montant(FACTURE_UPDATED_MONTANT)
                        .paiementMode(FACTURE_UPDATED_PAIEMENT_MODE)
                        .echeance(FACTURE_UPDATED_ECHEANCCE)
                    )
                    .contentType(ContentType.JSON)
                    .when()
                        .pathParam("numero", FACTURE.getNumero())
                        .put("/facture/{numero}")
                    .then()
                        .statusCode(Status.OK.getStatusCode())
                        .body("date", is(FACTURE_UPDATED_DATE))
                        .body("paiementMode", is(FACTURE_UPDATED_PAIEMENT_MODE))
                        .body("echeance", is(FACTURE_UPDATED_ECHEANCCE))
                        .body("dejaPayer", is(FACTURE_UPDATED_DEJA_PAYER))
                        .body("montant", is(FACTURE_UPDATED_MONTANT))
                        .extract()
                        .body()
                        .as(new TypeRef<Facture>(){});

        assertNotNull(updated_facture);

    }

    @Test
    @Order(3)
    void testReadFactureEndpoint() {
        Facture readed_facture = given()
                    .contentType(ContentType.JSON)
                    .when()
                        .pathParam("numero", FACTURE.getNumero())
                        .get("/facture/{numero}")
                    .then()
                        .statusCode(Status.OK.getStatusCode())
                        .body("date", is(FACTURE_UPDATED_DATE))
                        .body("paiementMode", is(FACTURE_UPDATED_PAIEMENT_MODE))
                        .body("echeance", is(FACTURE_UPDATED_ECHEANCCE))
                        .body("dejaPayer", is(FACTURE_UPDATED_DEJA_PAYER))
                        .body("montant", is(FACTURE_UPDATED_MONTANT))
                        .body("devise", is(FACTURE_DEVISE))
                        .body("client.nif", is(NIF))
                        .body("client.stat", is(STAT))
                        .body("client.raisonSocial", is(RAISON_SOCIAL))
                        .body("client.headOfficeAddress", is(ADDRESS))
                        .body("client.postalCode", is(CODE_POSTAL))
                        .body("client.city", is(VILLE))
                        .body("client.country", is(PAYS))
                        .body("paniers.[0].quantity", is(PANIER_QUANTITY))
                        .body("paniers.[0].marchandise.reference", is(MARCHANDISE_REFERENCE))
                        .body("paniers.[0].marchandise.description", is(MARCHANDISE_DESCRITPTION))
                        .body("paniers.[0].marchandise.prixUnitaire", is(MARCHANDISE_PRIX_UNITAIRE))
                        .body("paniers.[0].marchandise.unite", is(MARCHANDISE_UNITE))
                        .extract()
                        .body()
                        .as(new TypeRef<Facture>(){});

        assertNotNull(readed_facture);
        assertEquals(FACTURE_NUMERO, readed_facture.getNumero());
        assertEquals(FACTURE.getNumero(), readed_facture.getNumero());
        assertNotNull(readed_facture.getClient());
        assertFalse(CollectionUtils.isEmpty(readed_facture.getPaniers()));
    }

    @Test
    @Order(4)
    void testDeleteFactureEndpoint() {
        Boolean isdeleted = given()
                    .contentType(ContentType.JSON)
                    .when()
                        .pathParam("numero", FACTURE.getNumero())
                        .delete("/facture/{numero}")
                    .then()
                        .statusCode(Status.OK.getStatusCode())
                        .extract()
                        .body()
                        .as(new TypeRef<Boolean>(){});

        assertNotNull(isdeleted);
        assertTrue(isdeleted);
    }

}