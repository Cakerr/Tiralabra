/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import tira.gui.Karttaruutu;
import tira.logiikka.Solver;

/**
 *
 * @author Ari
 */
public class AloitusListener implements MouseListener {

    private Solver ratkaisija;
    private Karttaruutu kartta;

    public AloitusListener(Solver ratkaisija, Karttaruutu kartta) {
        this.ratkaisija = ratkaisija;
        this.kartta = kartta;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ratkaisija.ratkaise();
        kartta.asetaAika();
        kartta.maalaaReitti();
        
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
