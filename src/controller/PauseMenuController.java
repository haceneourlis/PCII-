package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import view.Affichage;

public class PauseMenuController implements MouseListener {
    private Affichage gamePanel; // mon affichage
    private JPanel pausePanel;
    private JButton reprendre, recommencer, quitter;

    public PauseMenuController(Affichage gamePanel) {
        this.gamePanel = gamePanel;
        setupPauseMenu();
    }

    /** Create Pause Menu Panel */
    private void setupPauseMenu() {
        pausePanel = new JPanel();
        pausePanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column
        pausePanel.setBounds(Affichage.LARGEUR_ECRAN / 2 - 75, Affichage.HAUTEUR_ECRAN / 2 - 75, 150, 200);
        pausePanel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent black

        JLabel pauseLabel = new JLabel("Game Paused", SwingConstants.CENTER);
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 18));

        reprendre = new JButton("Reprendre");
        recommencer = new JButton("Recommencer");
        quitter = new JButton("Quitter");

        // ajout des listeners
        reprendre.addMouseListener(this);
        recommencer.addMouseListener(this);
        quitter.addMouseListener(this);

        // ajouter les composants au panel
        pausePanel.add(pauseLabel);
        pausePanel.add(reprendre);
        pausePanel.add(recommencer);
        pausePanel.add(quitter);

        pausePanel.setVisible(false); // au début le panel est caché
        gamePanel.setLayout(null); // Set layout to null to use absolute positioning : github copilot suggestion .
        gamePanel.add(pausePanel); // Add pause panel to game panel (Affichage)
    }

    /** Show Pause Menu */
    public void showPauseMenu() {
        pausePanel.setVisible(true);
    }

    /** Hide Pause Menu */
    public void hidePauseMenu() {
        pausePanel.setVisible(false);
    }

    /** Resume the game */
    private void resumeGame() {
        synchronized (gamePanel) {
            Affichage.PAUSE = false;
            gamePanel.notifyAll(); // dire à tous les threads en pause de continuer
        }
        hidePauseMenu();
        gamePanel.repaint();
    }

    /** Restart the game */
    private void restartGame() {
        synchronized (gamePanel) {
            Affichage.PAUSE = false;
            gamePanel.notifyAll(); // dire à tous les threads en pause de continuer
        }
        gamePanel.cb.hacene.setScoreMario(0);
        gamePanel.cb.setObjects();
        hidePauseMenu();
        gamePanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == reprendre) {
            resumeGame();
        } else if (e.getSource() == recommencer) {
            restartGame();
        } else if (e.getSource() == quitter) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
