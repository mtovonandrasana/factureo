package mg.mtovonandrasana.factureo.domain.prestation;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class MarchandiseRepository implements PanacheRepositoryBase<Marchandise, String> {
    
}
