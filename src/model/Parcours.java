package model;

import java.awt.Point;
import java.util.ArrayList;

import view.Affichage;

public class Parcours {
    java.util.Random rand = new java.util.Random();
    // reprise de l'algo du prof
    private static final int XMIN = 100;
    private static final int XMAX = 800;

    private static final int XDEPART = 200;
    private static final int YDEPART = 150;

    private static final int ECARTMIN = 10;
    private static final int ECARTMAX = 30;

    private Position position;
    private Affichage GamePanel;
    private ArrayList<Point> listePoints;

    public Parcours() {
        listePoints = new ArrayList<Point>();
        generer_points();
    }

    /* set game panel */
    public void setGamePanel(Affichage GamePanel) {
        this.GamePanel = GamePanel;
    }

    /* set the position */
    public void set_position(Position position) {
        this.position = position;
    }

    /*
     * créer une méthode qui va générer une liste de points
     * par X croissant , en se basant sur l'écart maximale entre deux point : X_MAX
     * et l'ecart minimale entre deux points : X_MIN définies précedemment
     * cette méthode doit etre privé et sera appelé dans le constructeur
     */

    private void generer_points() {
        listePoints.add(new Point(XMIN - 1, YDEPART));
        listePoints.add(new Point(XDEPART + 50, YDEPART));
        int x = XDEPART + ECARTMIN;

        while (x <= XMAX) {
            x += rand.nextInt(ECARTMAX - ECARTMIN);
            int y = rand.nextInt(Position.HAUTEUR_MAX - Position.HAUTEUR_MIN) + Position.HAUTEUR_MIN;
            listePoints.add(new Point(x, y));
        }
    }

    /*
     * Met à jour la position des points du parcours tout en gérant la concurrence
     * entre les threads.
     * Cette méthode est synchronisée pour éviter les conflits lorsque plusieurs
     * threads accèdent
     * simultanément à la liste des points.
     */
    public synchronized void update_ligne() {
        for (Point point : listePoints) {
            point.x -= position.avancement;
        }
        // Supprimer le premier point s'il sort de l'écran
        if (listePoints.size() > 1 && listePoints.get(1).x - position.avancement < 0) {
            listePoints.remove(1);
        }

        // Ajouter un nouveau point si le dernier est proche de l'horizon
        Point dernierPoint = listePoints.get(listePoints.size() - 1);
        if (dernierPoint.x - position.avancement < GamePanel.LARGEUR_ECRAN) {
            int x = dernierPoint.x + rand.nextInt(ECARTMAX - ECARTMIN);
            int y = rand.nextInt(Position.HAUTEUR_MAX - Position.HAUTEUR_MIN) + Position.HAUTEUR_MIN;
            listePoints.add(new Point(x, y));
        }
    }

    /*
     * une méthode getter , qui renvoie la liste des points en décalant la position
     * de
     * tous ces dernies de -Position.AVANCEMENT pour que la vue soit centrée sur
     * l'ovale
     */
    public ArrayList<Point> get_liste_points() {
        ArrayList<Point> listePoints = new ArrayList<Point>();
        for (Point point : this.listePoints) {
            listePoints.add(new Point(point.x - position.avancement, point.y));
        }
        // System.out.println("it was supposed to work then ?");
        return listePoints;

    }

}

/**
 * COMMENTAIRE IMPORTANT :
 * j'ai fait ça , et j'ai eu une erreu du type : ConcurrentModificationException
 * parce que un thread ( position ) est entrain de modifier la liste des points
 * et un autre thread ( affichage ) est entrain de la parcourir
 * 
 * donc j'ai refait la méthode en creant une copie de la liste .
 */
// public ArrayList<Point> get_liste_points() {
// for (Point point : listePoints) {
// point.x -= position.avancement;
// }
// return listePoints;