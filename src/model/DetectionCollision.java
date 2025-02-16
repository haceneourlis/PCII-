package model;

import java.awt.Graphics2D;
import view.Affichage;

public class DetectionCollision extends Thread {
    Affichage GamePanel;
    Parcours parcours;

    public DetectionCollision(Affichage GamePanel, Parcours parcours) {
        this.GamePanel = GamePanel;
        this.parcours = parcours;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (GamePanel) {
                while (Affichage.PAUSE) {
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
                                    .setXpos(GamePanel.getWidth()
                                            + CreateurObjets.Rand.nextInt(0, 10) * Position.TAILLE_CELLULE);
                            CreateurObjets.objets[i]
                                    .setYpos(CreateurObjets.Rand.nextInt(0, 10) * Position.TAILLE_CELLULE);

                            CreateurObjets.ovaleDuJeu.incrScore();

                            System.out.println("Score de hacene : " + CreateurObjets.ovaleDuJeu.getScore());
                            GamePanel.repaint();
                        }
                    }

                    // detection de collision avec la ligne brisé , en parcourant toute la liste des
                    // points
                    // Détecter des collisions avec la ligne et déclencher une animation visuelle en
                    // cas de collision à l’aide d’un thread.
                    // Indication pour la détection de collision : la pente de la ligne entre deux
                    // points est donnée par (Y2-Y1)/(X2-X1). La valeur en Y du point de collision
                    // d’un objet situé en absice X est donc obtenu par (X-X1)*PENTE.
                    // Si la valeur en Y du point de collision est inférieure à la valeur en Y du
                    // point le plus bas de la ligne et supérieure à la valeur en Y du point le plus
                    // haut de la ligne, alors il y a collision.

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
