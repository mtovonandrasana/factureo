package mg.mtovonandrasana.factureo.domain.client;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ClientRepository implements PanacheRepositoryBase<Client, String> {
    
}
