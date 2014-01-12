/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JRadioButton;
import tira.gui.Karttaruutu;
import tira.logiikka.Solver;

/**
 *
 * @author Ari
 */
public class AloitusListener implements MouseListener {

    private Solver ratkaisija;
    private Karttaruutu kartta;
    private JRadioButton jps;
    private JRadioButton astar;
    private JRadioButton dijkstra;
    /**
     * 
     * @param ratkaisija
     * @param kartta
     * @param astar
     * @param jps
     * @param dijkstra
     */
    public AloitusListener(Solver ratkaisija, Karttaruutu kartta, JRadioButton astar,
            JRadioButton jps, JRadioButton dijkstra) {
        this.ratkaisija = ratkaisija;
        this.kartta = kartta;
        this.astar = astar;
        this.jps = jps;
        this.dijkstra = dijkstra;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (jps.isSelected()){
            ratkaisija.setJps();
        } else if (astar.isSelected()){
            ratkaisija.setAstar();
        } else if (dijkstra.isSelected()){
            ratkaisija.setDijkstra();
        }
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
