package mg.mtovonandrasana.factureo.domain.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;


@ApplicationScoped
public class ClientService {

    private static final Logger LOG = Logger.getLogger(ClientService.class);

    @Inject
    ClientRepository clientRepository;

    @Transactional
    public Client creaClient(Client clientPojo) {
        var client = checkInfosClient(clientPojo);
        clientRepository.persist(client);
        return client;
    }

    public Client getClientByNif(String nif) {
        return checkAndGetClient(nif);
    }

    public Client updatClient(Client client) {

        return null;
    }

    public boolean deleteClient(String nif) {
        return clientRepository.deleteById(nif);
    }

    public Set<Client> listAllClients() {
        Set<Client> clients = new HashSet<>();
        clients.addAll(clientRepository.listAll());
        if(CollectionUtils.isNotEmpty(clients)) {
            return clients;
        }
        return Collections.emptySet();
    }

    private Client checkInfosClient(Client clientPojo) {
        if (Objects.isNull(clientPojo))
            throw new NullPointerException("Client is Null");
        if (StringUtils.isBlank(clientPojo.getNif()))
            throw new IllegalArgumentException("Nif is blank.");
        if (StringUtils.isBlank(clientPojo.getStat()))
            throw new IllegalArgumentException("STAT is blank.");
        if (StringUtils.isBlank(clientPojo.getRaisonSocial()))
            throw new IllegalArgumentException("Raison social is blank.");
        if (Objects.isNull(clientPojo.getHeadOfficeAddress()))
            throw new NullPointerException("Office Address is null.");
        
        return clientPojo;
    }

    private Client checkAndGetClient(String nif) {
        if(StringUtils.isBlank(nif))
            throw new IllegalArgumentException("NIF is empty or blank.");
        validateNif(nif);
        var client =  clientRepository.findById(nif);
        if(Objects.isNull(client))
            throw new NotFoundException("Client not found.");
        return client;
    }

    // TODO: this method should be moved to a Common ot Utility class
    private void validateNif(String nif) {
        // TODO: add NIF validation here
    }

    // TODO: this method should be moved to a Common ot Utility class
    private void validateStat(String stat) {
        // TODO: add STAT validation here
    }

}
