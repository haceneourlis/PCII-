package main;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {
        /* Une fenêtre avec pour titre "Test" */
        JFrame frame = new javax.swing.JFrame("Test");
        /*
         * Un bouton "coucou" qui écrit "coucou" dans la console chaque fois qu'on
         * clique dessus
         */
        JButton button = new JButton("coucou");
        button.addActionListener(e -> System.out.println("coucou"));
        /* On ajoute le bouton à la fenêtre */
        frame.add(button);
        /* On fixe la taille de la fenêtre */
        frame.setSize(200, 200);
        /* On rend la fenêtre visible */
        frame.setVisible(true);
        /* On définit l'action à effectuer quand on ferme la fenetre */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
