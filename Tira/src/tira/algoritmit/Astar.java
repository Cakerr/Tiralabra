package tira.algoritmit;

import tira.logiikka.Koordinaatti;
import tira.tietorakenteet.Reitti;

/**
 *
 * @author Ari
 */
public class Astar extends Hakualgoritmi {

    /**
     * Luokan konstruktori
     *
     */
    public Astar() {
        super();
    }

    /**
     * Apumetodi findPath:lle, joka lisää kaikki käsiteltävänä olevan solmun
     * naapurit kekoon ja kutsuu asetaNaapurinEtaisyydet-metodia.
     *
     * @param kasiteltava
     */
    @Override
    protected void lisaaSeuraajatKekoon(Koordinaatti kasiteltava) {

        
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX(), kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX(), kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY(), kasiteltava.getX() - 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY(), kasiteltava.getX() + 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX() + 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX() - 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX() + 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX() - 1, kasiteltava);


    }
    /*
     * Apumetodi, jonka avulla karsitaan epäkelvot koordinaatit, jonka jälkeen
     * kutsutaan metodeja, jotka todellisuudessa asettavat etäisyyden maaliin ja
     * lähtösolmuun.
     *
     */

    private void asetaNaapurinEtaisyydet(int naapurinY, int naapurinX,
            Koordinaatti kasiteltava) {
        if (!voikoKulkea(naapurinY, naapurinX) || getKoordinaatti(naapurinY, naapurinX).getKayty()) {
            return;
        }
        Koordinaatti naapuri = getKoordinaatti(naapurinY, naapurinX);  
        asetaEtaisyysAlkuun(naapuri, kasiteltava);
        asetaEtaisyysMaaliin(naapuri);
        
       

    }

    /**
     * Tulostaa reitin oikein pain reitti oliion
     *
     * @param maali maalisolmu
     * @param reitti
     */
    @Override
    protected void tulostaReitti(Koordinaatti node, Reitti reitti) {
        if (node.getEdellinen() != null) {
            tulostaReitti(node.getEdellinen(), reitti);
        }
        reitti.lisaaNode(node);
    }
}
