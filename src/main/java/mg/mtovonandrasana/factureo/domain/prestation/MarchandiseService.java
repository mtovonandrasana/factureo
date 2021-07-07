package mg.mtovonandrasana.factureo.domain.prestation;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MarchandiseService {

    private static final Logger LOGGER = Logger.getLogger(MarchandiseService.class);
    
    @Inject
    MarchandiseRepository marchandiseRepository;
    
    @Transactional
    public Marchandise createMarchandise(Marchandise marchandise) {
        LOGGER.info("Creating Marchandise "+marchandise.toString());
        checkMarchandise(marchandise);
        checkUniqueReference(marchandise.getReference());
        LOGGER.info("Persisting Marchandise "+marchandise.toString());
        marchandiseRepository.persist(marchandise);
       return marchandise; 
    }

    public Marchandise getMarchandise(String reference) {
        LOGGER.info("Getting Marchandise with reference = "+reference);
        Optional<Marchandise> marchandiseOptional = marchandiseRepository.findByIdOptional(reference);
        if(marchandiseOptional.isEmpty()) { 
            LOGGER.error("Marchandise with reference = "+reference+" not found.");
            throw new NotFoundException("Marchandise not found.");
        }
        return marchandiseOptional.get();
    }

    @Transactional
    public Marchandise updateMarchandise(String reference, Marchandise  updatedMarchandise) {
        var marchandise = getMarchandise(reference);
        LOGGER.info("Updating Marchandise with reference = "+reference);
        if(StringUtils.isNotBlank(updatedMarchandise.getDescription())
            &&!marchandise.getDescription().equals(updatedMarchandise.getDescription())) {
            marchandise.setDescription(updatedMarchandise.getDescription());
        }
        if(StringUtils.isNotBlank(updatedMarchandise.getUnite())
            &&!marchandise.getUnite().equals(updatedMarchandise.getUnite())) {
            marchandise.setUnite(updatedMarchandise.getUnite());
        }
        if(Objects.nonNull(updatedMarchandise.getPrixUnitaire()) 
            && updatedMarchandise.getPrixUnitaire() >= 0) {
            marchandise.setPrixUnitaire(updatedMarchandise.getPrixUnitaire());
        }
        return marchandise;
    }

    @Transactional
    public boolean deleteMarchandise(String reference) {
        var marchandise = getMarchandise(reference);
        LOGGER.info("Deleting Marchandise with reference = "+reference);
        boolean isDeleted = marchandiseRepository.deleteById(marchandise.getReference());
        LOGGER.info("Marchandise deleted : "+isDeleted);
        return isDeleted;
    }

    public Set<Marchandise> listAllMarchandises() {
        return Collections.emptySet();
    }

    private void checkMarchandise(Marchandise marchandise) {
        if(Objects.isNull(marchandise)) 
            throw new NullPointerException("Marchandise is Null");
        if(StringUtils.isBlank(marchandise.getReference()))
            throw new IllegalArgumentException("Marchandise.reference is null or empty or blanked");
        if(StringUtils.isBlank(marchandise.getDescription())) {
            throw new IllegalArgumentException("Marchandise.description is null or empty or blanked");
        }
        if(StringUtils.isBlank(marchandise.getUnite())) {
            throw new IllegalArgumentException("Marchandise.unite is null or empty or blanked");
        }
        if(Objects.isNull(marchandise.getPrixUnitaire())) {
            throw new IllegalArgumentException("Marchandise.prixUnitaire is null or empty or blanked");
        }
        if(marchandise.getPrixUnitaire() <= 0) {
            throw new IllegalArgumentException("Marchandise.prixUnitaire is zero (0) or negative");
        }
    }

    private void checkUniqueReference(String referene) {
        Optional<Marchandise> marchandiseOptional = marchandiseRepository.find("reference", referene).firstResultOptional();
        if(marchandiseOptional.isPresent())
            throw new IllegalArgumentException("Marchandise.reference already exist.");
       
    }
}
