package tira.algoritmit;

import tira.logiikka.Kartta;
import tira.logiikka.Koordinaatti;
import tira.tietorakenteet.AstarKoordinaattiComparator;
import tira.tietorakenteet.Keko;
import tira.tietorakenteet.Reitti;

/**
 *
 * @author Ari
 */
public abstract class Hakualgoritmi {

    private Kartta kartta;
    private Koordinaatti lahto;
    private Koordinaatti maali;
    private Timer timer;
    private Keko keko;

    /**
     *
     */
    public Hakualgoritmi() {
    }

    /**
     * Getteri algoritmin käyttämälle kartalle
     *
     * @return Palauttaa algoritmin käyttämän kartan
     */
    protected Kartta getKartta() {
        return this.kartta;
    }

    /**
     * Palauttaa keon
     *
     * @return algoritmin käyttämä keko
     */
    protected Keko getKeko() {
        return this.keko;
    }

    /**
     *
     * @return palauttaa maalisolmun
     */
    protected Koordinaatti getMaali() {
        return maali;
    }

    /**
     *
     * @return Palauttaa lahtosolmun
     */
    protected Koordinaatti getLahto() {
        return lahto;
    }

    /**
     *
     * @param kartta kartta joka asetetaan luokkamuuttujaksi
     */
    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
    }

    /**
     * Etsii reitin kartalla merkitys lahto- ja maalisolmujen valilla
     *
     * @param kartta kartta jolta rietti halutaan löytää
     * @return reitti-olio, jossa alkio ovat lahto -> maali järjestyksessä
     */
    public Reitti findPath(Kartta kartta) {
        timer = new Timer();
        this.kartta = kartta;
        this.lahto = kartta.getLahto();
        this.maali = kartta.getMaali();
        this.timer.start();
        keko = new Keko(new AstarKoordinaattiComparator());
        Reitti reitti = new Reitti();
        relaxAll();
        keko.add(lahto);

        while (!maali.getKayty() && !keko.isEmpty()) {
            Koordinaatti kasiteltava = keko.poll();
            kasiteltava.setKayty(true);
            lisaaSeuraajatKekoon(kasiteltava);
            if (maali.getKayty()) {
                this.timer.stop();
                tulostaReitti(maali, reitti);
                break;
            }

        }


        return reitti;
    }

    /**
     * Palauttaa algoritmin käyttämän suoritusajan
     *
     * @return aika millisekuntteina
     */
    public long suoritusaika() {
        return timer.time();
    }

    /**
     *
     */
    public void relaxAll() {
        for (int i = 0; i < kartta.getKorkeus(); i++) {
            for (int j = 0; j < kartta.getLeveys(); j++) {
                kartta.getKoordinaatti(i, j).setAlkuun(Integer.MAX_VALUE);
                kartta.getKoordinaatti(i, j).setKayty(false);
                kartta.getKoordinaatti(i, j).setKeossa(false);
                getKoordinaatti(i, j).setEdellinen(null);
            }
        }
        lahto.setAlkuun(0);
    }

    /**
     * Palauttaa true, jos nodessa on seinä, muuten false
     *
     * @param node node josta halutaan selvittää onko se seinä
     * @return boolea-arvo, onko seinä vai ei
     */
    protected boolean onkoSeina(Koordinaatti node) {
        return node.getMerkki() == '#';
    }

    /**
     * Palauttaa true, jos nodessa on seinä, muuten false
     *
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return boolea-arvo, onko seinä vai ei
     */
    protected boolean onkoSeina(int y, int x) {
        return kartta.onSeina(y, x);
    }

    /**
     * Palauttaa true jos koordinaatissa (y,x) on maalisolmu
     *
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return palauttaa true, jos koordinaatti (y,x) on maali
     */
    protected boolean onkoMaali(int y, int x) {
        return kartta.onkoMaali(y, x);
    }

    /**
     * Tulostaa reitin oikein pain reitti oliion
     *
     * @param maali maalisolmu
     * @param reitti
     */
    protected abstract void tulostaReitti(Koordinaatti maali, Reitti reitti);

    /**
     * Lisaa kasiteltavana olevan noden seuraajat kekoon
     *
     * @param kasiteltava kasittelyssa oleva koordinaatti
     */
    protected abstract void lisaaSeuraajatKekoon(Koordinaatti kasiteltava);

    /**
     * Palauttaa (y,x) koordinaatin
     *
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return Koordinaatti paikassa (y,x)
     */
    protected Koordinaatti getKoordinaatti(int y, int x) {
        return kartta.getKoordinaatti(y, x);
    }

    /**
     * Palauttaa true jos pisteessä (y,x) ei ole seinää, ja se on kartan sisällä
     *
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return true jos voi kulkea, muuten false
     */
    public boolean voikoKulkea(int y, int x) {
        return kartta.voikoKulkea(y, x);
    }

    /**
     * Asettaa parametrina saatuun koordinaattiin suoran etäisyyden maaliin
     *
     * @param naapuri node, jonka etäisyys maaliin halutaan asettaa
     */
    protected void asetaEtaisyysMaaliin(Koordinaatti naapuri) {

        double suurempi = Math.max(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        double pienempi = Math.min(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        double diagonaali = 1.41 * pienempi;

        double etaisyys = suurempi - pienempi + diagonaali;

        naapuri.setMaaliin(etaisyys);


    }

    /**
     * Asettaa annetun koordinaatin etäisyyden lähtösolmuun.
     *
     * @param naapuri koordinaatti jonka etäisyys halutaan asettaa
     * @param kasiteltava naapuri edeltaja
     */
    protected void asetaEtaisyysAlkuun(Koordinaatti naapuri,
            Koordinaatti kasiteltava) {
        double suurempi = Math.max(Math.abs(naapuri.getX() - kasiteltava.getX()),
                Math.abs(naapuri.getY() - kasiteltava.getY()));
        double matkaAlkuun = kasiteltava.getAlkuun();
        if (naapuri.getX() == kasiteltava.getX()
                || naapuri.getY() == kasiteltava.getY()) {
            matkaAlkuun += 1.0 * suurempi;
        } else {
            matkaAlkuun += 1.41 * suurempi;
        }

        if (naapuri.getAlkuun() > matkaAlkuun) {
            if (naapuri.getKeossa()) {
                getKeko().delKoordinaatti(naapuri);
            }
            naapuri.setAlkuun(matkaAlkuun);
            naapuri.setEdellinen(kasiteltava);
            getKeko().add(naapuri);
            naapuri.setKeossa(true);
        }
    }
}
