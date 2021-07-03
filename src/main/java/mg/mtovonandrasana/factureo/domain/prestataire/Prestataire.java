package mg.mtovonandrasana.factureo.domain.prestataire;

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

    // TODO: add address here


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

}
