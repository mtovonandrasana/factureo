package mg.mtovonandrasana.factureo.domain.client;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import mg.mtovonandrasana.factureo.domain.common.Adresse;

@Entity(name = "_CLIENT_")
public class Client {

    @Id
    private String nif;
    @NotBlank
    private String stat;
    @NotBlank
    private String raisonSocial;
    @NotBlank
    private String reference;
    @NotBlank
    private String headOfficeAddress;
    @NotBlank
    private Integer postalCode;
    @NotBlank
    private String city;
    private String country = "Madagascar";


    public Client() {
    }

    public Client(String nif, String stat, String raisonSocial, String reference, String headOfficeAddress, Integer postalCode, String city, String country) {
        this.nif = nif;
        this.stat = stat;
        this.raisonSocial = raisonSocial;
        this.reference = reference;
        this.headOfficeAddress = headOfficeAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
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

    public String getRaisonSocial() {
        return this.raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getHeadOfficeAddress() {
        return this.headOfficeAddress;
    }

    public void setHeadOfficeAddress(String headOfficeAddress) {
        this.headOfficeAddress = headOfficeAddress;
    }

    public Integer getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Client nif(String nif) {
        setNif(nif);
        return this;
    }

    public Client stat(String stat) {
        setStat(stat);
        return this;
    }

    public Client raisonSocial(String raisonSocial) {
        setRaisonSocial(raisonSocial);
        return this;
    }

    public Client reference(String reference) {
        setReference(reference);
        return this;
    }

    public Client headOfficeAddress(String headOfficeAddress) {
        setHeadOfficeAddress(headOfficeAddress);
        return this;
    }

    public Client postalCode(Integer postalCode) {
        setPostalCode(postalCode);
        return this;
    }

    public Client city(String city) {
        setCity(city);
        return this;
    }

    public Client country(String country) {
        setCountry(country);
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
        return Objects.equals(nif, client.nif) && Objects.equals(stat, client.stat)
                && Objects.equals(raisonSocial, client.raisonSocial) && Objects.equals(reference, client.reference)
                && Objects.equals(headOfficeAddress, client.headOfficeAddress)
                && Objects.equals(postalCode, client.postalCode) && Objects.equals(city, client.city)
                && Objects.equals(country, client.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, stat, raisonSocial, reference, headOfficeAddress, postalCode, city, country);
    }

    @Override
    public String toString() {
        return "{" +
            " nif='" + getNif() + "'" +
            ", stat='" + getStat() + "'" +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", reference='" + getReference() + "'" +
            ", headOfficeAddress='" + getHeadOfficeAddress() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }


}
