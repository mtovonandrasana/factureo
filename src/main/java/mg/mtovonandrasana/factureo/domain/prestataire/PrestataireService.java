package mg.mtovonandrasana.factureo.domain.prestataire;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import mg.mtovonandrasana.factureo.domain.common.VerifyService;
import mg.mtovonandrasana.factureo.utils.FactureoUtils;

@ApplicationScoped
public class PrestataireService {

    private static final Logger LOGGER = Logger.getLogger(PrestataireService.class);

    @Inject
    PrestataireRepository prestataireRepository;
    
    @Transactional
    public Prestataire createPrestataire(Prestataire createdPrestataire) {
        LOGGER.info("Creating Prestataire: "+createdPrestataire.toString());
        var prestataire = checkInfoPrestatiaire(createdPrestataire);
        checkUniqueNif(prestataire.getNif());
        prestataireRepository.persistAndFlush(prestataire);
        LOGGER.info("Prestataire created.");
        return prestataire;
    }

    public Prestataire getPrestataire(String nif) {
        return checkAndGetPrestataire(nif);
    }

    @Transactional
    public Prestataire updatePresataire(String nif, Prestataire updatedPrestataire) {
        var prestataire = checkAndGetPrestataire(nif);
        LOGGER.info("Updating prestataire "+prestataire.toString()+" to "+updatedPrestataire.toString());
        updateStat(prestataire, updatedPrestataire.getStat());
        updateCIN(prestataire, updatedPrestataire.getCin());
        updateCompanyName(prestataire,updatedPrestataire.getComanyName());
        updateActivity(prestataire,updatedPrestataire.getActivity());
        updateEmail(prestataire, updatedPrestataire.getEmail());
        updateOfficeAdress(prestataire, updatedPrestataire.getHeadOfficeAdresse());
        updateCity(prestataire, updatedPrestataire.getCity());
        updatePostalCode(prestataire, updatedPrestataire.getPostalCode());
        updatePhoneNumbers(prestataire, updatedPrestataire.getPhoneNumbers());
        LOGGER.info("Prestataire updated: "+prestataire.toString());
        return prestataire;
    }

    @Transactional
    public boolean deletePrestataire(String nif) {
        var prestataire = checkAndGetPrestataire(nif);
        LOGGER.info("Deleting prestataire: "+prestataire);
        boolean isDeleted = prestataireRepository.deleteById(nif);
        LOGGER.info("Prestataire deleted: "+isDeleted);
        return isDeleted;
    }

    public Set<Prestataire> listAllPrestataires() {
        LOGGER.info("Collecting all prestataire.");
        return prestataireRepository.streamAll().collect(Collectors.toSet());
    }

 /* CHECK METHOD */

    private Prestataire checkInfoPrestatiaire(Prestataire prestataire) {
        LOGGER.info("Checking Prestataire infos: "+prestataire.toString());
        nullCheckPrestatire(prestataire);
        VerifyService.verifyNif(prestataire.getNif());
        VerifyService.verifyStat(prestataire.getStat());
        checkCommpanyName(prestataire.getComanyName());
        checkCinRcs(prestataire.isIsIndividuel(), prestataire.getCin(), prestataire.getRcs());
        VerifyService.verifyOfficeAddress(prestataire.getHeadOfficeAdresse(), prestataire.getCity(),
                String.valueOf(prestataire.getPostalCode()), prestataire.getCountry());
        VerifyService.verifyEmail(prestataire.getEmail());
        //TODO: Record all errors here
        if(CollectionUtils.isNotEmpty(prestataire.getPhoneNumbers()))
            VerifyService.varfyPhoneNumbers(prestataire.getPhoneNumbers());
        return prestataire;
    }

    private void nullCheckPrestatire(Prestataire prestataire) {
        if(Objects.isNull(prestataire)) {
            LOGGER.error("Prestataire is NULL");
            throw new NullPointerException("Prestataire is null");
        }
    }

