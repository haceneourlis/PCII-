package view;

public class Redessine extends Thread {
    affichage monAffichage;

    Redessine(affichage a) {
        monAffichage = a;
    }

    final int DELAY = 50;

    @Override
    public void run() {

        while (true) {
            monAffichage.repaint();
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
