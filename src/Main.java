import java.util.ArrayList;

public  class Main {


    public static void main(String[] args) {
        Graph G = new Graph();
        Sommet A = new Sommet(1,"A",1);
        Sommet B = new Sommet(2,"B",5);
        Sommet C = new Sommet(3,"C",2);
        Sommet D = new Sommet(4,"D",4);
        Arc a = new Arc(1,2,A,B);
        Arc b = new Arc(2,5,B,C);
        Arc c = new Arc(3,3,C,D);
        Arc d = new Arc(4,2,D,A);
        G.AdSommet(A);
        G.AdSommet(B);
        G.AdSommet(C);
        G.AdSommet(D);
        G.AdArc(a);
        G.AdArc(b);
        G.AdArc(c);
        G.AdArc(d);
        G.AffichGraph();
    }

}