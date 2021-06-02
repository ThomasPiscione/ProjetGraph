import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public  class Main {


    public static void main(String[] args) {
        Graph Graf = new Graph();
        Sommet A = new Sommet(1,"A",1);
        Sommet B = new Sommet(2,"B",1);
        Sommet C = new Sommet(3,"C",1);
        Sommet D = new Sommet(4,"D",1);
        Sommet E = new Sommet(5,"E",1);
        Sommet F = new Sommet(6,"F",1);
        Sommet G = new Sommet(7,"G",1);
        Sommet H = new Sommet(8,"H",1);
        Arc a = new Arc(1,3,A,B);
        Arc b = new Arc(2,1,A,C);
        Arc c = new Arc(3,1,A,D);
        Arc d = new Arc(4,7,B,E);
        Arc e = new Arc(5,5,C,E);
        Arc f = new Arc(6,4,C,F);
        Arc g = new Arc(7,1,C,D);
        Arc h = new Arc(8,6,D,G);
        Arc i = new Arc(9,1,E,F);
        Arc j = new Arc(10,2,E,G);
        Arc k = new Arc(11,2,F,G);
        Arc l = new Arc(12,1,F,H);
        Arc m = new Arc(13,1,G,H);

        Graf.AdSommet(A);
        Graf.AdSommet(B);
        Graf.AdSommet(C);
        Graf.AdSommet(D);
        Graf.AdSommet(E);
        Graf.AdSommet(F);
        Graf.AdSommet(G);
        Graf.AdSommet(H);

        Graf.AdArc(a);
        Graf.AdArc(b);
        Graf.AdArc(c);
        Graf.AdArc(d);
        Graf.AdArc(e);
        Graf.AdArc(f);
        Graf.AdArc(g);
        Graf.AdArc(h);
        Graf.AdArc(i);
        Graf.AdArc(j);
        Graf.AdArc(k);
        Graf.AdArc(l);
        Graf.AdArc(m);

        Graf.AffichGraph();
    }

    Graph Kruskal1(Graph G){
        Collections.sort(G.ListeArc);
        int j = 0;
        while (!G.ListeArc.isEmpty())
        {
            G.ListeArc.get(j).AfficheArc();
        }
        Graph T = new Graph();
        int i = 0;
        while (T.getNbSommet() < G.getNbSommet()-1)
        {
            //TODO verification circuit
        }
        return T;
    }

}