import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Graph G = new Graph();

        G.addSommet(new Sommet(0, 0));
        G.addSommet(new Sommet(1, 1));
        G.addSommet(new Sommet(2, 2));
        G.addSommet(new Sommet(3, 3));
        G.addSommet(new Sommet(4, 4));
        G.addSommet(new Sommet(5, 5));
        G.addSommet(new Sommet(6, 6));
        G.addSommet(new Sommet(7, 7));


        G.addArc(new Arc(0, 3, G.GetSommet(0), G.GetSommet(1)));
        G.addArc(new Arc(1, 1, G.GetSommet(0), G.GetSommet(2)));
        G.addArc(new Arc(2, 1, G.GetSommet(0), G.GetSommet(3)));
        G.addArc(new Arc(4, 7, G.GetSommet(1), G.GetSommet(4)));
        G.addArc(new Arc(3, 1, G.GetSommet(2), G.GetSommet(3)));
        G.addArc(new Arc(5, 5, G.GetSommet(2), G.GetSommet(4)));
        G.addArc(new Arc(6, 4, G.GetSommet(2), G.GetSommet(5)));
        G.addArc(new Arc(7, 6, G.GetSommet(3), G.GetSommet(6)));
        G.addArc(new Arc(8, 1, G.GetSommet(4), G.GetSommet(5)));
        G.addArc(new Arc(9, 2, G.GetSommet(4), G.GetSommet(7)));
        G.addArc(new Arc(10, 2, G.GetSommet(5), G.GetSommet(6)));
        G.addArc(new Arc(11, 1, G.GetSommet(5), G.GetSommet(7)));
        G.addArc(new Arc(12, 1, G.GetSommet(6), G.GetSommet(7)));


        System.out.println("Graphe :" + G + "\n");


        // PRIME
        double tpsPrim = System.currentTimeMillis();
        Graph G1 = new Graph();
        G1 = G.Prim(0);
        tpsPrim = System.currentTimeMillis() - tpsPrim;

        System.out.println("Temps d'exécution de prime : " + tpsPrim + "\n");
        System.out.println("Prim : Graphe min :" + G1 + "\n");
        System.out.println("Poids de l'arbre :" + G1.getPoids() + "\n");

        //KRUSKAL1
        double tpsKruskal = System.currentTimeMillis();
        Graph G2 = new Graph();
        G2 = G.Kruskal1(0);
        tpsKruskal = System.currentTimeMillis() - tpsKruskal;
        System.out.println("Temps d'exécution de Kruskal : " + tpsKruskal + "\n");
        System.out.println("Kruskal1 : Graphe min :" + G2 + "\n");
        System.out.println("Poids de l'arbre :" + G2.getPoids() + "\n");

        //KRUSKAL2
        double tpsKruskal2 = System.currentTimeMillis();
        Graph G3 = G.Kruskal2();
        tpsKruskal2 = System.currentTimeMillis() - tpsKruskal2;
        System.out.println("Temps d'exécution de Kruskal : " + tpsKruskal2 + "\n");
        System.out.println("Kruskal2 : Graphe min :" + G3 + "\n");
        System.out.println("Poids de l'arbre :" + G3.getPoids() + "\n");

    }

}

