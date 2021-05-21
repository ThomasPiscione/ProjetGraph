public class Sommet {
    private int SommetId;
    private int SommetVal;

    public Sommet(int SommetId, int SommetVal) {
        this.SommetId = SommetId;
        this.SommetVal = SommetVal;
    }

    public int getSommetId() {
        return SommetId;
    }

    public void setSommetId(int noeudId) {
        SommetId = noeudId;
    }

    public int getSommetVal() {
        return SommetVal;
    }

    public void setSommetVal(int noeudVal) {
        SommetVal = noeudVal;
    }
}
