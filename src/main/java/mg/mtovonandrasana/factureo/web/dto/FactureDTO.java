package mg.mtovonandrasana.factureo.web.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;


public class FactureDTO {

    private String numero;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFacture;
    private String paiementMode;
    private String devise;
    private String echeance;
    private long dejaPayer;
    private long montant;
    private String clientNIF;
    private Set<Long> panniersId = new HashSet<>();


    public FactureDTO() {
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

    public Set<Long> getPanniersId() {
        return this.panniersId;
    }

    public void setPanniersId(Set<Long> panniersId) {
        this.panniersId = panniersId;
    }

    public FactureDTO numero(String numero) {
        setNumero(numero);
        return this;
    }

    public FactureDTO dateFacture(LocalDate dateFacture) {
        setDateFacture(dateFacture);
        return this;
    }

    public FactureDTO paiementMode(String paiementMode) {
        setPaiementMode(paiementMode);
        return this;
    }

    public FactureDTO devise(String devise) {
        setDevise(devise);
        return this;
    }

    public FactureDTO echeance(String echeance) {
        setEcheance(echeance);
        return this;
    }

    public FactureDTO dejaPayer(long dejaPayer) {
        setDejaPayer(dejaPayer);
        return this;
    }

    public FactureDTO montant(long montant) {
        setMontant(montant);
        return this;
    }

    public FactureDTO clientNIF(String clientNIF) {
        setClientNIF(clientNIF);
        return this;
    }

    public FactureDTO panniersId(Set<Long> panniersId) {
        setPanniersId(panniersId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FactureDTO)) {
            return false;
        }
        var factureDTO = (FactureDTO) o;
        return Objects.equals(numero, factureDTO.numero) && Objects.equals(dateFacture, factureDTO.dateFacture)
                && Objects.equals(paiementMode, factureDTO.paiementMode) && Objects.equals(devise, factureDTO.devise)
                && Objects.equals(echeance, factureDTO.echeance) && dejaPayer == factureDTO.dejaPayer
                && montant == factureDTO.montant && Objects.equals(clientNIF, factureDTO.clientNIF)
                && Objects.equals(panniersId, factureDTO.panniersId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, dateFacture, paiementMode, devise, echeance, dejaPayer, montant, clientNIF,
                panniersId);
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
            ", panniersId='" + getPanniersId() + "'" +
            "}";
    }

}
