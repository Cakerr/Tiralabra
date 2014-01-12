/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.logiikka;

import java.awt.image.BufferedImage;
import java.io.File;
import tira.algoritmit.Astar;
import tira.algoritmit.Dijkstra;
import tira.algoritmit.Hakualgoritmi;
import tira.algoritmit.JumpPointSearch;
import tira.gui.Karttaruutu;
import tira.tietorakenteet.Reitti;

/**
 *
 * @author Ari
 */
public class Solver implements Runnable {

    private Kartta kartta;
    private Hakualgoritmi algo;
    private Reitti reitti;
    private Karttaruutu view;

    /**
     *Konstruktori, joka asettaa oletuksena käytetyn kartan
     */
    public Solver() {

        char[][] charKartta = {
            {'S', ' ', ' ', ' '},
            {' ', ' ', '#', ' '},
            {' ', ' ', '#', ' '},
            {' ', ' ', '#', 'M'}};
        this.kartta = new Kartta(new File("plain.png"));

    }

    /**
     *Palauttaa käytössä olevan kartan
     * @return
     */
    public Kartta getKartta() {
        return kartta;

    }

    /**
     *Asettaa käytetyksi algoritmiks A*:n
     */
    public void setAstar() {
        algo = new Astar();
    }

    /**
     *Asettaa käytettäväksi algoritmiksi Jump Point Searchin
     */
    public void setJps() {
        algo = new JumpPointSearch();
    }

    /**
     *Asettaa uuden kartan
     * @param kartta haluttu kartta
     */
    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
        algo = new Astar();
    }

    /**
     *Palauttaa kartan korkeuden
     * @return kartan korkeus
     */
    public int getKartanKorkeus() {
        return kartta.getKorkeus();
    }

    /**
     *Palauttaa kartan leveyden
     * @return kartan leveys
     */
    public int getKartanLeveys() {
        return kartta.getLeveys();
    }

    /**
     *Asettaa käytetttävksi algoritmiksi parametrina saadun algoritmin
     * @param algo haluttu hakualgoritmi
     */
    public void setAlgoritmi(Hakualgoritmi algo) {
        this.algo = algo;
    }

    /**
     *Palauttaa käytössä olevan hakualgoritmin
     * @return hakualgoritmi
     */
    public Hakualgoritmi getAlgoritmi() {
        return this.algo;
    }

    /**
     *Ratkaisee valitun karta, tällä hetkellä käytössä olevalla algoritmilla
     */
    public void ratkaise() {
        reitti = algo.findPath(kartta);
    }

    /**
     *Luo uuden kartan parametrina saadusta char taulukosta
     * @param taulukko char-taulukko josta kartta luodaan
     */
    public void luoKartta(char[][] taulukko) {
        this.kartta = new Kartta(taulukko);

    }

    /**
     *Palauttaa tiedon siitä, onko (y,x) maalisolmu
     * @param y y--akselin arvo
     * @param x x-akselin arvo
     * @return true jos (y,x) on maali, muuten false
     */
    public boolean onMaali(int y, int x) {
        return kartta.getKoordinaatti(y, x) == kartta.getMaali();
    }

    /**
     *Palauttaa tiedon siitä, onko (y,x) lähtösolmu
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return true, jos (y,x) on lähtö, muuten false
     */
    public boolean onLahto(int y, int x) {
        return kartta.getKoordinaatti(y, x) == kartta.getLahto();
    }

    /**
     *Palauttaa tiedon siitä, onko (y,x) seinä
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return true jos (y,x) on seinä
     */
    public boolean onSeina(int y, int x) {
        return kartta.onSeina(y, x);
    }

    /**
     *Plaauttaa tiedon siitä, onko (y,x) laitettu jo kekoon
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return true, jos 8y,x) on laitettu jo kekoon
     */
    public boolean onKeossa(int y, int x) {
        return kartta.getKoordinaatti(y, x).getKeossa();
    }

    /**
     *Palauttaa tiedon siitä onko koordinaatissa (y,x) käyty
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return true jos koordinaatissa on käyty
     */
    public boolean onKayty(int y, int x) {
        return kartta.getKoordinaatti(y, x).getKayty();
    }

    /**
     *Palauttaa reittissä olevan seuraavan koordinaatin
     * @return seuraava koordinaatti
     */
    public int[] getSeuraava() {
        Koordinaatti seuraava = reitti.getNext();
        if (seuraava == null) {
            return null;
        }
        int[] seuraavanKoordinaatit = {seuraava.getY(), seuraava.getX()};
        return seuraavanKoordinaatit;
    }

    /**
     *Palauttaa löydetyn reitin pituuden
     * @return reitin pituus
     */
    public int getReitinPituus() {
        return reitti.length();
    }

    /**
     *Plauttaa karttana käytetyn kuvan
     * @return kuva
     */
    public BufferedImage getKarttaKuva() {
        return kartta.getKuva();
    }

    /**
     *Lataa uuden kartan tiedostoista
     * @param tiedosto kuva josta kartta muodostetaan
     * @return true jos kuvasta onnistuttiin muodostamaan validi kartta
     */
    public boolean lataaUusikartta(File tiedosto) {
        Kartta uusiKartta = new Kartta(tiedosto);
        if (uusiKartta.onkoValidi()) {
            this.kartta = uusiKartta;
            this.reitti = null;
            view.piirraKartta();
            return true;
        }
        return false;
    }

    /**
     *Palauttaa reitin löytämiseen käytetty aika
     * @return reitin löytämiseen käytetty aika
     */
    public long getSuoritusaika() {

        return this.algo.suoritusaika();
    }

    @Override
    public void run() {
        view = new Karttaruutu(this);
    }

    /**
     *Palauttaa löydetyn reitin askelkustannuksen
     * @return askeleet
     */
    public double getAskeleetMaaliin() {
        return kartta.getMaali().getAlkuun();
    }

    /**
     *Asettaa käytettäväksi algoritmiksi Dijkstran
     */
    public void setDijkstra() {
        this.algo = new Dijkstra();
    }
}
