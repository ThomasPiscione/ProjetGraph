import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public  class Main {


    public static void main(String[] args) {
        Graph Graf = new Graph();
        Sommet A = new Sommet(0,"A",1);
        Sommet B = new Sommet(1,"B",1);
        Sommet C = new Sommet(2,"C",1);
        Sommet D = new Sommet(3,"D",1);
        Sommet E = new Sommet(4,"E",1);
        Sommet F = new Sommet(5,"F",1);
        Sommet G = new Sommet(6,"G",1);
        Sommet H = new Sommet(7,"H",1);
        Arc a = new Arc(0,3,A,B);
        Arc b = new Arc(1,1,A,C);
        Arc c = new Arc(2,1,A,D);
        Arc d = new Arc(3,7,B,E);
        Arc e = new Arc(4,5,C,E);
        Arc f = new Arc(5,4,C,F);
        Arc g = new Arc(6,1,C,D);
        Arc h = new Arc(7,6,D,G);
        Arc i = new Arc(8,1,E,F);
        Arc j = new Arc(9,2,E,H);
        Arc k = new Arc(10,2,F,G);
        Arc l = new Arc(11,1,F,H);
        Arc m = new Arc(12,1,G,H);

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
        //Graf.AdArc(d);
        //Graf.AdArc(e);
        Graf.AdArc(f);
        //Graf.AdArc(g);
        //Graf.AdArc(h);
        Graf.AdArc(i);
        //Graf.AdArc(j);
        //Graf.AdArc(k);
        Graf.AdArc(l);
        Graf.AdArc(m);

        Graf.AffichGraph();

        System.out.println("degre moins de A = "+Graf.DegreMoins(A));
        System.out.println("degre moins de B = "+Graf.DegreMoins(B));
        System.out.println("degre moins de C = "+Graf.DegreMoins(C));
        System.out.println("degre moins de D = "+Graf.DegreMoins(D));
        System.out.println("degre moins de E = "+Graf.DegreMoins(E));
        System.out.println("degre moins de F = "+Graf.DegreMoins(F));
        System.out.println("degre moins de G = "+Graf.DegreMoins(G));
        System.out.println("degre moins de H = "+Graf.DegreMoins(H));


        System.out.println("le graf a un circuit : "+Graf.Circuit());





    }

    Graph Kruskal1(Graph G){

        Collections.sort(G.ListeArc);
        Graph T = new Graph();
        int i = 0;
        while (T.getNbSommet() < G.getNbSommet()-1)
        {
            //TODO verification circuit
        }
        return T;
    }

}