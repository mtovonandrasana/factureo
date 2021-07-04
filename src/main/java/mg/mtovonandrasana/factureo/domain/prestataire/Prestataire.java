package mg.mtovonandrasana.factureo.domain.prestataire;

import java.util.Objects;
import java.util.Set;

import mg.mtovonandrasana.factureo.domain.common.Adresse;

public class Prestataire {
    private String nom;
    private String prenom;
    private String raisonSocial;
    private String nif;
    private String stat;
    private String rcs;
    private String cin;
    private String activite;
    private String nomResponsable;
    private boolean isIndividuel;
    private byte[] signature;
    private Adresse headOfficeAdresse;
    private String email;
    private Set<String> phoneNumbers;


    public Prestataire() {
        // default constructor
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getActivite() {
        return this.activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getNomResponsable() {
        return this.nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
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

    public Adresse getHeadOfficeAdresse() {
        return this.headOfficeAdresse;
    }

    public void setHeadOfficeAdresse(Adresse headOfficeAdresse) {
        this.headOfficeAdresse = headOfficeAdresse;
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

    public Prestataire nom(String nom) {
        setNom(nom);
        return this;
    }

    public Prestataire prenom(String prenom) {
        setPrenom(prenom);
        return this;
    }

    public Prestataire raisonSocial(String raisonSocial) {
        setRaisonSocial(raisonSocial);
        return this;
    }

    public Prestataire nif(String nif) {
        setNif(nif);
        return this;
    }

    public Prestataire stat(String stat) {
        setStat(stat);
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

    public Prestataire activite(String activite) {
        setActivite(activite);
        return this;
    }

    public Prestataire nomResponsable(String nomResponsable) {
        setNomResponsable(nomResponsable);
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

    public Prestataire headOfficeAdresse(Adresse headOfficeAdresse) {
        setHeadOfficeAdresse(headOfficeAdresse);
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
        Prestataire prestataire = (Prestataire) o;
        return Objects.equals(nom, prestataire.nom) && Objects.equals(prenom, prestataire.prenom)
                && Objects.equals(raisonSocial, prestataire.raisonSocial) && Objects.equals(nif, prestataire.nif)
                && Objects.equals(stat, prestataire.stat) && Objects.equals(rcs, prestataire.rcs)
                && Objects.equals(cin, prestataire.cin) && Objects.equals(activite, prestataire.activite)
                && Objects.equals(nomResponsable, prestataire.nomResponsable)
                && isIndividuel == prestataire.isIndividuel && Objects.equals(signature, prestataire.signature)
                && Objects.equals(headOfficeAdresse, prestataire.headOfficeAdresse)
                && Objects.equals(email, prestataire.email) && Objects.equals(phoneNumbers, prestataire.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, raisonSocial, nif, stat, rcs, cin, activite, nomResponsable, isIndividuel,
                signature, headOfficeAdresse, email, phoneNumbers);
    }

    @Override
    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", nif='" + getNif() + "'" +
            ", stat='" + getStat() + "'" +
            ", rcs='" + getRcs() + "'" +
            ", cin='" + getCin() + "'" +
            ", activite='" + getActivite() + "'" +
            ", nomResponsable='" + getNomResponsable() + "'" +
            ", isIndividuel='" + isIsIndividuel() + "'" +
            ", signature='" + getSignature() + "'" +
            ", headOfficeAdresse='" + getHeadOfficeAdresse() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumbers='" + getPhoneNumbers() + "'" +
            "}";
    }
    



}
