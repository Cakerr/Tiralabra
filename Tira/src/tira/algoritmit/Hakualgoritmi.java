
package tira.algoritmit;

import tira.logiikka.Kartta;
import tira.logiikka.Koordinaatti;
import tira.tietorakenteet.AstarKoordinaattiComparator;
import tira.tietorakenteet.Keko;
import tira.tietorakenteet.Reitti;

public abstract class Hakualgoritmi {
    
    private Kartta kartta;
    private Koordinaatti lahto;
    private Koordinaatti maali;
    private Timer timer;
    private Keko keko;
    
    public Hakualgoritmi(){
        timer = new Timer();
    }
    protected Kartta getKartta(){
        return this.kartta;
    }
    
    protected Keko getKeko(){
        return this.keko;
    }
    
    protected Koordinaatti getMaali(){
        return maali;
    }
    
    protected Koordinaatti getLahto(){
        return lahto;
    }
    
    public void setKartta(Kartta kartta){
        this.kartta = kartta;
    }
    
    public Reitti findPath(Kartta kartta){
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
            if (kasiteltava.getKayty() || kasiteltava.getKeossa()) {
                continue;
            }
            kasiteltava.setKayty(true);

            lisaaSeuraajatKekoon(kasiteltava);
        }
        if (maali.getKayty()) {

            tulostaReitti(maali, reitti);
        }
        this.timer.stop();
        return reitti;
    }
    public long suoritusaika(){
        return timer.time();
    }
    
    public void relaxAll() {
        for (int i = 0; i < kartta.getKorkeus(); i++) {
            for (int j = 0; j < kartta.getLeveys(); j++) {
                kartta.getKoordinaatti(i, j).setAlkuun(Integer.MAX_VALUE);
                kartta.getKoordinaatti(i, j).setKayty(false);
                kartta.getKoordinaatti(i, j).setKeossa(false);
            }
        }
        lahto.setAlkuun(0);
    }
    
    protected boolean onkoSeina(Koordinaatti node) {
        return node.getMerkki() == '#';
    }
    
    protected boolean onkoSeina(int y, int x) {
        return kartta.onSeina(y, x);
    }
    
    protected boolean onkoMaali(int y, int x){
        return kartta.onkoMaali(y, x);
    }
    
    protected abstract void tulostaReitti(Koordinaatti maali, Reitti reitti);
    
    protected abstract void lisaaSeuraajatKekoon(Koordinaatti kasiteltava);
    
    protected Koordinaatti getKoordinaatti(int y, int x){
        return kartta.getKoordinaatti(y, x);
    }
    
    public boolean voikoKulkea(int y, int x){
        return kartta.voikoKulkea(y, x);
    }
}
