package view;

import javax.swing.*;

import controller.PauseMenuController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.CreateurObjets;
import model.ObjetDetectable;
import model.Parcours;
import model.Position;

import java.awt.*;

public class Affichage extends JPanel implements KeyListener {

    public static boolean PAUSE = false;

    public int RATIO_X = 2;
    public int RATIO_Y = 3;

    public int LARGEUR_ECRAN = (Position.BEFORE + Position.AFTER) * RATIO_X;
    public int HAUTEUR_ECRAN = (Position.HAUTEUR_MAX - Position.HAUTEUR_MIN) * RATIO_Y;

    public static final int LARGEUR_OVALE_VUE = 20;

    private Position pos_character;
    private Parcours parcours;
    public CreateurObjets cb;

    private PauseMenuController pauseMenuController;

    public Affichage(Position pos, Parcours parcours) {
        super();
        this.parcours = parcours;
        pos_character = pos;
        this.cb = new CreateurObjets(pos_character);
        this.cb.setObjects();
        this.setPreferredSize(new Dimension(LARGEUR_ECRAN, HAUTEUR_ECRAN));

        // pour que le panel soit focusable
        this.setFocusable(true);
        this.addKeyListener(this);

        // ajouter un controller pour le menu pause
        pauseMenuController = new PauseMenuController(this);

        (new Redessine(this)).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (!PAUSE) {
            this.updateRatios();
            int yPos = (Position.HAUTEUR_MAX - pos_character.get_hauteur() - Position.HAUTEUR_OVALE)
                    * RATIO_Y;
            int xPos = (Position.BEFORE * RATIO_X) - LARGEUR_OVALE_VUE / 2;
            CreateurObjets.ovaleDuJeu.setYpos(yPos);
            CreateurObjets.ovaleDuJeu.setXpos(xPos);

            draw_Ovale(g, xPos, yPos);
            draw_points(g);
            draw_line(g);
            draw_objets(g);
            drawScore(g);
            cb.creer_new_objets();
        }
    }

    /*
     * affichage d'une ligne tout au long de la fenetre en reliant les points deux
     * par deux
     */
    /*
     * affichage d'une ligne tout au long de la fenetre en reliant les points deux
     * par deux
     */
    private void draw_line(Graphics g) {
        synchronized (parcours) {
            g.setColor(Color.BLUE); // Choose a color for the line

            java.util.List<Point> points = parcours.get_liste_points();
            for (int i = 0; i < points.size() - 1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);

                p1 = pos_character.transformToView(p1);
                p2 = pos_character.transformToView(p2);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    /* affichage de points */
    private void draw_points(Graphics g) {

        synchronized (parcours) { // bloquer le thread parcours pour éviter les problèmes de concurrence
            // si je fais pas ça , le thread parcours peut modifier la liste des points
            // pendant que je suis en train de la parcourir
            for (Point point : parcours.get_liste_points()) {
                /* afficher des points rond avec une vouleur rouge */
                g.setColor(Color.RED);
                Point new_point_vue = pos_character.transformToView(point);
                g.fillOval(new_point_vue.x, new_point_vue.y, 5, 5);
            }
        }
    }

    /* affichage des objets detectable : fruits et argent */
    private void draw_objets(Graphics g) {
        if (cb == null) {
            System.out.println("Warning: cb (CreateurObjets) is null in draw_objets()");
            return;
        }

        for (ObjetDetectable obj : cb.getObjets()) {
            obj.draw((Graphics2D) g);
        }
    }

    public void draw_Ovale(Graphics g, int x, int y) {
        // Dessiner l'oval
        g.setColor(Color.RED);
        // g.fillOval(this.getXpos(), this.getYpos(), Position.LARGEUR_OVALE,
        // Position.HAUTEUR_OVALE);
        g.drawOval(x, y, LARGEUR_OVALE_VUE, Position.HAUTEUR_OVALE * RATIO_X);
    }

    /* afficher le score actuel dans le TOP-LEFT , en grande polices */
    public void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score : " + CreateurObjets.ovaleDuJeu.getScore(), 10, 20);

    }

    /* exercice 2 */
    /* Get the current width of the game panel */
    public int getLargeurEcran() {
        return getWidth(); // Dynamically get panel width
    }

    /* Get the current height of the game panel */
    public int getHauteurEcran() {
        return getHeight(); // Dynamically get panel height
    }

    /* Update RATIO_X and RATIO_Y dynamically */
    private void updateRatios() {
        RATIO_X = getLargeurEcran() / (Position.BEFORE + Position.AFTER);
        RATIO_Y = getHauteurEcran() / (Position.HAUTEUR_MAX - Position.HAUTEUR_MIN);
    }

    /** Toggle PAUSE when ESC key is pressed */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            synchronized (this) {
                PAUSE = !PAUSE;
                if (PAUSE) {
                    pauseMenuController.showPauseMenu();
                } else {
                    pauseMenuController.hidePauseMenu();
                    this.notifyAll(); // le gamepanel dit à tous les threads de continuer
                }
            }
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
