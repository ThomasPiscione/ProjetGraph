import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.*;

public class Graph implements Cloneable {

    private int nbSommets;  // nombre de sommet
    private int nbArc;      // nombre d'arcs
    private final ListeSommet listeSommet; // Liste de sommet
    private final ListeArc listeArc;       // Liste D'arcs
    private final HashMap<Integer, ArrayList<Integer>> listeAdjacent; //id Sommets -> liste d'id sommets adjacent

    // Constructeur par default

    public Graph() {
        nbArc = 0;
        nbSommets = 0;
        listeSommet = new ListeSommet();
        listeArc = new ListeArc();
        listeAdjacent = new HashMap<>();
    }

    // Constructeur par copie de liste de sommet,liste d'arc et liste d'adjacence

    public Graph(ListeSommet lS, ListeArc lA, HashMap<Integer, ArrayList<Integer>> lAdj) {
        nbArc = lA.getSize();
        nbSommets = lS.getSize();
        listeSommet = lS;
        listeArc = lA;
        //clonage de la liste d'adjacence :
        this.listeAdjacent = new HashMap<>();
        clonelisteAdjacent(lAdj);
    }

    public void clonelisteAdjacent(HashMap<Integer, ArrayList<Integer>> lAdj) {

        for (Integer i : lAdj.keySet()) {
            ArrayList<Integer> adjacents = new ArrayList<>();
            for (Integer j : lAdj.get(i)) {
                adjacents.add(j);
            }
            this.listeAdjacent.put(i, adjacents);
        }
    }

    //ajout de sommet par référence:

    public void addSommet(Sommet S) {
        listeSommet.addSommet(S);
        listeAdjacent.put(S.getId(), new ArrayList<>());
        nbSommets++;
    }

    //ajout de sommet par copie :
    public void addSommetClone(Sommet S) {
        Sommet Sfix = new Sommet(S.getId(), S.getValue(), 0);
        listeSommet.addSommet(Sfix);
        listeAdjacent.put(Sfix.getId(), new ArrayList<>());
        nbSommets++;
    }

    //ajout d'arc

    public void addArc(Arc A) {
        listeArc.addArc(A);

        //vérifie que les sommets extreme de l'arc sont dans la liste :
        if (!listeSommet.idEstDansLaListe(A.GetIdS1()) && !listeSommet.idEstDansLaListe(A.GetIdS2())) {
            //aucun des sommets n'est dans la liste :
            this.addSommetClone(A.GetS1());
            this.addSommetClone(A.GetS2());
        } else if (!listeSommet.idEstDansLaListe(A.GetIdS1())) {
            //le 1er sommet n'est pas dans la liste
            this.addSommetClone(A.GetS1());
        } else if (!listeSommet.idEstDansLaListe(A.GetIdS2())) {
            //le 2ème sommet n'est pas dans la liste
            this.addSommetClone(A.GetS2());
        }

        listeSommet.addArc(A.GetIdS1(), A.GetIdS2());

        listeAdjacent.get(A.GetIdS1()).add(A.GetIdS2());
        listeAdjacent.get(A.GetIdS2()).add(A.GetIdS1());

        nbArc++;
    }

    public void supprArc(Arc A) {
        listeArc.supprArc(A);

        ArrayList<Integer> adjS1 = listeAdjacent.get(A.GetIdS1());
        ArrayList<Integer> adjS2 = listeAdjacent.get(A.GetIdS2());
        adjS1.remove(adjS1.indexOf(A.GetIdS2()));
        adjS2.remove(adjS2.indexOf(A.GetIdS1()));

        nbArc--;
    }

    // Assesseurs :

    public Sommet GetSommet(int id) {
        return listeSommet.getSommetById(id);
    }

    public Arc GetArc(int id) {
        return listeArc.getArcById(id);
    }

    public ListeSommet GetListeSommet() {
        return listeSommet;
    }

    public ListeArc GetListeArc() {
        return listeArc;
    }

    public int getNbSommets() {
        return nbSommets;
    }

    // fonction de Graph

    //Alog de PRIM

