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

    public static final int RATIO_X = 2;
    public static final int RATIO_Y = 3;

    public static final int LARGEUR_ECRAN = (Position.BEFORE + Position.AFTER) * RATIO_X;
    public static final int HAUTEUR_ECRAN = (Position.HAUTEUR_MAX - Position.HAUTEUR_MIN) * RATIO_Y;

    public static final int POSITION_EN_X = (Position.BEFORE * RATIO_X) - Position.LARGEUR_CHARCTER / 2;

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
            cb.hacene.setYpos(
                    Position.HAUTEUR_MAX - pos_character.get_hauteur() - (Position.HAUTEUR_CHARACTER) * RATIO_Y);
            draw_objets(g);
            draw_Character(g);
            draw_points(g);
            drawScore(g);
            cb.creer_new_objets();
        }
    }

    /* affichage de points */
    private void draw_points(Graphics g) {
        for (Point point : parcours.get_liste_points()) {
            /* afficher des points rond avec une vouleur rouge */
            g.setColor(Color.RED);
            g.fillOval(point.x * RATIO_X, HAUTEUR_ECRAN - point.y * RATIO_Y, 5, 5);
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

    /* affichage du personnage */
    private void draw_Character(Graphics g) {
        cb.hacene.draw((Graphics2D) g);
    }

    public void drawYellowLine(Graphics g2) {
        g2.setColor(Color.YELLOW);
        g2.drawLine(Affichage.LARGEUR_ECRAN / 2, 0, Affichage.LARGEUR_ECRAN / 2, Affichage.HAUTEUR_ECRAN);
    }

    public void drawWhiteLine(Graphics g2) {
        g2.setColor(Color.WHITE);
        g2.drawLine(Affichage.LARGEUR_ECRAN / 2, 0, Affichage.LARGEUR_ECRAN / 2, Affichage.HAUTEUR_ECRAN);
    }

    /* afficher le score actuel dans le TOP-LEFT , en grande polices */
    public void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score : " + cb.hacene.getScoreMario(), 10, 20);

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
                    this.notifyAll(); // Resume all paused threads
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
