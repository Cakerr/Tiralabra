/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.tietorakenteet;

import java.util.Comparator;
import tira.logiikka.Koordinaatti;

/**
 * Comparator Astaria käytettäessä, jonka avulla priorityQueue/keko vertailee
 * kahta Koordinaattia keskenänsä.
 *
 * @author Ari
 */
public class AstarKoordinaattiComparator implements Comparator<Koordinaatti> {

    /**
     *
     * @param k1
     * @param k2
     * @return
     */
    @Override
    public int compare(Koordinaatti k1, Koordinaatti k2) {
        double o1Etaisyys = k1.getAlkuun() + k1.getMaaliin();
        double o2Etaisyys = k2.getAlkuun() + k2.getMaaliin();

        if (o1Etaisyys - o2Etaisyys > 0) {
            return 1;
        } else if (o1Etaisyys - o2Etaisyys < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
