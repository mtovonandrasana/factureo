package mg.mtovonandrasana.factureo.domain.prestation;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PanierRepository implements PanacheRepository<Panier> {
    
}
