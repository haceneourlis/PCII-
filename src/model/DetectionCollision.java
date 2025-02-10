package model;

import java.awt.Graphics2D;
import view.Affichage;

public class DetectionCollision extends Thread {
    Affichage monAffichage;
    ObjetDetectable mainCharacter;

    public DetectionCollision(Affichage monAffichage, ObjetDetectable mainCharacter) {
        this.monAffichage = monAffichage;
        this.mainCharacter = mainCharacter;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (monAffichage) {
                while (Affichage.PAUSE) { // Pause the thread
                    try {
                        monAffichage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

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

                    if (mainCharacter != null && CreateurObjets.objets[i] != null) {
                        if (mainCharacter.solidArea.intersects(CreateurObjets.objets[i].solidArea)) {
                            System.out.println("Collision detected !");

                            // je redissine l'objet touché au début de l'horizon je ne le supprime pas.
                            CreateurObjets.objets[i]
                                    .setXpos(Affichage.LARGEUR_ECRAN
                                            + CreateurObjets.RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);
                            CreateurObjets.objets[i]
                                    .setYpos(CreateurObjets.RAND.nextInt(0, 10) * Position.TAILLE_CELLULE);

                            Character temp = (Character) mainCharacter;
                            temp.incrScoreMario();

                            System.out.println("Score de hacene : " + temp.getScoreMario());

                            monAffichage.drawYellowLine(monAffichage.getGraphics());
                            Thread.sleep(100); // pour voir la ligne jaune , sinon elle disparait trop vite
                            monAffichage.drawWhiteLine(monAffichage.getGraphics());
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
        for (int i = 0; i < 10; i++) {
            try {
                CreateurObjets.objets[i].draw(g2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
