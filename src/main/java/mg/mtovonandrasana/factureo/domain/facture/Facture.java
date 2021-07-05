package mg.mtovonandrasana.factureo.domain.facture;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

@Entity(name = "_FACTURE_")
public class Facture {
    
    @Id
    private String numero;
    @NotBlank
    private LocalDate date;
    @NotBlank
    private String paiementMode;
    @NotBlank
    private String devise;
    @NotBlank
    private String echeance;
    private Long dejaPayer;
    @NotNull
    private Long montant;

    // TODO: add Client here
    // TODO: add Prestataire here
    // TODO: add liste marchandise/quantuté here: example +> Map<Marchanise, Integer> 


    public Facture() {
    }

    public Facture(String numero, LocalDate date, String paiementMode, String devise, String echeance, Long dejaPayer, Long montant) {
        this.numero = numero;
        this.date = date;
        this.paiementMode = paiementMode;
        this.devise = devise;
        this.echeance = echeance;
        this.dejaPayer = dejaPayer;
        this.montant = montant;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Long getDejaPayer() {
        return this.dejaPayer;
    }

    public void setDejaPayer(Long dejaPayer) {
        this.dejaPayer = dejaPayer;
    }

    public Long getMontant() {
        return this.montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Facture numero(String numero) {
        setNumero(numero);
        return this;
    }

    public Facture date(LocalDate date) {
        setDate(date);
        return this;
    }

    public Facture paiementMode(String paiementMode) {
        setPaiementMode(paiementMode);
        return this;
    }

    public Facture devise(String devise) {
        setDevise(devise);
        return this;
    }

    public Facture echeance(String echeance) {
        setEcheance(echeance);
        return this;
    }

    public Facture dejaPayer(Long dejaPayer) {
        setDejaPayer(dejaPayer);
        return this;
    }

    public Facture montant(Long montant) {
        setMontant(montant);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Facture)) {
            return false;
        }
        var facture = (Facture) o;
        return Objects.equals(numero, facture.numero) && Objects.equals(date, facture.date)
                && Objects.equals(paiementMode, facture.paiementMode) && Objects.equals(devise, facture.devise)
                && Objects.equals(echeance, facture.echeance) && Objects.equals(dejaPayer, facture.dejaPayer)
                && Objects.equals(montant, facture.montant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, date, paiementMode, devise, echeance, dejaPayer, montant);
    }

    @Override
    public String toString() {
        return "{" +
            " numero='" + getNumero() + "'" +
            ", date='" + getDate() + "'" +
            ", paiementMode='" + getPaiementMode() + "'" +
            ", devise='" + getDevise() + "'" +
            ", echeance='" + getEcheance() + "'" +
            ", dejaPayer='" + getDejaPayer() + "'" +
            ", montant='" + getMontant() + "'" +
            "}";
    }


}
