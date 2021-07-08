package mg.mtovonandrasana.factureo.domain.common;

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
}
