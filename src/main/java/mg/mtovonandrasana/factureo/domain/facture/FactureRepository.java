package mg.mtovonandrasana.factureo.domain.facture;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class FactureRepository implements PanacheRepositoryBase<Facture, String> {
    
}
