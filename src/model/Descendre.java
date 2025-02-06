package model;

public class Descendre extends Thread {

    Position p;

    public Descendre(Position p) {
        this.p = p;

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                // System.out.println("hauteur : " + this.p.get_hauteur());
                // if(this.p.get_hauteur() != 0){
                this.p.move();
                // }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
