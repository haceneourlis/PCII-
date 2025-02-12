package model;

import java.util.Random;

public class CreateurObjets {

    public static Random Rand = new Random();

    private Position position;

    public static ObjetDetectable[] objets = new ObjetDetectable[10];
    public static Ovale ovaleDuJeu;

    public CreateurObjets(Position position) {
        this.position = position;
        for (int i = 0; i < objets.length; i++) {
            objets[i] = new ObjetDetectable(position);
        }
        ovaleDuJeu = new Ovale();
    }

    /* créer les objets initiaux */
    public void setObjects() {
        for (int i = 0; i < 10; i++) {

            objets[i].setXpos(Position.AFTER + Rand.nextInt(0, 10) * Position.TAILLE_CELLULE);
            int ypos = Rand.nextInt(Position.HAUTEUR_MAX - Position.HAUTEUR_MIN) + Position.HAUTEUR_MIN;

            objets[i].setYpos(ypos);
            if (i % 2 == 0) {
                objets[i].setImage("/model/images/coins.png");
            } else {

                objets[i].setImage("/model/images/Fruits.png");
            }
        }
    }

    public ObjetDetectable[] getObjets() {
        for (ObjetDetectable o : objets) {
            o.setXpos(o.getXpos() - position.avancement);
        }
        return objets;
    }

    /* I need to keep them coming though ! */
    public void creer_new_objets() {
        for (int i = 0; i < 10; i++) {
            if (objets[i].getXpos() < 0) {

                objets[i].setXpos(Position.AFTER + Rand.nextInt(0, 10) * Position.TAILLE_CELLULE);
                int ypos = Rand.nextInt(Position.HAUTEUR_MAX - Position.HAUTEUR_MIN) + Position.HAUTEUR_MIN;

                objets[i].setYpos(ypos);
                if (i % 2 == 0) {
                    objets[i].setImage("/model/images/coins.png");
                } else {
                    objets[i].setImage("/model/images/Fruits.png");
                }
            }
        }
    }
}
