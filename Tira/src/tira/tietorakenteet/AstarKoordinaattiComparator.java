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
     * Palauttaa 1 jos k1 > k2, -1 jos k1 < k2, 0 jos k1==k2
     * @param k1 ensimmainen koordinaatti
     * @param k2 toinen koordinaatti
     * @return
     */
    @Override
    public int compare(Koordinaatti k1, Koordinaatti k2) {
        double o1Etaisyys = k1.getAlkuun() + k1.getMaaliin();
        double o2Etaisyys = k2.getAlkuun() + k2.getMaaliin();
        if (o1Etaisyys - o2Etaisyys > 0.006) {
            return 1;
        }
        if (o1Etaisyys - o2Etaisyys < 0.006) {
            return -1;
        }
        return 0;
    }
}
