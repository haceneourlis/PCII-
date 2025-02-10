package model;

public class Character extends ObjetDetectable {

    private int scoreMario = 0;

    public Character() {

        super();
        this.setImage("/model/images/mario.png");
    }

    public void incrScoreMario() {
        scoreMario++;
    }

    public int getScoreMario() {
        return scoreMario;
    }

    public void setScoreMario(int scoreMario) {
        this.scoreMario = scoreMario;
    }
}
