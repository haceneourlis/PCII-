package model;

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

    /**
     * @param IMPULTION quand on click sur la souris l'ovale saute , donc on met
     *                  vitesse verticale = IMPULSION , pour que la position en Y de
     *                  l'ovale
     *                  augmentera de <IMPULSION> , mais cette augmention est "
     *                  imaginaire " càd
     *                  c'est une augmentation du modele mais pas de la vue , en
     *                  effet , à la vue en va descendre en bas car sur swing l'axe
     *                  Y est inversé
     */
    public static final int IMPULSION = 10;

    public static final int HAUTEUR_CHARACTER = 50;
    public static final int LARGEUR_CHARCTER = 10;

    public int avancement = 0;

    /**
     * @param hauteur position de l'ovale sur l'axe des Y initialement = 0
     */
    private int hauteur = 0;

    public double vitesseVerticale = 0;

    /*
     * retourne la hauteur de l'ovale dans la fenetre d'affiche
     * cette fonction servira à getter cette position en Y pour la mise à jour du
     * frame (affichage ) .
     */
    public int get_hauteur() {
        return hauteur;
    }

    public void move() {
        hauteur += (int) vitesseVerticale;
        vitesseVerticale -= 1;

        // Bloque la hauteur pour éviter que l'ovale ne sorte de l'écran
        // if (hauteur > HAUTEUR_MIN - HAUTEUR_CHARACTER / 2)
        // hauteur = HAUTEUR_MIN - HAUTEUR_CHARACTER / 2;
        // if (hauteur < HAUTEUR_MAX + HAUTEUR_CHARACTER / 2)
        // hauteur = HAUTEUR_MAX + HAUTEUR_CHARACTER / 2;

    }

    public void jump() {
        vitesseVerticale = IMPULSION;
    }

    /* Ajoutez un thread dans le modèle qui modifie Position.avancement */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                // System.out.println("hauteur : " + this.p.get_hauteur());
                // if(this.p.get_hauteur() != 0){
                avancement += 1;
                parcouuuurs.ligne_continue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
