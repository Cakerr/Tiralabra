/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actionlisteners;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import tira.logiikka.Solver;

/**
 *
 * @author Ari
 */
public class KartanLatausListener implements MouseListener {

    private Solver ohjain;

    /**
     * 
     * @param ratkaisija
     */
    public KartanLatausListener(Solver ratkaisija) {
        this.ohjain = ratkaisija;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter(null, "png"));
        int valinta = chooser.showOpenDialog(new Frame());
        if (valinta == JFileChooser.APPROVE_OPTION) {
            File valittu = chooser.getSelectedFile();
            ohjain.lataaUusikartta(valittu);
        } 
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
