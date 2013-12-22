/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.Comparator;

/**
 *
 * @author Ari
 */
public class AstarKoordinaattiComparator implements Comparator<Koordinaatti> {

    @Override
    public int compare(Koordinaatti o1, Koordinaatti o2) {
        int o1Etaisyys = o1.getAlkuun()+o1.getMaaliin();
        int o2Etaisyys = o2.getAlkuun()+o2.getMaaliin();
        return o1Etaisyys-o2Etaisyys;
    }

    
}
