package mg.mtovonandrasana.factureo.domain.facture;

import java.time.LocalDate;
import java.util.HashSet;
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

import mg.mtovonandrasana.factureo.domain.client.Client;
import mg.mtovonandrasana.factureo.domain.client.ClientService;
import mg.mtovonandrasana.factureo.domain.facture.proxy.FactureTO;
import mg.mtovonandrasana.factureo.domain.prestation.Panier;
import mg.mtovonandrasana.factureo.domain.prestation.PanierService;

@ApplicationScoped
public class FactureService {

    private static final Logger LOGGER = Logger.getLogger(FactureService.class);
    
    @Inject
    FactureRepository factureRepository;
    @Inject
    ClientService clientService;
    @Inject
    PanierService panierService;

    @Transactional
    public Facture createFacture(FactureTO factureTO) {
        var facture = new Facture();
        facture.numero(checkUniqueNumero(factureTO.getNumero()));
        facture.dateFacture(checkDate(factureTO.getDateFacture()));
        facture.client(checkGetAndCheckClient(factureTO.getClientNIF()));
        facture.devise(checkDevise(factureTO.getDevise()));
        facture.echeance(checkEcheance(factureTO.getEcheance()));
        facture.paiementMode(checkPaiementMode(factureTO.getPaiementMode()));
        facture.paniers(checkAndGetPaniersByIds(factureTO.getPannierIds()));
        facture.montant(calculMontantFacture(facture.getPaniers()));
        
        LOGGER.info("Persisting Facture = "+facture.toString());
        factureRepository.persist(facture);

        return facture;
    }

    public Facture getFacture(String numero) {
        return readFacture(numero);
    }

    @Transactional
    public Facture updateFacture(String numero, FactureTO factureTO) {
        LOGGER.debug("Updating facture.");
        var facture = readFacture(numero);
        LOGGER.info("Update facture = "+facture.toString()+" to "+factureTO.toString());
        updateDateFacture(facture, factureTO.getDateFacture());
        updateDevise(facture, factureTO.getDevise());
        updatePaimentMode(facture, factureTO.getPaiementMode());
        updateEcheance(facture, factureTO.getEcheance());
        updateDejaPayer(facture, factureTO.getDejaPayer());
        updatePaniers(facture, factureTO.getPannierIds());
        LOGGER.info("Facture updated "+facture.toString());
        return facture;
    }

    @Transactional
    public boolean deleteFacture(String numero) {
        var facture = readFacture(numero);
        LOGGER.info("Deleting facture "+facture.toString());
        boolean isDeleted = factureRepository.deleteById(facture.getNumero());
        LOGGER.info("Facture deleted: "+isDeleted);
        return isDeleted;
    }

    public Set<Facture> listAllFactures() {
        LOGGER.info("Collecting all factures");
        return factureRepository.streamAll().collect(Collectors.toSet());
    }

    public void printFacture(Facture facture) {
        // Not used yet
        /* or should be in Front-end */
    }

// tag:checkMethods[]

    private LocalDate checkDate(LocalDate dateFacture) {
        if(Objects.isNull(dateFacture)) {
            LOGGER.error("Facture.date is null.");
            throw new NullPointerException("Facture.date is null.");
        }
        return dateFacture;
    }

    private String checkPaiementMode(String paiementMode) {
        if(StringUtils.isBlank(paiementMode)) {
            LOGGER.error("Facture.paiementMode is blank.");
            throw new IllegalArgumentException("Facture.paiementMode is blank.");
        }
        return paiementMode;
    }
    
    private String checkDevise(String devise) {
        if(StringUtils.isBlank(devise)) {
            LOGGER.error("Facture.devise is blank.");
            throw new IllegalArgumentException("Facture.devise is blank.");
        }
        return devise;
    }

    private String checkEcheance(String echeance) {
        if(StringUtils.isBlank(echeance)) {
            LOGGER.error("Facture.echeance is blank.");
            throw new IllegalArgumentException("Facture.echeance is blank.");
        }
        return echeance;
    }

