/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.logiikka;

import java.awt.image.BufferedImage;
import java.io.File;
import tira.algoritmit.Astar;
import tira.algoritmit.Hakualgoritmi;
import tira.gui.Karttaruutu;
import tira.tietorakenteet.Reitti;

/**
 *
 * @author Ari
 */
public class Solver implements Runnable{

    private Kartta kartta;
    private Hakualgoritmi algo;
    private Reitti reitti;
    private Karttaruutu view;

    public Solver()  {   
        algo = new Astar();
        this.kartta = new Kartta(new File("testi.png"));
       
    }

    public Kartta getKartta() {
        return kartta;

    }

    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
        algo = new Astar();
    }

    public int getKartanKorkeus() {
        return kartta.getKorkeus();
    }

    public int getKartanLeveys() {
        return kartta.getLeveys();
    }

    public void setAlgoritmi(Hakualgoritmi algo) {
        this.algo = algo;
    }

    public Hakualgoritmi getAlgoritmi() {
        return this.algo;
    }

    public void ratkaise() {
        reitti = algo.findPath(kartta);
    }

    public void luoKartta(char[][] taulukko) {
        this.kartta = new Kartta(taulukko);

    }

    public boolean onMaali(int y, int x) {
        return kartta.getKoordinaatti(y, x) == kartta.getMaali();
    }

    public boolean onLahto(int y, int x) {
        return kartta.getKoordinaatti(y, x) == kartta.getLahto();
    }

    public boolean onSeina(int y, int x) {
        return kartta.onSeina(y, x);
    }
    
    public boolean onKeossa(int y, int x){
        return kartta.getKoordinaatti(y, x).getKeossa();
    }
    
     public boolean onKayty(int y, int x){
        return kartta.getKoordinaatti(y, x).getKayty();
    }

    public int[] getSeuraava() {
        Koordinaatti seuraava = reitti.getNext();
        if (seuraava == null) {
            return null;
        }
        int[] seuraavanKoordinaatit = {seuraava.getY(), seuraava.getX()};
        return seuraavanKoordinaatit;
    }

    public int getReitinPituus() {
        return reitti.length();
    }

    public BufferedImage getKarttaKuva() {
        return kartta.getKuva();
    }

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

    public long getSuoritusaika() {

        return this.algo.suoritusaika();
    }

    @Override
    public void run() {
         view = new Karttaruutu(this);
    }
}
