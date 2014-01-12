/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.logiikka;

import java.awt.image.BufferedImage;
import java.io.File;
import tira.algoritmit.Astar;
import tira.algoritmit.Dijkstra;
//import tira.algoritmit.Dijkstra;
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
     * 
     */
    public Solver() {

        this.kartta = new Kartta(new File("testi2.png"));

    }

    /**
     * 
     * @return
     */
    public Kartta getKartta() {
        return kartta;

    }

    /**
     * 
     */
    public void setAstar() {
        algo = new Astar();
    }

    /**
     * 
     */
    public void setJps() {
        algo = new JumpPointSearch();
    }

    /**
     * 
     * @param kartta
     */
    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
        algo = new Astar();
    }

    /**
     * 
     * @return
     */
    public int getKartanKorkeus() {
        return kartta.getKorkeus();
    }

    /**
     * 
     * @return
     */
    public int getKartanLeveys() {
        return kartta.getLeveys();
    }

    /**
     * 
     * @param algo
     */
    public void setAlgoritmi(Hakualgoritmi algo) {
        this.algo = algo;
    }

    /**
     * 
     * @return
     */
    public Hakualgoritmi getAlgoritmi() {
        return this.algo;
    }

    /**
     * 
     */
    public void ratkaise() {
        reitti = algo.findPath(kartta);
    }

    /**
     * 
     * @param taulukko
     */
    public void luoKartta(char[][] taulukko) {
        this.kartta = new Kartta(taulukko);

    }

    /**
     * 
     * @param y
     * @param x
     * @return
     */
    public boolean onMaali(int y, int x) {
        return kartta.getKoordinaatti(y, x) == kartta.getMaali();
    }

    /**
     * 
     * @param y
     * @param x
     * @return
     */
    public boolean onLahto(int y, int x) {
        return kartta.getKoordinaatti(y, x) == kartta.getLahto();
    }

    /**
     * 
     * @param y
     * @param x
     * @return
     */
    public boolean onSeina(int y, int x) {
        return kartta.onSeina(y, x);
    }

    /**
     * 
     * @param y
     * @param x
     * @return
     */
    public boolean onKeossa(int y, int x) {
        return kartta.getKoordinaatti(y, x).getKeossa();
    }

    /**
     * 
     * @param y
     * @param x
     * @return
     */
    public boolean onKayty(int y, int x) {
        return kartta.getKoordinaatti(y, x).getKayty();
    }

    /**
     * 
     * @return
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
     * 
     * @return
     */
    public int getReitinPituus() {
        return reitti.length();
    }

    /**
     * 
     * @return
     */
    public BufferedImage getKarttaKuva() {
        return kartta.getKuva();
    }

    /**
     * 
     * @param tiedosto
     * @return
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
     * 
     * @return
     */
    public long getSuoritusaika() {

        return this.algo.suoritusaika();
    }

    @Override
    public void run() {
        view = new Karttaruutu(this);
    }
    
    /**
     * 
     * @return
     */
    public double getAskeleetMaaliin(){
        return  kartta.getMaali().getAlkuun();
    }

    /**
     * 
     */
    public void setDijkstra() {
        this.algo = new Dijkstra();
    }
}
