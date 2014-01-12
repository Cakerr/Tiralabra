/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.logiikka;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ari
 */
public class Kartta {

    private Koordinaatti[][] kartta;
    private BufferedImage kuva;
    private char[][] charKartta;
    private final int vihrea = -14503604;
    private final int musta = -16777216;
    private final int punainen = -1237980;
    private Koordinaatti lahto;
    private Koordinaatti maali;
    private int korkeus;
    private int leveys;

    /**
     * Konstruktori joka saa parametrina tiedoston nimen, josta kuva yritetään
     * hakea. Jos tiedosto löytyy niin alustetaan kartta kuvan kokoiseksi,
     * alustetaan luokkamuuttujat leveys ja korkeus ja kutsutaan metodia
     * luoKarttaKuvasta()
     *
     * @param tiedosto
     */
    public Kartta(File tiedosto) {
        try {
            kuva = ImageIO.read(tiedosto);
            kartta = new Koordinaatti[kuva.getHeight()][kuva.getWidth()];
            leveys = kuva.getWidth();
            korkeus = kuva.getHeight();
            luoKarttaKuvasta();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * Konstruktori, joka saa parametrina char taulukon, josta luodaan karttana
     * käytetty Koordinaatti-taulukko. Alustetaan muuttujat korkeus ja leveys
     * parametrina saadun taulukon mukaisesti.
     *
     * @param taulukko
     */
    public Kartta(char[][] taulukko) {
        charKartta = taulukko;
        korkeus = charKartta.length;
        leveys = charKartta[0].length;
        kartta = new Koordinaatti[korkeus][leveys];
        luoKarttaTaulukosta();
    }

    private void luoKarttaKuvasta() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                kartta[i][j] = new Koordinaatti(i, j);
                int piste = kuva.getRGB(j, i);
                if (piste == vihrea) {
                    kartta[i][j].setMerkki('S');
                    lahto = kartta[i][j];
                } else if (piste == musta) {
                    kartta[i][j].setMerkki('#');
                } else if (piste == punainen) {
                    kartta[i][j].setMerkki('M');
                    maali = kartta[i][j];
                } else {
                    kartta[i][j].setMerkki(' ');
                }
            }
        }
    }

    private void luoKarttaTaulukosta() {
        for (int i = 0; i < charKartta.length; i++) {
            for (int j = 0; j < charKartta[i].length; j++) {
                char merkki = charKartta[i][j];
                kartta[i][j] = new Koordinaatti(i, j);
                kartta[i][j].setMerkki(merkki);
                if (merkki == 'S') {
                    lahto = kartta[i][j];
                } else if (merkki == 'M') {
                    maali = kartta[i][j];
                }
            }
        }
    }

    /**
     * Palauttaa Koordinaatti-taulukon.
     *
     * @return
     */
    public Koordinaatti[][] getKartta() {
        return kartta;
    }

    /**
     * Palauttaa kartan olevan maalisolmun.
     *
     * @return
     */
    public Koordinaatti getMaali() {
        return maali;
    }

    /**
     * Palauttaa kartan lähtösolmun
     *
     * @return
     */
    public Koordinaatti getLahto() {
        return lahto;
    }

    /**
     * Palauttaa kartan korkeuden
     *
     * @return
     */
    public int getKorkeus() {
        return korkeus;
    }

    /**
     * Palauttaa kartan leveyden
     *
     * @return
     */
    public int getLeveys() {
        return leveys;
    }

    /**
     * Palauttaa kartassa sijainnissa (y,x) olevan Koordinaatti-olion.
     *
     * @param y
     * @param x
     * @return
     */
    public Koordinaatti getKoordinaatti(int y, int x) {
        return kartta[y][x];
    }

    /**
     * Palauttaa true, jos koordinaatissa (y,x) on seinä.
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return true, jos koordinaatissa (y,x) on seinä, muuten false
     */
    public boolean onSeina(int y, int x) {
        return kartta[y][x].getMerkki() == '#';
    }

    /**
     * Palauttaa kartan muodostamisessa käytetyn bufferedimagen
     * @return kuva
     */
    public BufferedImage getKuva() {
        return this.kuva;
    }

    boolean onkoValidi() {
        if (this.maali != null) {
            if (this.lahto != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Palauttaa true, jos (y,x) on maalisolmu
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return true jos (y,x) on maalisolmu, muuten false
     */
    public boolean onkoMaali(int y, int x) {
        if (y >= korkeus || x >= leveys ||y < 0 || x < 0){
            return false;
        }
        return kartta[y][x] == maali;
    }

    /**
     * Palauttaa true, jos koordinaatti (y,x) ei ole seinä eikä ole 
     * kartan ulkopuolella
     * @param y y-akselin arvo
     * @param x x-akselin arvo
     * @return true jos (y,x)-koordinaatissa voi kulkea, muuten false
     */
    public boolean voikoKulkea(int y, int x) {
        if (y >= korkeus || x >= leveys || y < 0 || x < 0 || onSeina(y, x)) {
            return false;
        }
        return true;
    }
}
