package tira.algoritmit;

import tira.logiikka.Koordinaatti;
import tira.tietorakenteet.Reitti;

/**
 * 
 * @author Ari
 */
public class Dijkstra extends Hakualgoritmi {

    /**
     * 
     * @param maali
     * @param reitti
     */
    @Override
    protected void tulostaReitti(Koordinaatti maali, Reitti reitti) {
        if (maali.getEdellinen() != null) {
            tulostaReitti(maali.getEdellinen(), reitti);
        }
        reitti.lisaaNode(maali);
    }

    /**
     * 
     * @param kasiteltava
     */
    @Override
    protected void lisaaSeuraajatKekoon(Koordinaatti kasiteltava) {

        asetaKekoon(kasiteltava.getY() + 1, kasiteltava.getX() + 1, kasiteltava);
        asetaKekoon(kasiteltava.getY() - 1, kasiteltava.getX() - 1, kasiteltava);
        asetaKekoon(kasiteltava.getY() - 1, kasiteltava.getX() + 1, kasiteltava);
        asetaKekoon(kasiteltava.getY() + 1, kasiteltava.getX() - 1, kasiteltava);
        asetaKekoon(kasiteltava.getY() - 1, kasiteltava.getX(), kasiteltava);
        asetaKekoon(kasiteltava.getY() + 1, kasiteltava.getX(), kasiteltava);
        asetaKekoon(kasiteltava.getY(), kasiteltava.getX() - 1, kasiteltava);
        asetaKekoon(kasiteltava.getY(), kasiteltava.getX() + 1, kasiteltava);



    }

    private void asetaKekoon(int y, int x, Koordinaatti kasiteltava) {
        if (!voikoKulkea(y, x)) {
            return;
        }
        Koordinaatti naapuri = getKoordinaatti(y, x);
        if (naapuri.getKeossa()) {
            return;
        }
        naapuri.setKeossa(true);

        asetaEtaisyysAlkuun(naapuri, kasiteltava);
        naapuri.setMaaliin(0);
        
        getKeko().add(naapuri);
    }
}
