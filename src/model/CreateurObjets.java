package model;

import java.util.Random;

import view.Affichage;

public class CreateurObjets {

    public static Random RAND = new Random();

    Position position;

    public static ObjetDetectable[] objets = new ObjetDetectable[10];
    public Character hacene;

    public CreateurObjets(Position position) {
        this.position = position;
        for (int i = 0; i < objets.length; i++) {
            objets[i] = new ObjetDetectable();
        }
        hacene = new Character();
    }

    /* créer les objets initiaux */
    public void setObjects() {
        for (int i = 0; i < 10; i++) {
            objets[i].setXpos(Affichage.LARGEUR_ECRAN + RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);
            objets[i].setYpos(RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);
            if (i % 2 == 0) {
                objets[i].setImage("/model/images/coins.png");
            } else {

                objets[i].setImage("/model/images/Fruits.png");
            }
        }

        hacene.setXpos(Affichage.POSITION_EN_X);
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
            if (objets[i].getXpos() < Position.BEFORE) {
                objets[i].setXpos(Affichage.LARGEUR_ECRAN + RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);
                objets[i].setYpos(RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);
                if (i % 2 == 0) {
                    objets[i].setImage("/model/images/coins.png");
                } else {
                    objets[i].setImage("/model/images/Fruits.png");
                }
            }
        }
    }
}
