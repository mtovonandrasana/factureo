package mg.mtovonandrasana.factureo.domain.facture.proxy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FactureTO {

    private String numero;
    private LocalDate dateFacture;
    private String paiementMode;
    private String devise;
    private String echeance;
    private long dejaPayer;
    private long montant;
    private String clientNIF;
    private Set<Long> pannierIds = new HashSet<>();


    public FactureTO() {
        /* default constructor */
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDateFacture() {
        return this.dateFacture;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getPaiementMode() {
        return this.paiementMode;
    }

    public void setPaiementMode(String paiementMode) {
        this.paiementMode = paiementMode;
    }

    public String getDevise() {
        return this.devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getEcheance() {
        return this.echeance;
    }

    public void setEcheance(String echeance) {
        this.echeance = echeance;
    }

    public long getDejaPayer() {
        return this.dejaPayer;
    }

    public void setDejaPayer(long dejaPayer) {
        this.dejaPayer = dejaPayer;
    }

    public long getMontant() {
        return this.montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }

    public String getClientNIF() {
        return this.clientNIF;
    }

    public void setClientNIF(String clientNIF) {
        this.clientNIF = clientNIF;
    }

    public Set<Long> getPannierIds() {
        return this.pannierIds;
    }

    public void setPannierIds(Set<Long> pannierIds) {
        this.pannierIds = pannierIds;
    }

    public FactureTO numero(String numero) {
        setNumero(numero);
        return this;
    }

    public FactureTO dateFacture(LocalDate dateFacture) {
        setDateFacture(dateFacture);
        return this;
    }

    public FactureTO paiementMode(String paiementMode) {
        setPaiementMode(paiementMode);
        return this;
    }

    public FactureTO devise(String devise) {
        setDevise(devise);
        return this;
    }

    public FactureTO echeance(String echeance) {
        setEcheance(echeance);
        return this;
    }

    public FactureTO dejaPayer(long dejaPayer) {
        setDejaPayer(dejaPayer);
        return this;
    }

    public FactureTO montant(long montant) {
        setMontant(montant);
        return this;
    }

    public FactureTO clientNIF(String clientNIF) {
        setClientNIF(clientNIF);
        return this;
    }

    public FactureTO pannierIds(Set<Long> pannierIds) {
        setPannierIds(pannierIds);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FactureTO)) {
            return false;
        }
        var factureTO = (FactureTO) o;
        return Objects.equals(numero, factureTO.numero) && Objects.equals(dateFacture, factureTO.dateFacture)
                && Objects.equals(paiementMode, factureTO.paiementMode) && Objects.equals(devise, factureTO.devise)
                && Objects.equals(echeance, factureTO.echeance) && dejaPayer == factureTO.dejaPayer
                && montant == factureTO.montant && Objects.equals(clientNIF, factureTO.clientNIF)
                && Objects.equals(pannierIds, factureTO.pannierIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, dateFacture, paiementMode, devise, echeance, dejaPayer, montant, clientNIF,
                pannierIds);
    }

    @Override
    public String toString() {
        return "{" +
            " numero='" + getNumero() + "'" +
            ", dateFacture='" + getDateFacture() + "'" +
            ", paiementMode='" + getPaiementMode() + "'" +
            ", devise='" + getDevise() + "'" +
            ", echeance='" + getEcheance() + "'" +
            ", dejaPayer='" + getDejaPayer() + "'" +
            ", montant='" + getMontant() + "'" +
            ", clientNIF='" + getClientNIF() + "'" +
            ", pannierIds='" + getPannierIds() + "'" +
            "}";
    }

}
