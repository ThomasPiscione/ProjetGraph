import java.util.Comparator;

public class Arc implements Cloneable {
    private final int id;

    private final int value;

    private final Sommet s1;
    private final Sommet s2;

    public Arc(int id, int value, Sommet s1, Sommet s2) {
        this.id = id;
        this.value = value;
        this.s1 = s1;
        this.s2 = s2;
    }

    public int GetId() {
        return id;
    }

    public int GetValue() {
        return value;
    }

    public int GetIdS1() {
        return s1.getId();
    }

    public int GetIdS2() {
        return s2.getId();
    }

    public Sommet GetS1() {
        return s1;
    }

    public Sommet GetS2() {
        return s2;
    }

    public static Comparator<Arc> SortByPoids = new Comparator<Arc>() {
        @Override
        public int compare(Arc a1, Arc a2) {
            return a1.GetValue() - a2.GetValue();
        }
    };

    public static Comparator<Arc> SortByPoidsDecroissant = new Comparator<Arc>() {
        @Override
        public int compare(Arc a1, Arc a2) {
            return a2.GetValue() - a1.GetValue();
        }
    };

    @Override
    public String toString() {
        return "Arc{" +
                "id=" + id +
                ", valeur=" + value +
                ", s1=" + s1.getId() +
                ", s2=" + s2.getId() +
                "}" + '\n';
    }

    @Override
    public Arc clone() {
        return new Arc(this.id, this.value, this.s1, this.s2);
    }
}
