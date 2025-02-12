package view;

public class Redessine extends Thread {
    Affichage monAffichage;

    Redessine(Affichage a) {
        monAffichage = a;
    }

    final int DELAY = 10;

    @Override
    public void run() {
        while (true) {
            synchronized (monAffichage) {
                while (Affichage.PAUSE) { // si le jeu est en pause
                    try {
                        monAffichage.wait(); // attendre
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            monAffichage.revalidate();
            monAffichage.repaint(); // réafficher la frame (le panel)
            try {

                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
