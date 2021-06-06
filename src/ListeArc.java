import java.util.*;

public class ListeArc implements Cloneable {
    private final HashMap<Integer, Arc> arc = new HashMap<>();

    // constructeur par default
    public ListeArc() {
    }

    // constructeur par copie
    public ListeArc(ListeArc lstACp) {
        for (Arc a : lstACp.arc.values()) {
            arc.put(a.GetId(), a.clone());
        }
    }

    public int getSize() {
        return arc.size();
    }

    // copie dans la liste (création d'un nouveau pointeur pour éviter la modification
    // de l'original
    public void addArc(int id, int value, Sommet s1, Sommet s2) {
        this.addArc(new Arc(id, value, s1, s2));
    }


    // ajouter dans la vraie liste
    public void addArc(Arc a) {
        arc.put(a.GetId(), a);
    }

    //retire un arc :
    public void supprArc(Arc a) {
        arc.remove(a.GetId());
    }

    //récupère l'arc à l'indice spécifier :
    public Arc getArcById(int id) {
        return arc.get(id);
    }


    //retourne l'arc de poids minimum :

    /**
     * retourne la liste d'arc trié dans l'ordre décroissant :
     */
    public ArrayList<Arc> getListArcParValeurDecroissante() {
        //création hashmap de sortie :
        HashMap<Integer, Arc> lstTrieCroiss = new HashMap<Integer, Arc>();
        //trie de la liste des poids :
        ArrayList<Arc> lstArcTrie = new ArrayList<>(arc.values());
        lstArcTrie.sort(Arc.SortByPoidsDecroissant);

        return lstArcTrie;
    }

    public ArrayList<Integer> getIdArcParValeurCroissante() {
        ArrayList<Integer> idArc = new ArrayList<>(arc.keySet());
        idArc.sort(Comparator.comparingInt(id -> arc.get(id).GetValue()));
        return idArc;
    }

    /**
     * retourne la liste d'arc trié dans l'ordre croissant :
     */
    public ArrayList<Arc> getListeArcParValeurCroissante() {
        //création hashmap de sortie :
        HashMap<Integer, Arc> lstTrieCroiss = new HashMap<Integer, Arc>();
        //trie de la liste des poids :
        ArrayList<Arc> lstArcTrie = new ArrayList<>(arc.values());
        lstArcTrie.sort(Arc.SortByPoids);

        return lstArcTrie;
    }

    public ArrayList<Arc> getListeArcParIdSommet(int idSommet) {
        ArrayList<Arc> listArc = new ArrayList<>();
        for (int i : this.arc.keySet()) {
            if (this.arc.get(i).GetIdS1() == idSommet || this.arc.get(i).GetIdS2() == idSommet) {
                listArc.add(this.arc.get(i));
            }
        }
        return listArc;
    }

    public void SoustractionListe(ListeArc a2) {
        for (int id : a2.arc.keySet()) {
            arc.remove(id);
        }
    }

    /**
     * retourne un tableau d'id des adjacent du sommet passé en paramètre :
     */
    public ArrayList<Integer> getAdjacent(int id) {
        ArrayList<Integer> listeAdjacent = new ArrayList<>();
        for (Arc A : arc.values()) {
            if (A.GetIdS1() == id)
                listeAdjacent.add(A.GetIdS2());
            else if (A.GetIdS2() == id)
                listeAdjacent.add(A.GetIdS1());
        }

        return listeAdjacent;
    }


    @Override
    public String toString() {
        return "" + arc;
    }

    @Override
    public ListeArc clone() {
        return new ListeArc(this);
    }
}
