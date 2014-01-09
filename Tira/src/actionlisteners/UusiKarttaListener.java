/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import tira.logiikka.Solver;

/**
 *
 * @author Ari
 */
public class UusiKarttaListener extends JPanel implements MouseListener  {

    private Solver ohjain;

    public UusiKarttaListener(Solver ohjain) {
        this.ohjain = ohjain;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
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
