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
     * @param kartta
     */
    public Astar() {
        super();
    }

    /**
     * Apumetodi findPath:lle, joka lisää kaikki käsiteltävänä olevan solmun
     * naapurit kekoon ja kutsuu asetaNaapurinEtaisyydet-metodia.
     *
     * @param kasiteltava
     * @param keko
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
        if (naapurinX >= super.getKartta().getLeveys() || naapurinY >= super.getKartta().getKorkeus()
                || naapurinX < 0 || naapurinY < 0) {
            return;
        }

        Koordinaatti naapuri = super.getKartta().getKoordinaatti(naapurinY, naapurinX);
        if (!super.onkoSeina(naapuri)) {
            asetaEtaisyysAlkuun(naapuri, kasiteltava);
            asetaEtaisyysMaaliin(naapuri);
            if (!naapuri.getKayty()) {
                super.getKeko().add(naapuri);
            }
        }

    }

    /**
     * Laskee parametrina annetun koordinaatin etäisyyden maalisolmuun
     */
    private void asetaEtaisyysMaaliin(Koordinaatti naapuri) {
        Koordinaatti maali = super.getMaali();

        int suurempi = Math.max(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        int pienempi = Math.min(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        double diagonaali = Math.sqrt(2) * (suurempi - pienempi);
        double etaisyys = pienempi + diagonaali;
        naapuri.setMaaliin(etaisyys);
    }
    /*
     * Asettaa annetun koordinaatin etäisyyden lähtösolmuun.
     */

    private void asetaEtaisyysAlkuun(Koordinaatti naapuri,
            Koordinaatti kasiteltava) {
        double matkaAlkuun = kasiteltava.getAlkuun();
        if (naapuri.getX() == kasiteltava.getX()
                || naapuri.getY() == kasiteltava.getY()) {
            matkaAlkuun += 1;
        } else {
            matkaAlkuun += Math.sqrt(2);
        }

        if (naapuri.getAlkuun() > matkaAlkuun) {
            naapuri.setAlkuun(matkaAlkuun);
            naapuri.setEdellinen(kasiteltava);
        }
    }

    /**
     * Asettaa kartan kaikkien solmujen etäisyyden lähtöön
     * äärettömäksi(Integer.MAX_VALUE) Lähtösolmun etäisyys itseensä = 0 Laskee
     * jokaisen solmun suoran etäisyyden maalisolmusta
     *
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
