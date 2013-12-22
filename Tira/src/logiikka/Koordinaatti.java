package logiikka;

/**
 *
 * @author Ari
 */
public class Koordinaatti {

    private int x;
    private int y;
    private Koordinaatti edellinen;
    private int maaliin; // heurrestiikan arvio noden etäisyydestä maaliin.
    private int alkuun;
    private boolean kayty;
    private char merkki;

    /**
     * Luodaan koordinaatti, joka saa parametrina x- ja y-akselien arvot.
     *
     * @param y
     * @param x
     */
    public Koordinaatti(int y, int x) {
        this.x = x;
        this.y = y;
    }

    /**
     * Palauttaaa koordinaatin y-arvon.
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Palauttaa koordinaatin x-arvon
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Asettaa koordinaatin suoran etäisyyden maalisolmuun.
     *
     * @param maaliin
     */
    public void setMaaliin(int maaliin) {
        this.maaliin = maaliin;
    }

    /**
     * Palauttaa koordinaatin suoran etäisyyden maalisolmuun.
     *
     * @return
     */
    public int getMaaliin() {
        return maaliin;
    }

    /**
     * Asettaa koordinaatin etäisyyden aloitussolmuun.
     *
     * @param alkuun
     */
    public void setAlkuun(int alkuun) {
        this.alkuun = alkuun;
    }

    /**
     * Palauttaa koordinaatin etäisyyden aloitussolmuun.
     *
     * @return
     */
    public int getAlkuun() {
        return alkuun;
    }

    public boolean getKayty() {
        return kayty;
    }

    public void setKayty(boolean kayty) {
        this.kayty = kayty;
    }

    public Koordinaatti getEdellinen() {
        return edellinen;
    }

    public void setEdellinen(Koordinaatti edellinen) {
        this.edellinen = edellinen;
    }
    
    public void setMerkki(char merkki){
        this.merkki = merkki;
    }
    
    public char getMerkki(){
        return this.merkki;
    }
}
