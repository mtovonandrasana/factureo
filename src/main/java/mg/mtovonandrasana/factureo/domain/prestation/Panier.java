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
    private int quantity;
    @OneToOne
    @JoinColumn(name = "marchandise_id")
    private Marchandise marchandise;


    public Panier() {
        // default constuctor
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Panier quantity(int quantity) {
        setQuantity(quantity);
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
        var panier = (Panier) o;
        return Objects.equals(id, panier.id) && quantity == panier.quantity
                && Objects.equals(marchandise, panier.marchandise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, marchandise);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", marchandise='" + getMarchandise() + "'" +
            "}";
    }

}
