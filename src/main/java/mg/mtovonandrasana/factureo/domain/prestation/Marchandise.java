package mg.mtovonandrasana.factureo.domain.prestation;

import java.util.Objects;

public class Marchandise {
    private String reference;
    private String description;
    private Long prixUnitaire;

    public Marchandise() {
    }

    public Marchandise(String reference, String description, Long prixUnitaire) {
        this.reference = reference;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrixUnitaire() {
        return this.prixUnitaire;
    }

    public void setPrixUnitaire(Long prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Marchandise reference(String reference) {
        setReference(reference);
        return this;
    }

    public Marchandise description(String description) {
        setDescription(description);
        return this;
    }

    public Marchandise prixUnitaire(Long prixUnitaire) {
        setPrixUnitaire(prixUnitaire);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Marchandise)) {
            return false;
        }
        Marchandise marchandise = (Marchandise) o;
        return Objects.equals(reference, marchandise.reference) && Objects.equals(description, marchandise.description)
                && Objects.equals(prixUnitaire, marchandise.prixUnitaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, description, prixUnitaire);
    }

    @Override
    public String toString() {
        return "{" + " reference='" + getReference() + "'" + ", description='" + getDescription() + "'"
                + ", prixUnitaire='" + getPrixUnitaire() + "'" + "}";
    }

}
