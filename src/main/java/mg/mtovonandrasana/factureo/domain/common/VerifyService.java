package mg.mtovonandrasana.factureo.domain.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import mg.mtovonandrasana.factureo.utils.FactureoUtils;

public class VerifyService {
    private static final Logger LOGGER = Logger.getLogger(VerifyService.class);
    private VerifyService() {}
    
    public static void verifyNif(String nif) {
        if (StringUtils.isBlank(nif)) {
            LOGGER.error("NIF is empty.");
            throw new IllegalArgumentException("Nif is empty.");
        }
        if(!FactureoUtils.validateNif(nif)) {
            LOGGER.error("NIF is not valid: "+nif);
            throw new IllegalArgumentException("Nif is not valid.");
        }
    }

    public static void verifyStat(String stat) {
        if (StringUtils.isBlank(stat)) {
            LOGGER.error("STAT is empty.");
            throw new IllegalArgumentException("STAT is empty.");
        }
        if (!FactureoUtils.validateStat(stat)) {
            LOGGER.error("STAT is not valid.");
            throw new IllegalArgumentException("STAT is not valid.");
        }
    }

    public static void verifyCin(String cin) {
        if (StringUtils.isBlank(cin)) {
            LOGGER.error("CIN is empty.");
            throw new IllegalArgumentException("CIN is empty.");
        }
        if (!FactureoUtils.validateCin(cin)) {
            LOGGER.error("CIN is not valid.");
            throw new IllegalArgumentException("CIN is not valid.");
        }
    }

    public static void verifyOfficeAddress(String officeAddress, String ville, String postalCode, String pays) {
        if(StringUtils.isBlank(officeAddress)) {
            LOGGER.error("Office address is blank.");
            throw new IllegalArgumentException("Office address is blank.");
        }
        if(StringUtils.isBlank(ville)) {
            LOGGER.error("Ville is blank.");
            throw new IllegalArgumentException("Ville is blank.");
        }
        if(StringUtils.isBlank(postalCode)) {
            LOGGER.error("Postal code is blank.");
            throw new IllegalArgumentException("Postal code is blank.");
        }
        if(StringUtils.isBlank(pays)) {
            LOGGER.error("Pays is blank.");
            throw new IllegalArgumentException("Pays is blank.");
        }
    }

    public static void verifyEmail(String email) {
        if(StringUtils.isBlank(email)) {
            LOGGER.error("Email is blank.");
            throw new IllegalArgumentException("Email is blank.");
        }
        if(!FactureoUtils.validateEmail(email)) {
            LOGGER.error("Email is not valid.");
            throw new IllegalArgumentException("Email is not valid.");
        }
    }

    // TODO: to be rechecked
    public static void varfyPhoneNumbers(Set<String> phoneNumbers) {
        List<String> errors = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(phoneNumbers)) {
            phoneNumbers.forEach(p -> {
                if(!FactureoUtils.validatePhoneNumber(p)) {
                    LOGGER.error("Phone number is not valid: "+p);
                    errors.add(p+": This phone number is not valid.");
                    throw new IllegalArgumentException("Phone number is not valid: "+p);
                }
            });
        }
    }
}
