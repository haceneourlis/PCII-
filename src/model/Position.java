package model;

import java.awt.Point;

import view.Affichage;

public class Position extends Thread {
    public static final int TAILLE_CELLULE = 25;
    public static final int BEFORE = 100;
    public static final int AFTER = 300;

    public static final int HAUTEUR_MIN = -20;
    public static final int HAUTEUR_MAX = 50;

    private Parcours parcouuuurs;
    private Affichage GamePanel; // mon affichage

    public Position(Parcours parcours) {
        this.parcouuuurs = parcours;

    }

    // setter pour le GamePanel ( Affichage ) : TRES IMPORTANT
    public void setGamePanel(Affichage GamePanel) {
        this.GamePanel = GamePanel;
    }

    public Affichage getGamePanel() {
        return this.GamePanel;
    }

    public static final int IMPULSION = 5;
    public static final int HAUTEUR_OVALE = 25;

    public int avancement = 5;
    private int hauteur = HAUTEUR_MIN; // hauteur désigne la coordonnées en y du bas de l'ovale
    public double vitesseVerticale = 0;

    public int get_hauteur() {
        return hauteur;
    }

    public void move() {
        // je commence à mieux comprendre...
        if (hauteur + vitesseVerticale < HAUTEUR_MIN) {
            hauteur = HAUTEUR_MIN;
            vitesseVerticale = 0;
        }
        if (hauteur + vitesseVerticale > HAUTEUR_MAX - HAUTEUR_OVALE) {
            hauteur = HAUTEUR_MAX - HAUTEUR_OVALE;
            vitesseVerticale = 0;
        } else {
            hauteur += (int) vitesseVerticale;
            vitesseVerticale -= 0.5;
        }

    }

    public void jump() {
        vitesseVerticale = IMPULSION;
    }

    /* une fonction qui transforme chaque point du model en un point de la vue */
    public Point transformToView(Point modelPoint) {
        int xView = modelPoint.x * GamePanel.RATIO_X;
        int yView = (Position.HAUTEUR_MAX - modelPoint.y) * GamePanel.RATIO_Y;
        return new Point(xView, yView);
    }

    @Override
    public void run() {
        // Boucle infinie pour faire fonctionner le thread en continu
        while (true) {
            // Bloque l'accès à GamePanel pour éviter les conflits avec d'autres threads
            synchronized (GamePanel) {
                // Tant que le jeu est en pause, le thread attend
                while (Affichage.PAUSE) {
                    try {
                        // Met en pause ce thread jusqu'à ce que GamePanel le réveille
                        // c'est important d'ecrire GamePanel.wait() et non pas this.wait()
                        // car on veut que le thread Position soit en pause et le thread qui le reveille
                        // est bien GamePanel
                        GamePanel.wait();
                    } catch (InterruptedException e) {
                        // Affiche l'erreur si le thread est interrompu
                        e.printStackTrace();
                    }
                }
            }

            try {
                // Pause de 100ms pour ralentir la mise à jour du jeu
                Thread.sleep(50);

                // Met à jour la position des objets dans le jeu
                parcouuuurs.update_ligne();
                // avancement += 1;
                GamePanel.revalidate();
                GamePanel.repaint();
            } catch (Exception e) {
                // Affiche toute erreur qui pourrait survenir
                e.printStackTrace();
            }
        }
    }

}
