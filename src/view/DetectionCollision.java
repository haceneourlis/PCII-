package view;

import model.Position;

import java.awt.Graphics2D;

import model.CreateurObjets;
import model.Character;
import model.ObjetDetectable;

public class DetectionCollision extends Thread {

    affichage a;
    ObjetDetectable mainCharacter;

    public DetectionCollision(affichage a, ObjetDetectable mainCharacter) {
        this.a = a;
        this.mainCharacter = mainCharacter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                for (int i = 0; i < 10; i++) {

                    if (CreateurObjets.objets[i] != null && CreateurObjets.objets[i].solidArea != null) {
                        CreateurObjets.objets[i].solidArea.setLocation(
                                CreateurObjets.objets[i].getXpos(),
                                CreateurObjets.objets[i].getYpos());
                    }

                    if (mainCharacter != null && mainCharacter.solidArea != null) {
                        mainCharacter.solidArea.setLocation(mainCharacter.getXpos(), mainCharacter.getYpos());
                    }

                    System.out.println("hacene Y pos : " + mainCharacter.getYpos());

                    if (mainCharacter != null && CreateurObjets.objets[i] != null) {
                        if (mainCharacter.solidArea.intersects(CreateurObjets.objets[i].solidArea)) {
                            System.out.println("Collision detected !");
                            // afficher l'objet qui a été touché par hacene au début de l'horizon ( comme si
                            // ça venait d'etre crée )
                            // pas besoin d'etre supprimer !
                            CreateurObjets.objets[i]
                                    .setXpos(affichage.LARGEUR_ECRAN
                                            + CreateurObjets.RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);
                            CreateurObjets.objets[i]
                                    .setYpos(CreateurObjets.RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);

                            /* augmenter le score de hacene */
                            Character temp = (Character) mainCharacter;
                            temp.incrScoreMario();

                            System.out.println("Score de hacene : " + temp.getScoreMario());

                            /* afficher une sorte d'éclaire visuel au milieu de l'écran */
                            a.drawYellowLine(a.getGraphics());
                            Thread.sleep(100); // Show effect for 100ms
                            a.drawWhiteLine(a.getGraphics());

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* fonction : draw ; qui dessine toutes les tuiles */
    public void draw(Graphics2D g2) {
        /*
         * loop over the CreateurObjets.objets objects and call the draw image function
         */
        for (int i = 0; i < 10; i++) {
            try {
                CreateurObjets.objets[i].draw(g2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