    private void checkUniqueNif(String nif) {
        Optional<Prestataire> prestataireOptional = prestataireRepository.find("nif", nif).firstResultOptional();
        if(prestataireOptional.isPresent())
            throw new IllegalArgumentException("Prestataire.nif already exist.");
       
    }

    private void checkCommpanyName(String companyName) {
        if(StringUtils.isBlank(companyName)) {
            LOGGER.error("Prestataire.companyName is blank.");
            throw new IllegalArgumentException("Prestataire.companyName is blank.");
        }
    }

    private void checkCinRcs(boolean isIndividuel, String cin, String rcs) {
        if(isIndividuel && StringUtils.isBlank(cin)) {
            LOGGER.error("CIN is blank");
            throw new IllegalArgumentException("CIN is blank");
        }
        else if(StringUtils.isBlank(rcs) && StringUtils.isBlank(cin)){
            LOGGER.error("CIN and RCS is blank");
            throw new IllegalArgumentException("CIN and RCS is blank");
        }
    }

    private Prestataire checkAndGetPrestataire(String nif) {
        LOGGER.info("Getting client with NIF="+nif);
        VerifyService.verifyNif(nif);
        var prestataire =  prestataireRepository.findById(nif);
        if(Objects.isNull(prestataire)) {
            LOGGER.error("Client not found");
            throw new NotFoundException("Client not found.");
        }
        return prestataire;
    }

/* UPDATE METHOD */
    private Prestataire updateStat(Prestataire prestataire, String stat) {
        if(StringUtils.isNotBlank(stat) && !stat.equals(prestataire.getStat())) {
            VerifyService.verifyStat(stat);
            return prestataire.stat(stat);
        }
        return prestataire;
    }
    private Prestataire updateCIN(Prestataire prestataire, String cin) {
        if(StringUtils.isNotBlank(cin) && !cin.equals(prestataire.getCin())) {
            VerifyService.verifyCin(cin);
            return prestataire.cin(cin);
        }
        return prestataire;
    }
    private Prestataire updateCompanyName(Prestataire prestataire, String companyName) {
        if(StringUtils.isNotBlank(companyName) && !companyName.equals(prestataire.getComanyName())) {
            return prestataire.comanyName(companyName);
        }
        return prestataire;
    }
    private Prestataire updateActivity(Prestataire prestataire, String activity) {
        if(StringUtils.isNotBlank(activity) && !activity.equals(prestataire.getActivity())) {
            return prestataire.activity(activity);
        }
        return prestataire;
    }
    private Prestataire updateOfficeAdress(Prestataire prestataire, String officeAddress) {
        if(StringUtils.isNotBlank(officeAddress) && !officeAddress.equals(prestataire.getHeadOfficeAdresse())) {
            return prestataire.headOfficeAdresse(officeAddress);
        }
        return prestataire;
    }
    private Prestataire updateCity(Prestataire prestataire, String city) {
        if(StringUtils.isNotBlank(city) && !city.equals(prestataire.getCity())) {
            return prestataire.city(city);
        }
        return prestataire;
    }
    private Prestataire updatePostalCode(Prestataire prestataire, Integer postalCode) {
        if(postalCode != null 
            && FactureoUtils.validatePostalCode(String.valueOf(postalCode)) 
            && !postalCode.equals(prestataire.getPostalCode())) {
            return prestataire.postalCode(postalCode);
        }
        return prestataire;
    }

    private Prestataire updateEmail(Prestataire prestataire, String email) {
        if(StringUtils.isNotBlank(email) && !email.equals(prestataire.getEmail())) {
            VerifyService.verifyEmail(email);
            return prestataire.email(email);
        }
        return prestataire;
    }

    private Prestataire updatePhoneNumbers(Prestataire prestataire, Set<String> phoneNumbers) {
        if(CollectionUtils.isNotEmpty(phoneNumbers) && !phoneNumbers.equals(prestataire.getPhoneNumbers())) {
            VerifyService.varfyPhoneNumbers(phoneNumbers);
            return prestataire.phoneNumbers(phoneNumbers);
        }
        return prestataire;
    }
}
