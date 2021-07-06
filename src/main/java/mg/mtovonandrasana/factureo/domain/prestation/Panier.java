package mg.mtovonandrasana.factureo.domain.prestation;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "_PANIER_")
public class Panier {

    @Id
    @GeneratedValue
    private Long id;
    private int quarntity;
    @OneToOne
    @JoinColumn(name = "marchandise_id")
    private Marchandise marchandise;


    public Panier() {
    }

    public Panier(Long id, int quarntity, Marchandise marchandise) {
        this.id = id;
        this.quarntity = quarntity;
        this.marchandise = marchandise;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuarntity() {
        return this.quarntity;
    }

    public void setQuarntity(int quarntity) {
        this.quarntity = quarntity;
    }

    public Marchandise getMarchandise() {
        return this.marchandise;
    }

    public void setMarchandise(Marchandise marchandise) {
        this.marchandise = marchandise;
    }

    public Panier id(Long id) {
        setId(id);
        return this;
    }

    public Panier quarntity(int quarntity) {
        setQuarntity(quarntity);
        return this;
    }

    public Panier marchandise(Marchandise marchandise) {
        setMarchandise(marchandise);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Panier)) {
            return false;
        }
        Panier panier = (Panier) o;
        return Objects.equals(id, panier.id) && quarntity == panier.quarntity && Objects.equals(marchandise, panier.marchandise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quarntity, marchandise);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", quarntity='" + getQuarntity() + "'" +
            ", marchandise='" + getMarchandise() + "'" +
            "}";
    }


}
