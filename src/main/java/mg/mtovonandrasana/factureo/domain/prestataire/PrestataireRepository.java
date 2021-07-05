package mg.mtovonandrasana.factureo.domain.prestataire;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PrestataireRepository implements PanacheRepositoryBase<Prestataire, String> {
    
}
