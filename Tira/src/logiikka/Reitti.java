package logiikka;

/**
 *
 * @author Ari
 */
public class Reitti {

    private ReittiNode lahto;
    private ReittiNode maali;
    private ReittiNode next;
    private int pituus;

    /**
     * Luodaan Reitti, joka on linkitettylista ReittiNoden ilmentymiä.
     * Reittinode pitää sisällänsä tiedon reitissä käytetyistä koordinaateista.
     *
     * @param lahto
     */
    public Reitti(Koordinaatti lahto) {
        ReittiNode vali = new ReittiNode(lahto);
        this.lahto = vali;
        this.maali = vali;
        next = this.lahto;
        pituus = 1;
    }

    /**
     * Luodaan Reitti, jolla ei ole yhtään nodea.
     */
    public Reitti() {
        lahto = null;
        maali = null;
        next = null;
        pituus = 0;
    }

    /**
     * Palauttaa reitin seuraavan noden
     *
     * @return
     */
    public Koordinaatti next() {
        if (next == null) {
            return null;
        }
        Koordinaatti palautus = next.getKoordinaatti();
        next = next.getNext();
        return palautus;
    }

    /**
     * Asettaa next:n osoittamaan reitin ensimmaiseen nodeen
     */
    public void resetNext() {
        next = lahto;
    }

    /**
     * Palauttaa reitin pituuden
     *
     * @return
     */
    public int length() {
        return pituus;
    }

    /**
     * Lisaa node linkitettyyn listaan.
     *
     * @param node
     */
    public void lisaaNode(Koordinaatti node) {
        ReittiNode vali = new ReittiNode(node);
        if (lahto == null && maali == null) {
            lahto = vali;
            next = lahto;
        } else {
            maali.setNext(vali);  
        }
        maali = vali;
        pituus++;
    }
}
