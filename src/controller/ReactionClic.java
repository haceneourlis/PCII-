package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Position;

public class ReactionClic implements MouseListener {

    Position p;

    public ReactionClic(Position pp) {
        p = pp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        p.jump();
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
