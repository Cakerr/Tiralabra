package logiikka;

import java.util.PriorityQueue;

/**
 *
 * @author Ari
 */
public class Astar {

    private Koordinaatti[][] kartta;

    public Astar(Koordinaatti[][] kartta) {
        this.kartta = kartta;
    }

    /**
     *
     * @param lahto
     * @param maali
     */
    public Reitti findPath(Koordinaatti lahto, Koordinaatti maali) {
        PriorityQueue<Koordinaatti> keko =
                new PriorityQueue<>(1, new AstarKoordinaattiComparator());
        Reitti reitti = new Reitti();
        relaxAll(lahto, maali);
        keko.add(lahto);

        while (!maali.getKayty() && !keko.isEmpty()) {
            Koordinaatti kasiteltava = keko.poll();
            kasiteltava.setKayty(true);

            lisaaNaapuritKekoon(kasiteltava, keko, maali);
        }
        if (maali.getKayty()) {
            
            tulostaReitti(maali, reitti);
        }
        
        return reitti;
    }

    public void lisaaNaapuritKekoon(Koordinaatti kasiteltava,
            PriorityQueue<Koordinaatti> keko, Koordinaatti maali) {

        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX(), keko, kasiteltava, maali);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX(), keko, kasiteltava, maali);
        asetaNaapurinEtaisyydet(kasiteltava.getY(), kasiteltava.getX() + 1, keko, kasiteltava, maali);
        asetaNaapurinEtaisyydet(kasiteltava.getY(), kasiteltava.getX() - 1, keko, kasiteltava, maali);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX() - 1, keko, kasiteltava, maali);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX() + 1, keko, kasiteltava, maali);
        asetaNaapurinEtaisyydet(kasiteltava.getY() + 1, kasiteltava.getX() - 1, keko, kasiteltava, maali);
        asetaNaapurinEtaisyydet(kasiteltava.getY() - 1, kasiteltava.getX() + 1, keko, kasiteltava, maali);

    }

    private void asetaNaapurinEtaisyydet(int naapurinY, int naapurinX,
            PriorityQueue<Koordinaatti> keko, Koordinaatti kasiteltava,
            Koordinaatti maali) {

        try {
            Koordinaatti naapuri = kartta[naapurinY][naapurinX];
            if (!onkoSeina(naapuri)) {
                asetaEtaisyysAlkuun(naapuri, kasiteltava);
                asetaEtaisyysMaaliin(naapuri, maali);
                if (!naapuri.getKayty()) {
                    keko.add(naapuri);
                }
            }
        } catch (Exception e) {
        }
    }

    private void asetaEtaisyysMaaliin(Koordinaatti naapuri, Koordinaatti maali) {
        int suurempi = Math.max(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        int pienempi = Math.min(Math.abs(naapuri.getY() - maali.getY()),
                Math.abs(naapuri.getX() - maali.getX()));
        int etaisyys = pienempi + (suurempi - pienempi);
        naapuri.setMaaliin(etaisyys);
    }

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
     * @param lahto
     * @param maali
     */
    public void relaxAll(Koordinaatti lahto, Koordinaatti maali) {
        for (int i = 0; i < kartta.length; i++) {
            for (int j = 0; j < kartta.length; j++) {
                kartta[i][j].setAlkuun(Integer.MAX_VALUE);
                kartta[i][j].setKayty(false);
            }
        }
        lahto.setAlkuun(0);
    }

    private void tulostaReitti(Koordinaatti node, Reitti reitti) {
        if (node.getEdellinen() != null) {
            tulostaReitti(node.getEdellinen(), reitti);
        }
        reitti.lisaaNode(node);
        //System.out.println("Y: " + node.getY() + " X: " + node.getX());
    }

    private boolean onkoSeina(Koordinaatti node) {
        return node.getMerkki() == '#';
    }
}
