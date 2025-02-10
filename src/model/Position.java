package model;

import view.Affichage;

public class Position extends Thread {
    public static final int TAILLE_CELLULE = 25;
    public static final int BEFORE = 100;
    public static final int AFTER = 300;

    public static final int HAUTEUR_MIN = -50;
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
                Thread.sleep(100);

                // Vérifie que ce bloc de code est bien exécuté
                System.out.println(" so you do enter this block ( Position ) ? ");

                // Met à jour la position des objets dans le jeu
                parcouuuurs.update_ligne();
            } catch (Exception e) {
                // Affiche toute erreur qui pourrait survenir
                e.printStackTrace();
            }
        }
    }

}
