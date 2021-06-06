import java.util.Objects;

public class Sommet implements Cloneable {

    private final int id;     //id du sommet
    private final int value;  //valeur du sommet
    private int degre;        //degré du sommet

    public Sommet(int id, int value) {
        this.id = id;
        this.value = value;
        degre = 0;
    }

    public Sommet(int id, int value, int degre) {
        this.id = id;
        this.value = value;
        this.degre = degre;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public int getDegre() {
        return degre;
    }

    public void setDegre(int d) {
        degre = d;
    }

    public void addArc() {
        degre++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sommet sommet = (Sommet) o;
        return id == sommet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sommet{" +
                "id=" + id +
                ", value=" + value +
                ", degré=" + degre +
                "}" + '\n';
    }

    @Override
    public Sommet clone() {
        return new Sommet(this.id, this.value, this.degre);
    }
}
