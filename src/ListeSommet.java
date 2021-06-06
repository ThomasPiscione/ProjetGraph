import java.util.*;


public class ListeSommet implements Cloneable {
    private final HashMap<Integer, Sommet> sommets = new HashMap<>();
    private HashMap<Integer, ArrayList<Integer>> listAdjacent; //id Sommets -> id sommets adjacent

    public ListeSommet() {
    }

    //construct par copie :
    public ListeSommet(ListeSommet lstSCp) {
        for (Sommet s : lstSCp.sommets.values()) {
            sommets.put(s.getId(), s.clone());
        }
    }

    public void initListAdjacent(HashMap<Integer, ArrayList<Integer>> _listAdjacent) {
        listAdjacent = new HashMap<>();
        for (Integer i : _listAdjacent.keySet()) {
            ArrayList<Integer> adjacents = new ArrayList<>();
            for (Integer j : _listAdjacent.get(i)) {
                adjacents.add(j);
            }
            this.listAdjacent.put(i, adjacents);
        }
    }

    /**
     * obtient le sommet en spécifiant son identifiant
     */
    public Sommet getSommetById(int id) {
        return sommets.get(id);
    }

    public ArrayList<Sommet> getSommetsListe() {
        Collection<Sommet> values = sommets.values();
        ArrayList<Sommet> a = new ArrayList<Sommet>(values);
        return a;
    }

    public int getSize() {
        return sommets.size();
    }

    public void addSommet(int id, int value, int degree) {
        this.addSommet(new Sommet(id, value, degree));
    }

    public void addSommet(Sommet s) {
        sommets.put(s.getId(), s);
    }

    public void addArc(Sommet s1, Sommet s2) {
        sommets.get(s1.getId()).addArc();
        sommets.get(s2.getId()).addArc();
    }

    public void addArc(int IdS1, int IdS2) {
        sommets.get(IdS1).addArc();
        sommets.get(IdS2).addArc();
    }

    public boolean idEstDansLaListe(int id) {
        return sommets.containsKey(id);
    }

    public boolean estDansLaListe(Sommet s) {
        return sommets.containsKey(s.getId());
    }


    public void soustractionListe(ListeSommet lstS2) {
        for (Sommet s : lstS2.sommets.values()) {
            this.sommets.remove(s.getId());
            //suppression de la liste des adjacent et diminution des degrès associés :
            ArrayList<Integer> adjacentASupprimer = this.listAdjacent.get(s.getId());
            for (Integer i : adjacentASupprimer) {
                this.listAdjacent.get(i).remove((Integer) s.getId());
                Sommet adj = this.sommets.get(i);
                adj.setDegre(adj.getDegre() - 1);
            }
            this.listAdjacent.remove(s.getId());
        }
    }

    @Override
    public String toString() {
        return " " + sommets;
    }

    @Override
    public ListeSommet clone() {
        return new ListeSommet(this);
    }
}
