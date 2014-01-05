package logiikka;

import java.util.PriorityQueue;

/**
 *
 * @author Ari
 */
public class Astar {

    private Kartta kartta;
    private Koordinaatti lahto;
    private Koordinaatti maali;

    /**
     * Luokan konstruktori, saa parametrinaan luokan käytämän kartan
     *
     * @param kartta
     */
    public Astar(Kartta kartta) {
        this.kartta = kartta;
        this.lahto = kartta.getLahto();
        this.maali = kartta.getMaali();
    }

    /**
     * Etsii kartasta lyhimmän reitin lahto-solmusta maali-solmuun, ja palauttaa
     * sen Reitti-oliona.
     *
     * @return
     */
    public Reitti findPath() {
        Keko keko = new Keko(new AstarKoordinaattiComparator());
        Reitti reitti = new Reitti();
        relaxAll();
        keko.insert(lahto);

        while (!maali.getKayty() && !keko.isEmpty()) {
            Koordinaatti kasiteltava = keko.poll();
            if (kasiteltava.getKayty()){
                continue;
            }
            kasiteltava.setKayty(true);

            lisaaNaapuritKekoon(kasiteltava, keko);
        }
        if (maali.getKayty()) {

            tulostaReitti(maali, reitti);
        }

        return reitti;
    }

    /**
     * Apumetodi findPath:lle, joka lisää kaikki käsiteltävänä olevan solmun
     * naapurit kekoon ja kutsuu asetaNaapurinEtaisyydet-metodia.
     *
     * @param kasiteltava
     * @param keko
     */
    public void lisaaNaapuritKekoon(Koordinaatti kasiteltava,
            Keko keko) {

        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX(), keko, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX(), keko, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY(), kasiteltava.getX() + 1, keko, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY(), kasiteltava.getX() - 1, keko, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX() - 1, keko, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX() + 1, keko, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX() - 1, keko, kasiteltava);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX() + 1, keko, kasiteltava);

    }
    /*
     * Apumetodi, jonka avulla karsitaan epäkelvot koordinaatit, jonka jälkeen
     * kutsutaan metodeja, jotka todellisuudessa asettavat etäisyyden maaliin ja
     * lähtösolmuun.
     *
     */

    private void asetaNaapurinEtaisyydet(int naapurinY, int naapurinX,
            Keko keko, Koordinaatti kasiteltava) {
        if (naapurinX >= kartta.getLeveys() || naapurinY >= kartta.getKorkeus()
                || naapurinX < 0 || naapurinY < 0) {
            return;
        }

        Koordinaatti naapuri = kartta.getKoordinaatti(naapurinY, naapurinX);
        if (!onkoSeina(naapuri)) {
            asetaEtaisyysAlkuun(naapuri, kasiteltava);
            asetaEtaisyysMaaliin(naapuri);
            if (!naapuri.getKayty()) {
                keko.insert(naapuri);
            }
        }

    }

    /**
     * Laskee parametrina annetun koordinaatin etäisyyden maalisolmuun
     */
    private void asetaEtaisyysMaaliin(Koordinaatti naapuri) {
        int suurempi = Math.max(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        int pienempi = Math.min(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        int etaisyys = pienempi + (suurempi - pienempi);
        naapuri.setMaaliin(etaisyys);
    }
    /*
     * Asettaa annetun koordinaatin etäisyyden lähtösolmuun.
     */

    private void asetaEtaisyysAlkuun(Koordinaatti naapuri,
            Koordinaatti kasiteltava) {
        int matkaAlkuun = kasiteltava.getAlkuun() + 1;
        if (naapuri.getAlkuun() > matkaAlkuun) { // oletuksena vielä tässä vaiheessa, että kaikki siirtymät "maksaa yhden".
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
    public void relaxAll() {
        for (int i = 0; i < kartta.getKorkeus(); i++) {
            for (int j = 0; j < kartta.getLeveys(); j++) {
                kartta.getKoordinaatti(i, j).setAlkuun(Integer.MAX_VALUE);
                kartta.getKoordinaatti(i, j).setKayty(false);
            }
        }
        lahto.setAlkuun(0);
    }
    /*
     * Lisää löydetyn reitin koordinaatti-oliot parametrina saatuun reittiin.
     */

    private void tulostaReitti(Koordinaatti node, Reitti reitti) {
        if (node.getEdellinen() != null) {
            tulostaReitti(node.getEdellinen(), reitti);
        }
        reitti.lisaaNode(node);
        //System.out.println("Y: " + node.getY() + " X: " + node.getX());
    }
   
    /*
     * tarkistaa onko parametrina saadussa koordinaatissa seinä ('#').
     */
    private boolean onkoSeina(Koordinaatti node) {
        return node.getMerkki() == '#';
    }
}
