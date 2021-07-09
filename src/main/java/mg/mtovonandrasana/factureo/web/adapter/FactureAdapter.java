package mg.mtovonandrasana.factureo.web.adapter;

import mg.mtovonandrasana.factureo.domain.facture.proxy.FactureTO;
import mg.mtovonandrasana.factureo.web.dto.FactureDTO;

public class FactureAdapter {

    private FactureAdapter() {
    }
    
    public static FactureTO factureDTOtoTO(FactureDTO dTO) {
        return new FactureTO().numero(dTO.getNumero())
                                .clientNIF(dTO.getClientNIF())
                                .dateFacture(dTO.getDateFacture())
                                .paiementMode(dTO.getPaiementMode())
                                .devise(dTO.getDevise())
                                .echeance(dTO.getEcheance())
                                .dejaPayer(dTO.getDejaPayer())
                                .montant(dTO.getMontant())
                                .pannierIds(dTO.getPanniersId());
    }
}
