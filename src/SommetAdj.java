public class SommetAdj {
    private Sommet sommet;
    private Arc arc;

    public SommetAdj(Sommet sommet, Arc arc) {
        this.sommet=sommet;
        this.arc=arc;
    }

    public SommetAdj(Sommet sommet){
        this.sommet=sommet;
        arc = null;
    }

    public void AfficheSommetAdj(){
        System.out.println(sommet.getSommetNom()+"("+arc.getArcVal()+")");
    }

    public Sommet getSommet() {
        return sommet;
    }

    public void setSommet(Sommet sommet) {
        this.sommet = sommet;
    }

    public Arc getArc() {
        return arc;
    }

    public void setArc(Arc arc) {
        this.arc = arc;
    }
}
