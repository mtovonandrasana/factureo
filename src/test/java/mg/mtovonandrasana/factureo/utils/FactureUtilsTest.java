package mg.mtovonandrasana.factureo.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class FactureUtilsTest {

/* NIF */
    private static final String VALID_NIF_1 = "4002 483 621";
    private static final String VALID_NIF_2 = "4002483621";
    private static final String UN_VALID_NIF_1 = "400a483621";
    private static final String UN_VALID_NIF_2 = "0002483621";
    private static final String UN_VALID_NIF_3 = "40024836";
    private static final String UN_VALID_NIF_4 = "40024836214";

/* STAT */
    private static final String VALID_STAT_1 = "47521112016010900";
    private static final String VALID_STAT_2 = "47521 11 2016 0 10900";
    private static final String UN_VALID_STAT_1 = "07521112016010900";
    private static final String UN_VALID_STAT_2 = "47521a12016010900";
    private static final String UN_VALID_STAT_3 = "4752111201601090";
    private static final String UN_VALID_STAT_4 = "475211120160109001";

/* EMAIL */
    private static final String VALID_EMAIL_1 = "user@domain.com";
    private static final String VALID_EMAIL_2 = "user@domain.co.in";
    private static final String VALID_EMAIL_3 = "user.name@domain.com";
    private static final String VALID_EMAIL_4 = "user_name@domain.com";
    private static final String VALID_EMAIL_5 = "username@yahoo.corporate.in";
    private static final String VALID_EMAIL_6 = "user_name@domain.fr";
    private static final String UN_VALID_EMAIL_1 = ".username@yahoo.com";
    private static final String UN_VALID_EMAIL_2 = "username@yahoo.com.";
    private static final String UN_VALID_EMAIL_3 = "username@yahoo..com";
    private static final String UN_VALID_EMAIL_4 = "username@yahoo.c";
    private static final String UN_VALID_EMAIL_5 = "username@yahoo.corporate";

    /* PHONE NUMBER */
// Valide V1
    private static final String VALID_MALAGASY_PHONE_NUMBER_1 = "+261 34 61 851 44";
    private static final String VALID_MALAGASY_PHONE_NUMBER_2 = "+261 33 61 851 44";
    private static final String VALID_MALAGASY_PHONE_NUMBER_3 = "+261 32 61 851 44";
    private static final String VALID_MALAGASY_PHONE_NUMBER_4 = "+261346185144";
    private static final String VALID_MALAGASY_PHONE_NUMBER_5 = "+261336185144";
    private static final String VALID_MALAGASY_PHONE_NUMBER_6 = "+261326185144";
    private static final String VALID_MALAGASY_PHONE_NUMBER_7 = "+261 (0) 32 62 592 61";
    private static final String VALID_MALAGASY_PHONE_NUMBER_8 = "+261 (0) 33 62 592 61";
    private static final String VALID_MALAGASY_PHONE_NUMBER_9 = "+261 (0) 34 62 592 61";
// Valide V2
    private static final String VALID_MALAGASY_PHONE_NUMBER_10 = "034 62 592 61";
    private static final String VALID_MALAGASY_PHONE_NUMBER_11 = "032 62 592 61";
    private static final String VALID_MALAGASY_PHONE_NUMBER_12 = "033 62 592 61";
    private static final String VALID_MALAGASY_PHONE_NUMBER_13 = "+261(0)326259261";
    private static final String VALID_MALAGASY_PHONE_NUMBER_14 = "+261(0)336259261";
    private static final String VALID_MALAGASY_PHONE_NUMBER_15 = "+261(0)346259261";
    private static final String VALID_MALAGASY_PHONE_NUMBER_16 = "0346259261";
    private static final String VALID_MALAGASY_PHONE_NUMBER_18 = "033 62 59261";
// Unvalid V1
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_1 = "-261 34 61 851 44";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_2 = "+216 34 61 851 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_3 = "+116 34 61 851 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_4 = "+217 34 61 851 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_5 = "+116 34 61 851 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_6 = "+217 34 612 851 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_7 = "+216 44 61 51 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_8 = "0+216 34 61 851 44 ";
// Unvalid v2
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_9 = "+261 (1) 34 61 851 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_10 = "+261 () 34 61 851 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_11 = "+261 (0) 34 61 85 44 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_12 = "+261 (0) 34 61 851 444 ";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_13 = "034 61 814 4";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_14 = "032 61 814 245";
    private static final String UNVALID_MALAGASY_PHONE_NUMBER_15 = "032 6 2 5 9 2 6 1";
    
    @Test
    void validateNifTest() {
        assertTrue(FactureoUtils.validateNif(VALID_NIF_1));
        assertTrue(FactureoUtils.validateNif(VALID_NIF_2));
        assertFalse(FactureoUtils.validateNif(UN_VALID_NIF_1));
        assertFalse(FactureoUtils.validateNif(UN_VALID_NIF_2));

        assertFalse(FactureoUtils.validateNif(" "));
        assertFalse(FactureoUtils.validateNif(""));
        assertFalse(FactureoUtils.validateNif(null));
        assertFalse(FactureoUtils.validateNif(UN_VALID_NIF_3));
        assertFalse(FactureoUtils.validateNif(UN_VALID_NIF_4));        
    }

    @Test
    void testRegexNif() {
        var pattern = Pattern.compile("^([1-9]{1}\\d{3}( )?\\d{3}( )?\\d{3}( )?)$");
        assertTrue(pattern.matcher(VALID_NIF_1).matches());
        assertFalse(pattern.matcher(UN_VALID_NIF_1).matches());
        assertTrue(pattern.matcher(VALID_NIF_2).matches());
    }

    @Test
    void validateStatTest() {
        assertTrue(FactureoUtils.validateStat(VALID_STAT_1));
        assertTrue(FactureoUtils.validateStat(VALID_STAT_2));

        assertFalse(FactureoUtils.validateStat(" "));
        assertFalse(FactureoUtils.validateStat(""));
        assertFalse(FactureoUtils.validateStat(null));
        assertFalse(FactureoUtils.validateStat(UN_VALID_STAT_1));
        assertFalse(FactureoUtils.validateStat(UN_VALID_STAT_2));
        assertFalse(FactureoUtils.validateStat(UN_VALID_STAT_3));
        assertFalse(FactureoUtils.validateStat(UN_VALID_STAT_4));
    }

    @Test
    void validateEmailTest() {
        assertTrue(FactureoUtils.validateEmail(VALID_EMAIL_1));
        assertTrue(FactureoUtils.validateEmail(VALID_EMAIL_2));
        assertTrue(FactureoUtils.validateEmail(VALID_EMAIL_3));
        assertTrue(FactureoUtils.validateEmail(VALID_EMAIL_4));
        assertTrue(FactureoUtils.validateEmail(VALID_EMAIL_5));
        assertTrue(FactureoUtils.validateEmail(VALID_EMAIL_6));
    }

    @Test
    void validateMailUnvalidTest() {
        assertFalse(FactureoUtils.validateEmail(""));
        assertFalse(FactureoUtils.validateEmail(" "));
        assertFalse(FactureoUtils.validateEmail(null));
        assertFalse(FactureoUtils.validateEmail(UN_VALID_EMAIL_1));
        assertFalse(FactureoUtils.validateEmail(UN_VALID_EMAIL_2));
        assertFalse(FactureoUtils.validateEmail(UN_VALID_EMAIL_3));
        assertFalse(FactureoUtils.validateEmail(UN_VALID_EMAIL_4));
        assertFalse(FactureoUtils.validateEmail(UN_VALID_EMAIL_5));
    }

    @Test
    void validateMalagasyPhoneNumberValidTest_v1() {
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_1));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_2));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_3));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_4));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_5));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_6));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_7));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_8));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_9));
    }

    @Test
    void validateMalagasyPhoneNumberValidTest_v2() {
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_10));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_11));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_12));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_13));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_14));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_15));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_16));
        assertTrue(FactureoUtils.validatePhoneNumber(VALID_MALAGASY_PHONE_NUMBER_18));
    }

    @Test
    void validateMalagasyPhoneNumberUnvalidTest_v1() {
        assertFalse(FactureoUtils.validatePhoneNumber(" "));
        assertFalse(FactureoUtils.validatePhoneNumber(""));
        assertFalse(FactureoUtils.validatePhoneNumber(null));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_1));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_2));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_3));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_4));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_5));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_6));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_7));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_8));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_9));
    }

    @Test
    void validateMalagasyPhoneNumberUnvalidTest_v2() {
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_10));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_11));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_12));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_13));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_14));
        assertFalse(FactureoUtils.validatePhoneNumber(UNVALID_MALAGASY_PHONE_NUMBER_15));
    }

    @Test
    void regexTest() {
        var pattern = Pattern.compile("^(((\\+[2]{1}[6]{1}[1]{1}( )?)(\\([0]{1}\\))?( )?([3]{1}[2-4]{1}))|([0]{1}[3]{1}[2-4]{1}))( )?(\\d{2})( )?(\\d{3})( )?(\\d{2})( )?$");
        assertTrue(pattern.matcher("+261 (0) 34 06 555 11").matches());
        assertTrue(pattern.matcher("+261 (0) 3311 123 44").matches());
        assertTrue(pattern.matcher("+261 344422233").matches());
        assertTrue(pattern.matcher("+261345544477").matches());
        assertTrue(pattern.matcher("+261 32 62 592 61").matches());
        assertFalse(pattern.matcher("+2610341112").matches());
        assertTrue(pattern.matcher("033 02 402 75").matches());
        assertTrue(pattern.matcher("0346185144").matches());
    }
}
