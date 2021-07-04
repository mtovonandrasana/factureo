package mg.mtovonandrasana.factureo.domain.client;

import java.util.Objects;

import mg.mtovonandrasana.factureo.domain.common.Adresse;

public class Client {

    private String raisonSocial;
    private String nif;
    private String stat;
    private String reference;

    private Adresse headOfficeAddress;


    public Client() {
    }

    public Client(String raisonSocial, 
                    String nif, 
                    String stat, 
                    String reference, 
                    Adresse headOfficeAddress) {
        this.raisonSocial = raisonSocial;
        this.nif = nif;
        this.stat = stat;
        this.reference = reference;
        this.headOfficeAddress = headOfficeAddress;
    }

    public String getRaisonSocial() {
        return this.raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getNif() {
        return this.nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getStat() {
        return this.stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Adresse getHeadOfficeAddress() {
        return this.headOfficeAddress;
    }

    public void setHeadOfficeAddress(Adresse headOfficeAddress) {
        this.headOfficeAddress = headOfficeAddress;
    }

    public Client raisonSocial(String raisonSocial) {
        setRaisonSocial(raisonSocial);
        return this;
    }

    public Client nif(String nif) {
        setNif(nif);
        return this;
    }

    public Client stat(String stat) {
        setStat(stat);
        return this;
    }

    public Client reference(String reference) {
        setReference(reference);
        return this;
    }

    public Client headOfficeAddress(Adresse headOfficeAddress) {
        setHeadOfficeAddress(headOfficeAddress);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        var client = (Client) o;
        return Objects.equals(raisonSocial, client.raisonSocial) 
                && Objects.equals(nif, client.nif) 
                && Objects.equals(stat, client.stat) 
                && Objects.equals(reference, client.reference) 
                && Objects.equals(headOfficeAddress, client.headOfficeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raisonSocial, nif, stat, reference, headOfficeAddress);
    }

    @Override
    public String toString() {
        return "{" +
            " raisonSocial='" + getRaisonSocial() + "'" +
            ", nif='" + getNif() + "'" +
            ", stat='" + getStat() + "'" +
            ", reference='" + getReference() + "'" +
            ", headOfficeAddress='" + getHeadOfficeAddress() + "'" +
            "}";
    }

}
