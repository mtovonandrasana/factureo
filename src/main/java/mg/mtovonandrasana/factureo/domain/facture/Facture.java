package mg.mtovonandrasana.factureo.domain.facture;

import java.time.LocalDate;

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

}
