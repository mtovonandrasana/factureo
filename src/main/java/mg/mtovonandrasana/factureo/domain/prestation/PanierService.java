package mg.mtovonandrasana.factureo.domain.prestation;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.jboss.logging.Logger;

@ApplicationScoped
public class PanierService {
    
    private static final Logger LOGGER = Logger.getLogger(PanierService.class);

    @Inject
    PanierRepository panierRepository;

    @Transactional
    public Panier createPanier(Panier panierPojo) {
        var panier = checkPanier(panierPojo);
        panierRepository.persist(panier);
        return panier;
    }

    public Panier readPanier(Long panierId) {
        LOGGER.debug("Finding Panier with panierId = "+panierId);
        var panier = panierRepository.findById(panierId);
        if(Objects.isNull(panier)) {
            LOGGER.error("Panier with panierId = "+panierId+" not found.");
            throw new NotFoundException("Panier not found");
        }
        return panier;
    }

    @Transactional
    public Panier updatePanier(Long panierId, Panier updatedPanier) {
        var panier = readPanier(panierId);
        if(panier.getQuantity() != updatedPanier.getQuantity()) {
            checkPanierQuanity(updatedPanier.getQuantity());
            panier.setQuantity(updatedPanier.getQuantity());
        }
        if(Objects.nonNull(panier.getMarchandise()) 
            && !panier.getMarchandise().equals(updatedPanier.getMarchandise())){
            if(!panier.getMarchandise().getReference().equals(updatedPanier.getMarchandise().getReference())) {
                throw new IllegalArgumentException("Marchandise.reference not updatable");
            }
            panier.setMarchandise(updatedPanier.getMarchandise());
        }
        return panier;
    }

    @Transactional
    public boolean deletePanier(Long panierId) {
        var panier = readPanier(panierId);
        LOGGER.info("Deleting Panier with panierId = "+panierId);
        boolean isDeleted = panierRepository.deleteById(panier.getId());
        LOGGER.info("Panier deleted : "+isDeleted);
        return isDeleted;
    }

    public Set<Panier> listAllPaniers() {
        LOGGER.info("Getting all paniers!");
        return panierRepository.streamAll().collect(Collectors.toSet());
    }

    private Panier checkPanier(Panier panier) {
        checkPanierIfNull(panier);
        checkPanierQuanity(panier.getQuantity());
        checkPanierMarchandise(panier.getMarchandise());
        return panier;
    }

    private void checkPanierIfNull(Panier panier) {
        if(Objects.isNull(panier))
            throw new NullPointerException("Painer is Null");
    }

    private void checkPanierQuanity(Integer quantity) {
        if(Objects.isNull(quantity) || quantity <= 0) {
                throw new IllegalArgumentException("Panier.quantity is null or 0 or negative.");
        }
    }

    private void checkPanierMarchandise(Marchandise marchandise) {
        if(Objects.isNull(marchandise))
            throw new NullPointerException("Panier.marchandise is Null");
    }
}
