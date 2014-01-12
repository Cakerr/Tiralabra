package tira.algoritmit;

import tira.logiikka.Koordinaatti;
import tira.tietorakenteet.Reitti;

/**
 *
 * @author Ari
 */
public class Astar extends Hakualgoritmi {

    /**
     * Luokan konstruktori, saa parametrinaan luokan käytämän kartan
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

        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX() + 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX() - 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX() + 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX() - 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX(), kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX(), kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY(), kasiteltava.getX() - 1, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY(), kasiteltava.getX() + 1, kasiteltava);



    }
    /*
     * Apumetodi, jonka avulla karsitaan epäkelvot koordinaatit, jonka jälkeen
     * kutsutaan metodeja, jotka todellisuudessa asettavat etäisyyden maaliin ja
     * lähtösolmuun.
     *
     */

    private void asetaNaapurinEtaisyydet(int naapurinY, int naapurinX,
            Koordinaatti kasiteltava) {
        if (!voikoKulkea(naapurinY, naapurinX)) {
            return;
        }
        Koordinaatti naapuri = getKoordinaatti(naapurinY, naapurinX);
        asetaEtaisyysAlkuun(naapuri, kasiteltava);
        if (naapuri.getKeossa()) {
            return;
        }    
        naapuri.setKeossa(true);

        getKeko().add(naapuri);
    }

    /**
     * Laskee parametrina annetun koordinaatin etäisyyden maalisolmuun
     */
    /**
     * Asettaa kartan kaikkien solmujen etäisyyden lähtöön
     * äärettömäksi(Integer.MAX_VALUE) Lähtösolmun etäisyys itseensä = 0 Laskee
     * jokaisen solmun suoran etäisyyden maalisolmusta
     *
     * @param node
     * @param reitti
     */
    /*
     * Lisää löydetyn reitin koordinaatti-oliot parametrina saatuun reittiin.
     */
    @Override
    protected void tulostaReitti(Koordinaatti node, Reitti reitti) {
        if (node.getEdellinen() != null) {
            tulostaReitti(node.getEdellinen(), reitti);
        }
        reitti.lisaaNode(node);
        //System.out.println("Y: " + node.getY() + " X: " + node.getX());
    }

    /*
     * tarkistaa onko parametrina saadussa koordinaatissa seinä ('#').
     */
}
