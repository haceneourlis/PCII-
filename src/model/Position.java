package model;

import view.Affichage;

public class Position extends Thread {
    public static final int TAILLE_CELLULE = 25;
    public static final int BEFORE = 100;
    public static final int AFTER = 300;

    public static final int HAUTEUR_MIN = -50;
    public static final int HAUTEUR_MAX = 50;

    private Parcours parcouuuurs;

    public Position(Parcours parcours) {
        this.parcouuuurs = parcours;
    }

    public static final int IMPULSION = 10;
    public static final int HAUTEUR_CHARACTER = 50;
    public static final int LARGEUR_CHARCTER = 10;

    public int avancement = 5;
    private int hauteur = 0;
    public double vitesseVerticale = 0;

    public int get_hauteur() {
        return hauteur;
    }

    public void move() {
        hauteur += (int) vitesseVerticale;
        vitesseVerticale -= 0.5;
    }

    public void jump() {
        vitesseVerticale = IMPULSION;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) { // pause le thread Position
                while (Affichage.PAUSE) { // si le jeu est en pause

                    try {
                        wait(); // attendre la fin de la pause pour continuer
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(100);
                // avancement += 1;

                // if (avancement > 10) {
                // avancement = 1;
                // }
                parcouuuurs.ligne_continue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
