package mg.mtovonandrasana.factureo.domain.client;

import java.util.Objects;

public class Client {

    private String raisonSocial;
    private String nif;
    private String stat;
    private String reference;

    // TODO: add Client address here

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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        var client = (Client) o;
        return Objects.equals(raisonSocial, client.raisonSocial) && Objects.equals(nif, client.nif)
                && Objects.equals(stat, client.stat) && Objects.equals(reference, client.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raisonSocial, nif, stat, reference);
    }

}
