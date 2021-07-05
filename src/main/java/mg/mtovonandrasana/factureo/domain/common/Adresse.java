package mg.mtovonandrasana.factureo.domain.common;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity(name = "_ADRESSE_")
@Deprecated
public class Adresse {

    @Id
    private String ownerId;
    @NotBlank
    private String address;
    @NotBlank
    private Integer postalCode;
    @NotBlank
    private String city;
    private String country = "Madagascar";

    public Adresse() {
    }


    public Adresse(String ownerId, String address, Integer postalCode, String city, String country) {
        this.ownerId = ownerId;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Adresse ownerId(String ownerId) {
        setOwnerId(ownerId);
        return this;
    }

    public Adresse address(String address) {
        setAddress(address);
        return this;
    }

    public Adresse postalCode(Integer postalCode) {
        setPostalCode(postalCode);
        return this;
    }

    public Adresse city(String city) {
        setCity(city);
        return this;
    }

    public Adresse country(String country) {
        setCountry(country);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Adresse)) {
            return false;
        }
        var adresse = (Adresse) o;
        return Objects.equals(ownerId, adresse.ownerId) && Objects.equals(address, adresse.address)
                && Objects.equals(postalCode, adresse.postalCode) && Objects.equals(city, adresse.city)
                && Objects.equals(country, adresse.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, address, postalCode, city, country);
    }

    @Override
    public String toString() {
        return "{" +
            " ownerId='" + getOwnerId() + "'" +
            ", address='" + getAddress() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }


}
