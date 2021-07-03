package mg.mtovonandrasana.factureo.domain.facture;

import java.time.LocalDate;

public class Facture {
    
    private String numero;
    private LocalDate date;
    private String paiementMode;
    private String devise;
    private String echeance;
    private Long restAPayer;
    private Long dejaPayer;
    private Long montant;

    // TODO: add Client here
    // TODO: add Prestataire here
    // TODO: 


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

    public Long getRestAPayer() {
        return this.restAPayer;
    }

    public void setRestAPayer(Long restAPayer) {
        this.restAPayer = restAPayer;
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
