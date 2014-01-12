package tira.logiikka;

/**
 *
 * @author Ari
 */
public class Koordinaatti {

    private int x;
    private int y;
    private Koordinaatti edellinen;
    private double maaliin; // heurrestiikan arvio noden etäisyydestä maaliin.
    private double alkuun;
    private boolean kayty = false;
    private char merkki;
    private boolean keossa = false;

    /**
     * Luodaan koordinaatti, joka saa parametrina x- ja y-akselien arvot.
     *
     * @param y
     * @param x
     */
    public Koordinaatti(int y, int x) {
        this.x = x;
        this.y = y;
        keossa = false;
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
    public void setMaaliin(double maaliin) {
        if (maaliin < 0){
            this.maaliin = 0;
        }
        this.maaliin = maaliin;
    }

    /**
     * Palauttaa koordinaatin suoran etäisyyden maalisolmuun.
     *
     * @return
     */
    public double getMaaliin() {
        return maaliin;
    }

    /**
     * Asettaa koordinaatin etäisyyden aloitussolmuun.
     *
     * @param alkuun
     */
    public void setAlkuun(double alkuun) {
        if (alkuun < 0){
            this.alkuun = 0;
        }
        this.alkuun = alkuun;
    }

    /**
     * Palauttaa koordinaatin etäisyyden aloitussolmuun.
     *
     * @return
     */
    public double getAlkuun() {
        return alkuun;
    }

    /**
     * Palauttaa tiedon, siitä onko etsintäkerralla käyty kyseisessä 
     * koordinaatissa
     * @return
     */
    public boolean getKayty() {
        return kayty;
    }

    /**
     * Asettaa tiedon, siitä onko etsintä käynyt kyseisessä koordinaatissa
     * @param kayty
     */
    public void setKayty(boolean kayty) {
        this.kayty = kayty;
    }

    /**
     * Palauttaa koordinaatin, josta "this."koordinaatiin on päästy.
     * @return
     */
    public Koordinaatti getEdellinen() {
        return edellinen;
    }

    /**
     * Asettaa edellisen koordinaatin
     * @param edellinen
     */
    public void setEdellinen(Koordinaatti edellinen) {
        this.edellinen = edellinen;
    }
    
    /**
     * Asettaa koordinaatille karttamerkin, joka tulkitaan esim. # -> seinä
     * @param merkki
     */
    public void setMerkki(char merkki){
        this.merkki = merkki;
    }
    
    /**
     * Palauttaa kyseisen koordinaatin karttamerkin
     * @return
     */
    public char getMerkki(){
        return this.merkki;
    }
    
    /**
     * 
     * @param onko
     */
    public void setKeossa(boolean onko){
        this.keossa = onko;
    }
    
    /**
     * 
     * @return
     */
    public boolean getKeossa(){
        return keossa;
    }
}
