package model;

import java.util.Random;

import view.affichage;

public class CreateurObjets {

    public static Random RAND = new Random();

    Position position;

    public static ObjetDetectable[] objets = new ObjetDetectable[10];
    public Character hacene;

    public CreateurObjets(Position position) {
        this.position = position;
        for (int i = 0; i < objets.length; i++) {
            objets[i] = new ObjetDetectable(); // Ensure each object is instantiated
        }
        hacene = new Character();
    }

    public void setObjects() {
        for (int i = 0; i < 10; i++) {
            objets[i].setXpos(affichage.LARGEUR_ECRAN + RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);
            objets[i].setYpos(RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);
            if (i % 2 == 0) {
                objets[i].setImage("/model/images/coins.png");
            } else {

                objets[i].setImage("/model/images/Fruits.png");
            }
        }

        hacene.setXpos(affichage.POSITION_EN_X);
    }

    public ObjetDetectable getMario() {
        return hacene;
    }

    public ObjetDetectable[] getObjets() {
        /*
         * for loop on the array objets and set the X position to : objet.getXpos -
         * avancement
         */

        for (ObjetDetectable o : objets) {
            o.setXpos(o.getXpos() - position.avancement / 2);
        }
        return objets;
    }

    public Character getMarioObjet() {
        return (Character) hacene;
    }

}
