/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.Comparator;

/**
 *Comparator Astaria käytettäessä, jonka avulla priorityQueue/keko
 * vertailee kahta Koordinaattia keskenänsä.
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
        int o1Etaisyys = k1.getAlkuun()+k1.getMaaliin();
        int o2Etaisyys = k2.getAlkuun()+k2.getMaaliin();
        return o1Etaisyys-o2Etaisyys;
    }

    
}
