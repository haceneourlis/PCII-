package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjetDetectable extends Rectangle {
    private int xpos, ypos;
    public static final int TAILLE_CELLULE = 25;

    private BufferedImage image;
    public Rectangle solidArea = new Rectangle(0, 0, Position.TAILLE_CELLULE, Position.TAILLE_CELLULE); // pour m'aider
    // à
    // détecter les
    // collisions .

    private Position position;

    public ObjetDetectable() {
    }

    public ObjetDetectable(Position position) {
        super();
        this.position = position;
    }

    public void setSolidArea() {
        this.solidArea.setBounds(this.getXpos(), this.getYpos(), Position.TAILLE_CELLULE, Position.TAILLE_CELLULE);
    }

    public void setImage(String pathToImage) {
        try {
            // System.out.println("Trying to load image: " + pathToImage);
            java.net.URL imgUrl = getClass().getClassLoader().getResource(pathToImage.substring(1));

            if (imgUrl == null) {
                System.out.println("ERROR: Image not found at path: " + pathToImage);
                throw new IllegalArgumentException("Image not found: " + pathToImage);
            }

            // System.out.println("printing from the class ObjetDetectable");
            // System.out.println("Image found: " + imgUrl);
            this.image = ImageIO.read(imgUrl);
            // ystem.out.println("Image successfully loaded: " + pathToImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setXpos(int x) {
        xpos = x;
    }

    public void setYpos(int y) {
        ypos = y;
    }

    public void draw(Graphics2D g2) {
        try {
            Point p = new Point(this.getXpos(), this.getYpos());
            p = position.transformToView(p);
            g2.drawImage(this.image, p.x, p.y, TAILLE_CELLULE, TAILLE_CELLULE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
