package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import view.Affichage;

public class Ovale extends ObjetDetectable {

    private int scoreGame = 0;

    public Ovale() {
        super();
        this.solidArea = new Rectangle(this.getXpos(), this.getYpos(), Affichage.LARGEUR_OVALE_VUE,
                Position.HAUTEUR_OVALE); // Define
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
        this.solidArea.setBounds(this.getXpos(), this.getYpos(), Affichage.LARGEUR_OVALE_VUE, Position.HAUTEUR_OVALE);
    }

}