    public Graph Prim(int degreMax) {
        // prendre un sommet aleatoire dans le graph

        int nbAleat = (int) (Math.random() * listeSommet.getSize());

        Graph graphMin = new Graph();
        graphMin.addSommetClone(listeSommet.getSommetsListe().get(nbAleat).clone());

        while (graphMin.getNbSommets() != nbSommets) {
            //on crée une liste de sommet issue de la soustraction de X par R :

            ListeSommet XmoinsR = listeSommet.clone();
            XmoinsR.initListAdjacent(listeAdjacent);
            XmoinsR.soustractionListe(graphMin.GetListeSommet());

            //on crée une liste d'arc issue de la soustraction de U par T :

            ListeArc UmoinsT = listeArc.clone();
            UmoinsT.SoustractionListe(graphMin.GetListeArc());

            //recherche de l'arc (y, z) de poids mini

            Arc aMin = null;
            Sommet z = null;

            ArrayList<Integer> idArc = UmoinsT.getIdArcParValeurCroissante();

            for (int i = 0; i < UmoinsT.getSize(); i++) {
                aMin = UmoinsT.getArcById(idArc.get(i));
                int idy = aMin.GetIdS1();
                int idz = aMin.GetIdS2();


                boolean degreNonDepasse = false;


                if (graphMin.listeSommet.idEstDansLaListe(idy) && XmoinsR.idEstDansLaListe(idz)) {
                    //vérification du degré max du sommet y:
                    if (degreMax == 0) {
                        degreNonDepasse = true;
                    } else if (graphMin.listeSommet.getSommetById(idy).getDegre() < degreMax) {
                        degreNonDepasse = true;
                    }

                    if (degreNonDepasse) {
                        z = this.listeSommet.getSommetById(idz);
                        break;
                    }
                } else if (graphMin.listeSommet.idEstDansLaListe(idz) && XmoinsR.idEstDansLaListe(idy)) {
                    //vérification du degré max du sommet z:
                    if (degreMax == 0) {
                        degreNonDepasse = true;
                    } else if (graphMin.listeSommet.getSommetById(idz).getDegre() < degreMax) {
                        degreNonDepasse = true;
                    }

                    if (degreNonDepasse) {
                        z = this.listeSommet.getSommetById(idy);
                        break;
                    }
                }
            }


            // ajouter (y, z) à T
            // ajouter z à R
            if (z != null) {
                graphMin.addSommetClone(z.clone()); // reinitialise a 1 le d°
                graphMin.addArc(aMin.clone());
            }
        }

        return graphMin;
    }


    public Graph Kruskal1(int degreMax) {

        Graph T = new Graph();

        //on range les arc dans l'ordre croissant de leur poids dans une nouvelle arrayliste d'arc :
        ArrayList<Arc> listeArcCroissant = new ArrayList<>();
        listeArcCroissant = this.listeArc.getListeArcParValeurCroissante();

        int i = 0;

        Graph GTUnionUi;

        while (T.listeArc.getSize() < this.listeSommet.getSize() - 1) {
            //création de T U {ui} :
            Arc ArcAjoute = listeArcCroissant.get(i).clone();
            boolean degreNonDepasse = false;
            if (degreMax == 0) {
                degreNonDepasse = true;
            }//on vérifie si les sommets sont déjà dans la liste (évitant de tester des sommets inexistant dans la liste)
            //les 2 sommets sont dans le graphMin :
            else if (T.listeSommet.idEstDansLaListe(ArcAjoute.GetIdS1()) && T.listeSommet.idEstDansLaListe(ArcAjoute.GetIdS2())) {
                if (T.listeSommet.getSommetById(ArcAjoute.GetIdS1()).getDegre() < degreMax && T.listeSommet.getSommetById(ArcAjoute.GetIdS2()).getDegre() < degreMax)
                    degreNonDepasse = true;
            }//si seulement le sommet 1 est dans la liste :
            else if (T.listeSommet.idEstDansLaListe(ArcAjoute.GetIdS1())) {
                if (T.listeSommet.getSommetById(ArcAjoute.GetIdS1()).getDegre() < degreMax)
                    degreNonDepasse = true;
            }//si seulement le sommet 2 est dans la liste :
            else if (T.listeSommet.idEstDansLaListe(ArcAjoute.GetIdS2())) {
                if (T.listeSommet.getSommetById(ArcAjoute.GetIdS2()).getDegre() < degreMax)
                    degreNonDepasse = true;
            }//si il n'y a aucun des sommets dans la liste (il n'y a donc pas de contrainte :
            else {
                degreNonDepasse = true;
            }
            //vérifie si les degrés des sommets n'ont pas atteint le d° maximum avant d'éventuellement ajouter l'arête OU si il n'y a pas de degré max:
            if (degreNonDepasse) {
                GTUnionUi = T.clone();
                GTUnionUi.addArc(ArcAjoute);

                //vérification de l'existence d'un cycle avec l'ajout de ui :
                if (!GTUnionUi.existeCycle()) {
                    T = GTUnionUi.clone();
                }

                GTUnionUi = null;

            }
            //sinon passe à l'arc suivante
            i++;
        }

        return T;
    }

