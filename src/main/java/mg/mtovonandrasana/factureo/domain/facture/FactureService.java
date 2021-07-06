package mg.mtovonandrasana.factureo.domain.facture;

import java.util.Collections;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class FactureService {
    
    @Transactional
    public Facture createFacture(Facture facture) {
        return null;
    }

    public Facture getFacture(String numero) {
        return null;
    }

    @Transactional
    public Facture updateFacture(Facture facture) {
        return null;
    }

    @Transactional
    public boolean deleteFacture(String numero) {
        return false;
    }

    public Set<Facture> listAllFactures() {
        return Collections.emptySet();
    }

    public void printFacture(Facture facture) {
        // Not used yet
        /* or should be in Front-end */
    }
}
