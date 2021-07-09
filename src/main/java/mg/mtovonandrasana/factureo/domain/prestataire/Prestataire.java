package mg.mtovonandrasana.factureo.domain.prestataire;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity(name = "_PRESTATAIRE_")
public class Prestataire {
    @Id
    private String nif;

    @NotBlank
    private String stat;
    @NotBlank
    private String comanyName;
    private String rcs;
    private String cin;
    @NotBlank
    private String activity;
    private String responsableName;
    private boolean isIndividuel;
    private byte[] signature;

    @NotBlank
    private String headOfficeAdresse;
    @NotBlank
    private Integer postalCode;
    @NotBlank
    private String city;
    private String country = "Madagascar";
    private String email;

    @ElementCollection
    private Set<String> phoneNumbers = new HashSet<>();


    public Prestataire() {
        // Default constructor
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

    public String getComanyName() {
        return this.comanyName;
    }

    public void setComanyName(String comanyName) {
        this.comanyName = comanyName;
    }

    public String getRcs() {
        return this.rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public String getCin() {
        return this.cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getResponsableName() {
        return this.responsableName;
    }

    public void setResponsableName(String responsableName) {
        this.responsableName = responsableName;
    }

    public boolean isIsIndividuel() {
        return this.isIndividuel;
    }

    public boolean getIsIndividuel() {
        return this.isIndividuel;
    }

    public void setIsIndividuel(boolean isIndividuel) {
        this.isIndividuel = isIndividuel;
    }

    public byte[] getSignature() {
        return this.signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getHeadOfficeAdresse() {
        return this.headOfficeAdresse;
    }

    public void setHeadOfficeAdresse(String headOfficeAdresse) {
        this.headOfficeAdresse = headOfficeAdresse;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Prestataire nif(String nif) {
        setNif(nif);
        return this;
    }

    public Prestataire stat(String stat) {
        setStat(stat);
        return this;
    }

    public Prestataire comanyName(String comanyName) {
        setComanyName(comanyName);
        return this;
    }

    public Prestataire rcs(String rcs) {
        setRcs(rcs);
        return this;
    }

    public Prestataire cin(String cin) {
        setCin(cin);
        return this;
    }

    public Prestataire activity(String activity) {
        setActivity(activity);
        return this;
    }

    public Prestataire responsableName(String responsableName) {
        setResponsableName(responsableName);
        return this;
    }

    public Prestataire isIndividuel(boolean isIndividuel) {
        setIsIndividuel(isIndividuel);
        return this;
    }

    public Prestataire signature(byte[] signature) {
        setSignature(signature);
        return this;
    }

    public Prestataire headOfficeAdresse(String headOfficeAdresse) {
        setHeadOfficeAdresse(headOfficeAdresse);
        return this;
    }

    public Prestataire postalCode(Integer postalCode) {
        setPostalCode(postalCode);
        return this;
    }

    public Prestataire city(String city) {
        setCity(city);
        return this;
    }

    public Prestataire country(String country) {
        setCountry(country);
        return this;
    }

    public Prestataire email(String email) {
        setEmail(email);
        return this;
    }

    public Prestataire phoneNumbers(Set<String> phoneNumbers) {
        setPhoneNumbers(phoneNumbers);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Prestataire)) {
            return false;
        }
        var prestataire = (Prestataire) o;
        return Objects.equals(nif, prestataire.nif) && Objects.equals(stat, prestataire.stat)
                && Objects.equals(comanyName, prestataire.comanyName) && Objects.equals(rcs, prestataire.rcs)
                && Objects.equals(cin, prestataire.cin) && Objects.equals(activity, prestataire.activity)
                && Objects.equals(responsableName, prestataire.responsableName)
                && isIndividuel == prestataire.isIndividuel && Objects.equals(signature, prestataire.signature)
                && Objects.equals(headOfficeAdresse, prestataire.headOfficeAdresse)
                && Objects.equals(postalCode, prestataire.postalCode) && Objects.equals(city, prestataire.city)
                && Objects.equals(country, prestataire.country) && Objects.equals(email, prestataire.email)
                && Objects.equals(phoneNumbers, prestataire.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, stat, comanyName, rcs, cin, activity, responsableName, isIndividuel, signature,
                headOfficeAdresse, postalCode, city, country, email, phoneNumbers);
    }

    @Override
    public String toString() {
        return "{" +
            " nif='" + getNif() + "'" +
            ", stat='" + getStat() + "'" +
            ", comanyName='" + getComanyName() + "'" +
            ", rcs='" + getRcs() + "'" +
            ", cin='" + getCin() + "'" +
            ", activity='" + getActivity() + "'" +
            ", responsableName='" + getResponsableName() + "'" +
            ", isIndividuel='" + isIsIndividuel() + "'" +
            ", signature='" + getSignature() + "'" +
            ", headOfficeAdresse='" + getHeadOfficeAdresse() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumbers='" + getPhoneNumbers() + "'" +
            "}";
    }

}