    public Graph Kruskal2() {
        //on creer le graph de sortie :
        Graph T = new Graph();

        //on range les arcs dans l'ordre croissant de leur poids dans une nouvelle arrayliste d'arc :
        ArrayList<Arc> lstArcDecroiss = new ArrayList<>();
        lstArcDecroiss = this.listeArc.getListArcParValeurDecroissante();

        T = this.clone();

        int i = 0;
        Graph GTMoinsUi;


        while (T.listeArc.getSize() >= this.listeSommet.getSize()) {
            //création de T - {ui} :
            Arc ArcSuppr = lstArcDecroiss.get(i).clone();
            GTMoinsUi = T.clone();
            GTMoinsUi.supprArc(ArcSuppr);

            //vérification de l'existence d'un cycle avec l'ajout de ui :
            if (GTMoinsUi.estConnexe()) {
                T = GTMoinsUi.clone();
            }
            GTMoinsUi = null;
            i++;
        }

        return T;
    }

    public void Afficher() {
        System.out.print(listeAdjacent.get(0));
    }


    public boolean existeCycle() {
        if (listeSommet.getSize() < 3)
            return false;

        ArrayDeque<Sommet> sommetsNonParcourus = new ArrayDeque<>();
        ArrayDeque<Arc> ArcParcourues = new ArrayDeque<>();
        for (Sommet s : listeSommet.getSommetsListe()) {
            sommetsNonParcourus.addLast(s);
        }

        ArrayDeque<Sommet> file = new ArrayDeque<>();

        while (!sommetsNonParcourus.isEmpty()) {
            ArrayDeque<Sommet> sommetsParcourus = new ArrayDeque<>();
            file.addLast(sommetsNonParcourus.removeFirst());

            while (!file.isEmpty()) {
                Sommet s = file.removeFirst();
                if (sommetsParcourus.contains(s)) {
                    return true;
                }
                sommetsParcourus.addLast(s);

                for (Arc a : listeArc.getListeArcParIdSommet(s.getId())) {
                    if (!ArcParcourues.contains(a)) {
                        ArcParcourues.addLast(a);
                        //ajout sommet adjacent dans la liste :
                        if (a.GetIdS1() == s.getId())
                            file.addLast(listeSommet.getSommetById(a.GetIdS2()));
                        if (a.GetIdS2() == s.getId())
                            file.addLast(listeSommet.getSommetById(a.GetIdS1()));
                    }
                }
            }
            sommetsNonParcourus.remove(sommetsParcourus);
        }

        return false;
    }

    public boolean estConnexe() {
        //on part du premier sommet et verifie chaque sommet en les marquants :

        HashMap<Integer, Boolean> sommetsVisite = new HashMap<Integer, Boolean>();

        // on initialise les valeurs à faux
        for (Sommet s : listeSommet.getSommetsListe()) {
            sommetsVisite.put(s.getId(), false);
        }

        ArrayDeque file = new ArrayDeque<Sommet>();
        //on place le sommet de départ dans la file :
        file.addLast(this.listeSommet.getSommetsListe().get(0));

        while (!file.isEmpty()) {
            Sommet s = (Sommet) file.removeFirst();

            if (!sommetsVisite.get(s.getId())) {
                sommetsVisite.put(s.getId(), true);
                for (Arc a : listeArc.getListeArcParIdSommet(s.getId())) {
                    if (s.getId() == a.GetIdS1() && !file.contains(listeSommet.getSommetById(a.GetIdS2())))
                        file.addLast(listeSommet.getSommetById(a.GetIdS2()));
                    else if (s.getId() == a.GetIdS2() && !file.contains(listeSommet.getSommetById(a.GetIdS1())))
                        file.addLast(listeSommet.getSommetById(a.GetIdS1()));
                }
            }
        }

        // si on trouve au moins un sommet false : return false
        for (Sommet s : listeSommet.getSommetsListe()) {
            if (!sommetsVisite.get(s.getId()))
                return false;
        }

        return true;
    }

    public double getPoids() {
        double poids = 0;

        for (Arc a : listeArc.getListeArcParValeurCroissante()) {
            poids += a.GetValue();
        }
        return poids;
    }

    public int getDegMax() {
        int degMax = 0;

        for (Sommet s : listeSommet.getSommetsListe()) {
            if (degMax < s.getDegre())
                degMax = s.getDegre();
        }
        return degMax;
    }

    @Override
    public String toString() {
        return "Graph : " +
                " \n  Nombres de sommets = " + nbSommets +
                ",\n  Nombre d'arcs = " + nbArc +
                ", \n  Liste de sommets = " + listeSommet +
                ", \n  Liste d'arcs = " + listeArc +
                ", \n  Liste d'Adjacence = " + listeAdjacent +
                "\n";
    }

    @Override
    public Graph clone() {
        return new Graph(this.listeSommet.clone(), this.listeArc.clone(), this.listeAdjacent);
    }

}
