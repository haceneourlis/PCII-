package view;

import javax.swing.*;

import model.CreateurObjets;
import model.ObjetDetectable;
import model.Parcours;
import model.Position;

import java.awt.*;

public class affichage extends JPanel {

    public static final int RATIO_X = 2;
    public static final int RATIO_Y = 3;

    public static final int LARGEUR_ECRAN = (Position.BEFORE + Position.AFTER) * RATIO_X;
    public static final int HAUTEUR_ECRAN = (Position.HAUTEUR_MAX - Position.HAUTEUR_MIN) * RATIO_Y;

    public static final int POSITION_EN_X = (Position.BEFORE * RATIO_X) - Position.LARGEUR_CHARCTER / 2;

    private Position pos_charcter;
    private Parcours parcours;
    public CreateurObjets cb;

    public affichage(Position pos, Parcours parcours) {
        super();
        this.parcours = parcours;
        pos_charcter = pos;
        this.cb = new CreateurObjets(pos_charcter);
        this.cb.setObjects();
        this.setPreferredSize(new Dimension((int) LARGEUR_ECRAN, (int) HAUTEUR_ECRAN));
        (new Redessine(this)).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // int position_en_y = (Position.HAUTEUR_MAX - pos_oval.get_hauteur() -
        // (Position.HAUTEUR_OVALE)) * RATIO_Y;

        // l'oval ici c'est mario (le personnage)

        cb.hacene
                .setYpos(Position.HAUTEUR_MAX - pos_charcter.get_hauteur() - (Position.HAUTEUR_CHARACTER) * RATIO_Y);

        // redessinner mario et les autres objets
        draw_objets(g);
        draw_Character(g);
        draw_points(g);

    }

    private void draw_points(Graphics g) {
        for (Point point : parcours.get_liste_points()) {
            g.drawRect(point.x * RATIO_X, point.y * RATIO_Y, 10, 10);
        }
    }

    private void draw_objets(Graphics g) {
        if (cb == null) {
            System.out.println("Warning: cb (CreateurObjets) is null in draw_objets()");
            return;
        }

        for (ObjetDetectable obj : cb.getObjets()) {
            obj.draw((Graphics2D) g);
        }
    }

    private void draw_Character(Graphics g) {
        cb.getMario().draw((Graphics2D) g);
    }

    public void drawYellowLine(Graphics g2) {
        g2.setColor(Color.YELLOW);
        g2.drawLine(affichage.LARGEUR_ECRAN / 2, 0, affichage.LARGEUR_ECRAN / 2, affichage.HAUTEUR_ECRAN);
    }

    public void drawWhiteLine(Graphics g2) {
        g2.setColor(Color.WHITE);
        g2.drawLine(affichage.LARGEUR_ECRAN / 2, 0, affichage.LARGEUR_ECRAN / 2, affichage.HAUTEUR_ECRAN);
    }

}
