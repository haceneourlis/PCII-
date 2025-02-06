package model;

import java.awt.Point;
import java.util.ArrayList;

public class Parcours {
    java.util.Random rand = new java.util.Random();
    // reprise de l'algo du prof
    private static final int XMIN = 100;
    private static final int XMAX = 800;

    private static final int YMIN = 50;
    private static final int YMAX = 300;

    private static final int XDEPART = 200;
    private static final int YDEPART = 150;

    private static final int ECARTMIN = 10;
    private static final int ECARTMAX = 30;

    private Position position;
    private ArrayList<Point> listePoints = new ArrayList<Point>();

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
            x += rand.nextInt(ECARTMAX - ECARTMIN) + ECARTMIN;
            int y = rand.nextInt(YMAX - YMIN) + YMIN;
            listePoints.add(new Point(x, y));
        }
    }

    /* une méthode qui met à jour le parcours */
    public void ligne_continue() {
        // Supprimer le premier point s'il sort de l'écran
        if (listePoints.size() > 1 && listePoints.get(1).x - position.avancement < 0) {
            listePoints.remove(1);
        }

        // Ajouter un nouveau point si le dernier est proche de l'horizon
        Point dernierPoint = listePoints.get(listePoints.size() - 1);
        if (dernierPoint.x - position.avancement < Position.AFTER) {
            int x = dernierPoint.x + rand.nextInt(ECARTMAX - ECARTMIN) + ECARTMIN;
            int y = rand.nextInt(YMAX - YMIN) + YMIN;
            listePoints.add(new Point(x, y));
        }
    }

    /* constructeur */
    public Parcours() {
        generer_points();
    }

    /* set the position */
    public void set_position(Position position) {
        this.position = position;
    }

    /*
     * une méthode getter , qui renvoie la liste des points en décalant la position
     * de
     * tous ces dernies de -Position.AVANCEMENT pour que la vue soit centrée sur
     * l'ovale
     */
    public ArrayList<Point> get_liste_points() {
        ArrayList<Point> liste_points_decale = new ArrayList<Point>();
        for (Point point : listePoints) {
            liste_points_decale.add(new Point(point.x - position.avancement, point.y));
        }
        return liste_points_decale;
    }

}