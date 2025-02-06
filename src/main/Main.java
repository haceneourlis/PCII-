package main;

import javax.swing.*;

import controller.ReactionClic;
import model.Descendre;
import model.Parcours;
import model.Position;
import view.DetectionCollision;
import view.affichage;

/** La classe principale de ce projet */
public class Main {

  /** La méthode de lancement du programme */
  public static void main(String[] args) {
    JFrame maFenetre = new JFrame("Exercice 1");

    Parcours parcours = new Parcours();
    Position pp = new Position(parcours);
    parcours.set_position(pp);

    affichage toto = new affichage(pp, parcours);
    ReactionClic rc = new ReactionClic(pp);

    (new Descendre(pp)).start(); // le 2 eme thread est la
    pp.start(); // le 3eme thread
    (new DetectionCollision(toto, toto.cb.hacene)).start(); // le 4eme thread

    toto.addMouseListener(rc);
    maFenetre.add(toto);

    maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    maFenetre.pack();
    maFenetre.setVisible(true);
  }

}
