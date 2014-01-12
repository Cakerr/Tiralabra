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
     *
     * @return
     */
    protected Kartta getKartta() {
        return this.kartta;
    }

    /**
     *
     * @return
     */
    protected Keko getKeko() {
        return this.keko;
    }

    /**
     *
     * @return
     */
    protected Koordinaatti getMaali() {
        return maali;
    }

    /**
     *
     * @return
     */
    protected Koordinaatti getLahto() {
        return lahto;
    }

    /**
     *
     * @param kartta
     */
    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
    }

    /**
     *
     * @param kartta
     * @return
     */
    public Reitti findPath(Kartta kartta) {
        timer = new Timer();
        this.kartta = kartta;
        this.lahto = kartta.getLahto();
        this.maali = kartta.getMaali();
        this.timer.start();
        //Keko keko = new Keko(new AstarKoordinaattiComparator());
        keko = new Keko(new AstarKoordinaattiComparator());
        Reitti reitti = new Reitti();
        relaxAll();
        keko.add(lahto);

        while (!maali.getKayty() && !keko.isEmpty()) {
            Koordinaatti kasiteltava = keko.poll();
//            if (kasiteltava.getKayty() || kasiteltava.getKeossa()) {
//                continue;
//            }
            //  System.out.println("iteraation n");
            lisaaSeuraajatKekoon(kasiteltava);
            kasiteltava.setKayty(true);
            if (maali.getKayty()) {
                this.timer.stop();
                tulostaReitti(maali, reitti);
                break;
            }
            
        }


        return reitti;
    }

    /**
     *
     * @return
     */
    public long suoritusaika() {
        return timer.time();
    }

    public void relaxAll() {
        for (int i = 0; i < kartta.getKorkeus(); i++) {
            for (int j = 0; j < kartta.getLeveys(); j++) {
                kartta.getKoordinaatti(i, j).setAlkuun(Integer.MAX_VALUE);
                asetaEtaisyysMaaliin(getKoordinaatti(i, j));
                kartta.getKoordinaatti(i, j).setKayty(false);
                kartta.getKoordinaatti(i, j).setKeossa(false);
                getKoordinaatti(i, j).setEdellinen(null);
            }
        }
        lahto.setAlkuun(0);
    }

    /**
     *
     * @param node
     * @return
     */
    protected boolean onkoSeina(Koordinaatti node) {
        return node.getMerkki() == '#';
    }

    /**
     *
     * @param y
     * @param x
     * @return
     */
    protected boolean onkoSeina(int y, int x) {
        return kartta.onSeina(y, x);
    }

    /**
     *
     * @param y
     * @param x
     * @return
     */
    protected boolean onkoMaali(int y, int x) {
        return kartta.onkoMaali(y, x);
    }

    /**
     *
     * @param maali
     * @param reitti
     */
    protected abstract void tulostaReitti(Koordinaatti maali, Reitti reitti);

    /**
     *
     * @param kasiteltava
     */
    protected abstract void lisaaSeuraajatKekoon(Koordinaatti kasiteltava);

    /**
     *
     * @param y
     * @param x
     * @return
     */
    protected Koordinaatti getKoordinaatti(int y, int x) {
        return kartta.getKoordinaatti(y, x);
    }

    /**
     *
     * @param y
     * @param x
     * @return
     */
    public boolean voikoKulkea(int y, int x) {
        return kartta.voikoKulkea(y, x);
    }

    /**
     *
     * @param naapuri
     */
    protected void asetaEtaisyysMaaliin(Koordinaatti naapuri) {
//        double vaaka = Math.pow(Math.abs(naapuri.getX() - maali.getX()), 2);
//        double pysty = Math.pow(Math.abs(naapuri.getY() - maali.getY()), 2);
//        
//        double etaisyys = Math.sqrt(vaaka+pysty);
//       
        
        double suurempi = Math.max(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        double pienempi = Math.min(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        double diagonaali = 1.41 * pienempi;

        double etaisyys = suurempi - pienempi + diagonaali;

        naapuri.setMaaliin(etaisyys);

    }
    /*
     * Asettaa annetun koordinaatin etäisyyden lähtösolmuun.
     */

    protected void asetaEtaisyysAlkuun(Koordinaatti naapuri,
            Koordinaatti kasiteltava) {
        int suurempi = Math.max(Math.abs(naapuri.getX() - kasiteltava.getX()),
                Math.abs(naapuri.getY() - kasiteltava.getY()));
        double matkaAlkuun = kasiteltava.getAlkuun();
        if (naapuri.getX() == kasiteltava.getX()
                || naapuri.getY() == kasiteltava.getY()) {
            matkaAlkuun += 1 * suurempi;
        } else {
            matkaAlkuun += Math.sqrt(2) * suurempi;
        }

        if (naapuri.getAlkuun() > matkaAlkuun) {
            naapuri.setAlkuun(matkaAlkuun);
            naapuri.setEdellinen(kasiteltava);
        }
    }
}
