public class Arc implements Comparable<Arc> {
    private int ArcId;
    private int ArcVal;
    private Sommet S1;
    private Sommet S2;

    public Arc(int ArcId, int ArcVal, Sommet S1, Sommet S2) {
        this.ArcId = ArcId;
        this.ArcVal = ArcVal;
        this.S1 = S1;
        this.S2 = S2;
    }

    public void AfficheArc(){
        System.out.println(ArcId+" ( "+S1.getSommetNom()+" , "+S2.getSommetNom()+" [ "+ArcVal+" ]");
    }

    public int getArcId() {
        return ArcId;
    }

    public void setArcId(int arcId) {
        ArcId = arcId;
    }

    public int getArcVal() {
        return ArcVal;
    }

    public void setArcVal(int arcVal) {
        ArcVal = arcVal;
    }

    public Sommet getS1() {
        return S1;
    }

    public void setS1(Sommet s1) {
        S1 = s1;
    }

    public Sommet getS2() {
        return S2;
    }

    public void setS2(Sommet s2) {
        S2 = s2;
    }

    public int compareTo(Arc A){
        return (this.getArcVal()-A.getArcVal());
    }
}
