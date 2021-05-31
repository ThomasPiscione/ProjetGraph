import java.util.ArrayList;

public class Graph {

    public ArrayList<ArrayList<SommetAdj>> ListeAdj;
    private int NbSommet;

    public Graph() {
        ListeAdj = new ArrayList<ArrayList<SommetAdj>>();
        NbSommet = 0;
    }

    public Graph(ArrayList<ArrayList<SommetAdj>> ListeAdj) {

        this.ListeAdj = new ArrayList<>();
        for (int i = 0; i < ListeAdj.size(); i++) {
            this.ListeAdj.add(i, new ArrayList<SommetAdj>());
            for (int j = 0; j < ListeAdj.get(i).size(); j++) {
                this.ListeAdj.get(i).add(j, ListeAdj.get(i).get(j));
            }
        }
        this.NbSommet = ListeAdj.size();
    }

    public void AffichGraph(){
        System.out.println("Le Graph a "+NbSommet+" Sommets");
        for (int i=0;i<NbSommet;i++){
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
        ListeAdj.add(S.getSommetId()-1, new ArrayList<SommetAdj>());
        ListeAdj.get(S.getSommetId()-1).add(0, Sfix);
        NbSommet++;
    }

    public void AdSommetAdj(SommetAdj S) {
        if (S.getSommet() == S.getArc().getS1())
        {
            ListeAdj.get(S.getArc().getS2().getSommetId()-1).add(S);
        }
        else {
            if (S.getSommet() == S.getArc().getS2())
            {
                ListeAdj.get(S.getArc().getS1().getSommetId()-1).add(S);
            }
        }
    }

    public  void AdArc(Arc A){
        /*
        if(!ListeAdj.get(A.getS1().getSommetId()).contains(A.getS1()))
        {
            AdSommet(A.getS1());
        }*/
        SommetAdj SA1 = new SommetAdj(A.getS1(), A);
        AdSommetAdj(SA1);
        /*
        if(!ListeAdj.get(A.getS2().getSommetId()).contains(A.getS2()))
        {
            AdSommet(A.getS2());
        }*/
        SommetAdj SA2 = new SommetAdj(A.getS2(), A);
        AdSommetAdj(SA2);

    }
}
