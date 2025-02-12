package model;

import java.awt.Graphics2D;
import view.Affichage;

public class DetectionCollision extends Thread {
    Affichage GamePanel;

    public DetectionCollision(Affichage GamePanel) {
        this.GamePanel = GamePanel;

    }

    @Override
    public void run() {
        while (true) {
            synchronized (GamePanel) {
                while (Affichage.PAUSE) { // Pause the thread
                    try {
                        GamePanel.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(16);
                for (int i = 0; i < 10; i++) {
                    if (CreateurObjets.objets[i] != null && CreateurObjets.objets[i].solidArea != null) {
                        CreateurObjets.objets[i].setSolidArea();
                    }

                    if (CreateurObjets.ovaleDuJeu != null && CreateurObjets.ovaleDuJeu.solidArea != null) {
                        CreateurObjets.ovaleDuJeu.setSolidArea();
                    }

                    if (CreateurObjets.ovaleDuJeu != null && CreateurObjets.objets[i] != null) {
                        if (CreateurObjets.ovaleDuJeu.solidArea.intersects(CreateurObjets.objets[i].solidArea)) {
                            System.out.println("Collision detected !");

                            // je redissine l'objet touché au début de l'horizon je ne le supprime pas.
                            CreateurObjets.objets[i]
                                    .setXpos(GamePanel.LARGEUR_ECRAN
                                            + CreateurObjets.Rand.nextInt(0, 10) * Position.TAILLE_CELLULE);
                            CreateurObjets.objets[i]
                                    .setYpos(CreateurObjets.Rand.nextInt(0, 10) * Position.TAILLE_CELLULE);

                            CreateurObjets.ovaleDuJeu.incrScore();

                            System.out.println("Score de hacene : " + CreateurObjets.ovaleDuJeu.getScore());
                            GamePanel.repaint();
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
