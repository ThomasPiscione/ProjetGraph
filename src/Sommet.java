public class Sommet {
    private int SommetId;
    private String SommetNom;
    private int SommetVal;

    public Sommet(int SommetId, int SommetVal) {
        this.setSommetId(SommetId);
        this.setSommetVal(SommetVal);
    }

    public void AfficheSommet(){
        System.out.println(SommetNom+" [ "+SommetVal+" ]");
    }


    public int getSommetId() {
        return SommetId;
    }

    public void setSommetId(int sommetId) {
        SommetId = sommetId;
    }

    public String getSommetNom() {
        return SommetNom;
    }

    public void setSommetNom(String sommetNom) {
        SommetNom = sommetNom;
    }

    public int getSommetVal() {
        return SommetVal;
    }

    public void setSommetVal(int sommetVal) {
        SommetVal = sommetVal;
    }
}
