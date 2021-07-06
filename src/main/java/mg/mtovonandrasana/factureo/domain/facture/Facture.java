package mg.mtovonandrasana.factureo.domain.facture;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;
import mg.mtovonandrasana.factureo.domain.client.Client;
import mg.mtovonandrasana.factureo.domain.prestation.Panier;

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
    private long dejaPayer;
    @NotNull
    private long montant;

    @ManyToOne
    @JoinColumn(name = "nif")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Panier> paniers = new HashSet<>();

    public Facture() {
        // default constructor
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

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Panier> getPaniers() {
        return this.paniers;
    }

    public void setPaniers(Set<Panier> paniers) {
        this.paniers = paniers;
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

    public Facture dejaPayer(long dejaPayer) {
        setDejaPayer(dejaPayer);
        return this;
    }

    public Facture montant(long montant) {
        setMontant(montant);
        return this;
    }

    public Facture client(Client client) {
        setClient(client);
        return this;
    }

    public Facture paniers(Set<Panier> paniers) {
        setPaniers(paniers);
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
                && Objects.equals(echeance, facture.echeance) && dejaPayer == facture.dejaPayer
                && montant == facture.montant && Objects.equals(client, facture.client)
                && Objects.equals(paniers, facture.paniers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, date, paiementMode, devise, echeance, dejaPayer, montant, client, paniers);
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
            ", client='" + getClient() + "'" +
            ", paniers='" + getPaniers() + "'" +
            "}";
    }

}
