package mg.mtovonandrasana.factureo.domain.common;

import java.util.Objects;

public class Adresse {

    private Long addressId;
    private String address;
    private Integer postalCode;
    private String city;
    private String country = "Madagascar";

    public Adresse() {
    }

    public Adresse(Long addressId, String address, Integer postalCode, String city, String country) {
        this.addressId = addressId;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public Adresse addressId(Long addressId) {
        setAddressId(addressId);
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
        return Objects.equals(addressId, adresse.addressId) && Objects.equals(address, adresse.address)
                && Objects.equals(postalCode, adresse.postalCode) && Objects.equals(city, adresse.city)
                && Objects.equals(country, adresse.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, postalCode, city, country);
    }

    @Override
    public String toString() {
        return "{" + " addressId='" + getAddressId() + "'" + ", address='" + getAddress() + "'" + ", postalCode='"
                + getPostalCode() + "'" + ", city='" + getCity() + "'" + ", country='" + getCountry() + "'" + "}";
    }

}
