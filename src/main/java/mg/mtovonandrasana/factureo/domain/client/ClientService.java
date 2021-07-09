package mg.mtovonandrasana.factureo.domain.client;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import mg.mtovonandrasana.factureo.domain.common.VerifyService;


@ApplicationScoped
public class ClientService {

    private static final Logger LOG = Logger.getLogger(ClientService.class);

    @Inject
    ClientRepository clientRepository;

    @Transactional
    public Client creaClient(Client clientPojo) {
        LOG.info("Creating client : "+clientPojo.toString());
        var client = checkInfosClient(clientPojo);
        clientRepository.persist(client);
        LOG.info("Client created : "+client.toString());
        return client;
    }

    public Client getClientByNif(String nif) {
        return checkAndGetClient(nif);
    }

    @Transactional
    public Client updatClient(String nif, Client updatedClient) {
        var client = checkAndGetClient(nif);
        LOG.info("Updating Client : "+client.toString()+"to "+updatedClient.toString());
        if(!client.getStat().equals(updatedClient.getStat())) {
            VerifyService.verifyStat(updatedClient.getStat());
            client.setStat(updatedClient.getStat());
        }
        if(StringUtils.isNotBlank(updatedClient.getRaisonSocial()) 
            && !client.getRaisonSocial().equals(updatedClient.getRaisonSocial())) {
            client.setRaisonSocial(updatedClient.getRaisonSocial());
        }
        if(StringUtils.isNotBlank(updatedClient.getHeadOfficeAddress()) 
            && !client.getHeadOfficeAddress().equals(updatedClient.getHeadOfficeAddress())) {
            client.setHeadOfficeAddress(updatedClient.getHeadOfficeAddress());
        }
        if(StringUtils.isNotBlank(updatedClient.getCity()) 
             && !client.getCity().equals(updatedClient.getCity())) {
            client.setCity(updatedClient.getCity());
        }
        if(Objects.nonNull(updatedClient.getPostalCode()) 
             && !client.getPostalCode().equals(updatedClient.getPostalCode())) {
            client.setPostalCode(updatedClient.getPostalCode());
        }
        LOG.info("Client Updated: "+client.toString());
        return client;
    }

    @Transactional
    public boolean deleteClient(String nif) {
        var client = checkAndGetClient(nif);
        LOG.info("Deleting Client = "+client.toString());
        Boolean isDeleted = clientRepository.deleteById(nif);
        LOG.info("Client deleted: "+isDeleted);
        return isDeleted; 
    }

    public Set<Client> listAllClients() {
        LOG.info("Collecting all clients.");
        return clientRepository.streamAll().collect(Collectors.toSet());
    }

    private Client checkInfosClient(Client clientPojo) {
        LOG.info("Checking client infos");
        if (Objects.isNull(clientPojo)){
            LOG.error("Client is null");
            throw new NullPointerException("Client is Null");
        }
        VerifyService.verifyNif(clientPojo.getNif());
        VerifyService.verifyStat(clientPojo.getStat());
        if (StringUtils.isBlank(clientPojo.getRaisonSocial())) {
            LOG.error("Raison social is blank.");
            throw new IllegalArgumentException("Raison social is blank.");
        }
        VerifyService.verifyOfficeAddress(clientPojo.getHeadOfficeAddress(), clientPojo.getCity(),
                String.valueOf(clientPojo.getPostalCode()), clientPojo.getCountry());
        return clientPojo;
    }

    private Client checkAndGetClient(String nif) {
        LOG.info("Getting client with NIF="+nif);
        VerifyService.verifyNif(nif);
        var client =  clientRepository.findById(nif);
        if(Objects.isNull(client)) {
            LOG.error("Client not found");
            throw new NotFoundException("Client not found.");
        }
        return client;
    }

}
