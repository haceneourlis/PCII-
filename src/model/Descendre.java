package model;

import view.Affichage;

public class Descendre extends Thread {
    private Position p;
    private Affichage monAffichage; // Game panel

    public Descendre(Position p, Affichage monAffichage) {
        this.p = p;
        this.monAffichage = monAffichage;
    }

    @Override
    public void run() {
        while (true) {
            /**
             * !!!!!!!!!! IMPORTANT !!!!!!!!!!
             * 
             * Au début, j'avais utilisé synchronized(this), mais j'ai remarqué que le
             * thread
             * Descendre ne se réveillait pas lorsque notifyAll() était appelé dans
             * Affichage.
             * 
             * D'après la documentation Java, lorsqu'on utilise synchronized(GamePanel),
             * on force ce thread à attendre sur l'objet GamePanel plutôt que sur lui-même.
             * 
             * Cela signifie que lorsque notifyAll() est appelé sur GamePanel (Affichage),
             * tous les threads qui attendent sur cet objet (y compris Descendre) seront
             * réveillés.
             * 
             * C'est crucial, car GamePanel (Affichage) est le cœur de la gestion de
             * l'affichage
             * et doit coordonner les threads graphiques (Redessine) et ceux liés au
             * contrôle
             * (comme Descendre et DetectionCollision).
             * 
             * Ainsi, en synchronisant sur GamePanel, on garantit que tous ces threads
             * s'arrêtent et redémarrent en même temps, évitant toute désynchronisation
             * entre l'affichage et la logique du jeu.
             */

            synchronized (monAffichage) { // Synchronize with the game panel
                while (Affichage.PAUSE) {
                    try {
                        monAffichage.wait(); // Wait until game resumes
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(48);
                p.move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