    private Client checkGetAndCheckClient(String nif) {
        var client = clientService.getClientByNif(nif);
        if(Objects.isNull(client)) {
            LOGGER.error("Facture.client is null.");
            throw new NullPointerException("Facture.client is null.");
        }
        return client;
    }

    private Set<Panier> checkAndGetPaniersByIds(Set<Long> panierIds) {
        Set<Panier> paniers = new HashSet<>();
        if(CollectionUtils.isNotEmpty(panierIds)) {
            panierIds.stream().filter(Objects::nonNull)
                    .forEach(panairId -> paniers.add(panierService.readPanier(panairId)));
        }
        if(CollectionUtils.isEmpty(paniers)) {
            LOGGER.error("Facture.paniers is empty.");
            throw new IllegalArgumentException("Facture.paniers is empty.");
        }
        return paniers;
    }

    private String checkUniqueNumero(String numero) {
        if(StringUtils.isBlank(numero)) {
            LOGGER.error("Numero is blank.");
            throw new NullPointerException("Numero is blank.");
        }
        Optional<Facture> factureOp = factureRepository.findByIdOptional(numero);
        if(factureOp.isPresent()) {
            LOGGER.error("Facture.numero already exist: "+numero);
            throw new IllegalArgumentException("Facture.numero already exist: "+numero);
        }
        return numero;
    }

// tag::calculs[]
    private long calculMontantFacture(Set<Panier> paniers) {
        if(CollectionUtils.isNotEmpty(paniers)) {
            return paniers.stream().filter(Objects::nonNull).mapToLong(p -> p.getQuantity() * p.getMarchandise().getPrixUnitaire()).sum();
        }
        return 0;
    }

//tag::getters[]
    private Facture readFacture(String numero) {
        LOGGER.info("Getting facture with numero = "+numero);
        if(StringUtils.isBlank(numero)) {
            LOGGER.error("Numero is blank.");
            throw new NullPointerException("Numero is blank.");
        }
        Optional<Facture> factureOp = factureRepository.findByIdOptional(numero);
        if(factureOp.isEmpty()) {
            LOGGER.error("Facture not found.");
            throw new NotFoundException("Facture not found.");
        }
        LOGGER.info("Facutre readed "+factureOp.get().toString());
        return factureOp.get();
    }

//tag::updateMethods[] 
    private void updatePaniers(Facture facture, Set<Long> panierIds) {
        Set<Panier> paniers = new HashSet<>();
        if (CollectionUtils.isNotEmpty(panierIds)) {
            panierIds.stream().filter(Objects::nonNull)
                    .forEach(panairId -> paniers.add(panierService.readPanier(panairId)));
        }
        if (!facture.getPaniers().equals(paniers)) {
            facture.paniers(paniers);
            facture.montant(calculMontantFacture(paniers));
        }
    }

    private void updateDejaPayer(Facture facture, long dejaPayer) {
        if(dejaPayer > 0 && dejaPayer != facture.getDejaPayer())
            facture.dejaPayer(dejaPayer);
    }

    private void updatePaimentMode(Facture facture, String paiementMode) {
        if(StringUtils.isNotBlank(paiementMode) && !paiementMode.equals(facture.getPaiementMode()))
            facture.paiementMode(paiementMode);
    }

    private void updateEcheance(Facture facture, String echeance) {
        if(StringUtils.isNotBlank(echeance) && !echeance.equals(facture.getEcheance()))
            facture.echeance(echeance);
    }

    private void updateDevise(Facture facture, String devise) {
        if(StringUtils.isNotBlank(devise) && !devise.equals(facture.getDevise()))
            facture.devise(devise);
    }

    private void updateDateFacture(Facture facture, LocalDate dateFacture) {
        if(Objects.nonNull(dateFacture) && !dateFacture.equals(facture.getDateFacture()))
            facture.dateFacture(dateFacture);
    }
}
