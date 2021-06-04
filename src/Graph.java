import java.util.ArrayList;

public class Graph {

    public ArrayList<ArrayList<SommetAdj>> ListeAdj;
    public ArrayList<Arc> ListeArc;
    private int NbSommet;


    public Graph() {
        ListeAdj = new ArrayList<ArrayList<SommetAdj>>();
        ListeArc = new ArrayList<Arc>();
        setNbSommet(0);
    }

    public Graph(ArrayList<ArrayList<SommetAdj>> ListeAdj) {

        this.ListeAdj = new ArrayList<>();
        for (int i = 0; i < ListeAdj.size(); i++) {
            this.ListeAdj.add(i, new ArrayList<SommetAdj>());
            for (int j = 0; j < ListeAdj.get(i).size(); j++) {
                this.ListeAdj.get(i).add(j, ListeAdj.get(i).get(j));
            }
        }
        this.setNbSommet(ListeAdj.size());
    }

    public void AffichGraph(){
        System.out.println("Le Graph a "+ getNbSommet() +" Sommets");
        for (int i = 0; i< getNbSommet(); i++){
            for (int j=0;j<ListeAdj.get(i).size();j++){
                if (j == 0){
                    ListeAdj.get(i).get(0).getSommet().AfficheSommet();
                }else {
                    ListeAdj.get(i).get(j).AfficheSommetAdj();
                }
            }
        }
    }

    public void AdSommet(Sommet S) {
        SommetAdj Sfix = new SommetAdj(S);
        ListeAdj.add(S.getSommetId(), new ArrayList<SommetAdj>());
        ListeAdj.get(S.getSommetId()).add(0, Sfix);
        setNbSommet(getNbSommet() + 1);
    }

    public void AdSommetAdj(SommetAdj S) {
        if (S.getSommet() == S.getArc().getS1())
        {
            ListeAdj.get(S.getArc().getS2().getSommetId()).add(S);
        }
        else {
            if (S.getSommet() == S.getArc().getS2())
            {
                ListeAdj.get(S.getArc().getS1().getSommetId()).add(S);
            }
        }
    }

    public int DegreMoins(Sommet S){
        int Degre = 0;
        int i = 1;
        if (ListeAdj.size()>S.getSommetId()) {
            if (!(ListeAdj.get(S.getSommetId()).isEmpty())) {
                while (i < ListeAdj.get(S.getSommetId()).size()) {
                    Degre++;
                    i++;
                }
            }
        }
        return Degre;
    }

    public  void AdArc(Arc A){

        if(!(ListeAdj.get(A.getS1().getSommetId()).get(0).getSommet() == A.getS1()))
        {
            AdSommet(A.getS1());
        }
        SommetAdj SA1 = new SommetAdj(A.getS1(), A);
        AdSommetAdj(SA1);

        if(!(ListeAdj.get(A.getS2().getSommetId()).get(0).getSommet() == A.getS2()))
        {
            AdSommet(A.getS2());
        }
        SommetAdj SA2 = new SommetAdj(A.getS2(), A);
        AdSommetAdj(SA2);

        ListeArc.add(A);

    }

    public boolean Circuit() {
        Graph F = new Graph();
        int i;
        for (i = 0; i < ListeAdj.size(); i++) {
            if (F.DegreMoins(ListeAdj.get(i).get(0).getSommet()) == 0) {
                F.AdSommet(ListeAdj.get(i).get(0).getSommet());
            }
        }
        i = 0;
        while (F.getNbSommet() != 0) {
            while (F.ListeAdj.get(i).size() > 1) {
                if (F.DegreMoins(ListeAdj.get(i).get(1).getSommet()) == 0) {
                    F.AdSommet(ListeAdj.get(i).get(1).getSommet());
                }
            }
            F.ListeAdj.get(i).clear();
            F.setNbSommet(F.getNbSommet() - 1);
            i++;
        }
        if (F.getNbSommet() == getNbSommet())
            return false;
        else return true;
    }


    public int getNbSommet() {
        return NbSommet;
    }

    public void setNbSommet(int nbSommet) {
        NbSommet = nbSommet;
    }
}
