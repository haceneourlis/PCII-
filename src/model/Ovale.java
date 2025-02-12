package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ovale extends ObjetDetectable {

    private int scoreGame = 0;

    public Ovale() {
        super();
        this.solidArea = new Rectangle(this.getXpos(), this.getYpos(), Position.LARGEUR_OVALE, Position.HAUTEUR_OVALE); // Define
                                                                                                                        // collision
                                                                                                                        // area
    }

    public void incrScore() {
        scoreGame++;
    }

    public int getScore() {
        return scoreGame;
    }

    public void resetScore() {
        this.scoreGame = 0;
    }

    public void setSolidArea() {
        this.solidArea.setBounds(this.getXpos(), this.getYpos(), Position.LARGEUR_OVALE, Position.HAUTEUR_OVALE);
    }

    @Override
    public void draw(Graphics2D g2) {
        // Dessiner l'oval
        Graphics g = g2;
        g.setColor(Color.RED);
        g.fillOval(this.getXpos(), this.getYpos(), Position.LARGEUR_OVALE, Position.HAUTEUR_OVALE);

        g.drawOval(this.getXpos(), this.getYpos(), Position.LARGEUR_OVALE, Position.HAUTEUR_OVALE);
        // Dessiner la zone de collision opour debugger
        g.setColor(Color.BLUE);
        g.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
    }
}
