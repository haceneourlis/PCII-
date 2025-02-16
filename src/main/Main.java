package main;

import javax.swing.*;

import controller.ReactionClic;
import model.Descendre;
import model.DetectionCollision;
import model.Parcours;
import model.Position;
import view.Affichage;

/** La classe principale de ce projet */
public class Main {

  /** La méthode de lancement du programme */
  public static void main(String[] args) {
    Thread.currentThread().setPriority(1);

    JFrame maFenetre = new JFrame("Exercice 1");

    Parcours parcours = new Parcours();
    Position pp = new Position(parcours);
    parcours.set_position(pp);
    Affichage GamePanel = new Affichage(pp, parcours);
    pp.setGamePanel(GamePanel);
    parcours.setGamePanel(GamePanel);

    ReactionClic rc = new ReactionClic(pp);

    /* lancer les thread */
    (new Descendre(pp, GamePanel)).start(); // le 2 eme thread est la : pour faire descendre le personnage ( GRAVIITE )
    // le 3eme thread : pour faire avancer le personnage ( avancer la ligne de
    // parcours )
    pp.start();
    // le 4eme thread : pour detecter les collisions ( Classe Rectangle )
    (new DetectionCollision(GamePanel, parcours)).start();

    GamePanel.addMouseListener(rc);
    maFenetre.add(GamePanel);

    maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    maFenetre.pack();
    maFenetre.setVisible(true);
  }

}
