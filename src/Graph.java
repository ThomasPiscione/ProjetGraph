import java.util.ArrayList;

public class Graph {

    private ArrayList<ArrayList<SommetAdj>> ListeAdj;
    int NbSommet;

    public Graph() {
        ListeAdj = new ArrayList<>();
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

    public void AdSommet(Sommet S) {
        SommetAdj Sfix = new SommetAdj(S);
        ListeAdj.add(S.getSommetId(), new ArrayList<>());
        ListeAdj.get(S.getSommetId()).add(1, Sfix);
        NbSommet++;
    }

    public void AdSommetAdj(SommetAdj S) {
        ListeAdj.get(S.getSommet().getSommetId()).add(S);
    }


}
