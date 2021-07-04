package mg.mtovonandrasana.factureo.domain.client;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class ClientService {
    
    public Client creaClient(Client client) {
        if(Objects.nonNull(client)){
            return client;
        }
        return null;
    }

    public Client getClientByNif(String nif) {
        return null;
    }

    public Client updatClient(Client client) {
        return null;
    }

    public boolean deleteClient(String nif) {
        return false;
    }

    public Set<Client> listAllClients(){
        return Collections.emptySet();
    }

}
