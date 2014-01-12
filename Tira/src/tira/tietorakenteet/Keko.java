package tira.tietorakenteet;

import java.util.Comparator;
import tira.logiikka.Koordinaatti;

/**
 * Oma toteutus keosta, joka saa parametrina Comparator-luokan ilmentymän, jonka
 * avulla tehdään kahden eri Koordinaatin väliset "suuruus"vertailut.
 *
 * @author Ari
 */
public class Keko {

    private Koordinaatti[] keko;
    private int size;
    private Comparator<Koordinaatti> comparator;

    /**
     * Luo keon johon voidaan lisätä 7 alkiota ennen kuin sen koko täytyy
     * tuplata. Saa parametrina Comparator-olion, jonka avulla tehään kahden
     * Koordinaatin välinen vertailu.
     *
     * @param comparator
     */
    public Keko(Comparator<Koordinaatti> comparator) {
        size = 0;
        this.comparator = comparator;
        keko = new Koordinaatti[8];
    }

    /**
     * Palauttaa keossa olevien alkioiden määrän.
     *
     * @return
     */
    public int length() {
        return size;
    }

    /**
     * Palauttaa keon "päällä" olevan alkion, jos keossa ei ole yhtään alkiota,
     * niin palauttaa null. Pienentää size:a yhdellä
     *
     * @return
     */
    public Koordinaatti poll() {
        if (size < 1) {
            return null;
        }

        Koordinaatti pienin = keko[1];
        keko[1] = keko[size];
        size--;
        heapify(1);
        return pienin;
    }

    /**
     * Palauttaa tiedon siitä onko keossa yhtään alkiota.
     *
     * @return
     */
    public boolean isEmpty() {
        return size < 1;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return 2 * i + 1;
    }

    private void heapify(int i) {
        int r = rightChild(i);
        int l = leftChild(i);
        int pienin = 1;
        if (r <= size) {
            if (comparator.compare(keko[l], keko[r]) < 0) {
                pienin = l;
            } else {
                pienin = r;
            }
            if (comparator.compare(keko[i], keko[pienin]) > 0) {
                swap(i, pienin);
                heapify(pienin);
            }
        }
        if (l == size && comparator.compare(keko[i], keko[l]) > 0) {
            swap(i, l);
        }
    }

    /**
     * Lisää parametrina saadun Koordinaatin kekoon oikealle paikalle. Kasvattaa
     * sizea yhdellä. Joos keon toteutuksene käytetyssä taulukossa ei ole
     * enempää tilaa, niin tuplaa taulukon koon kutsumalla
     * kasvataKokoa()-metodia.
     *
     * @param node
     */
    public void add(Koordinaatti node) {
        size++;
        if (size >= keko.length) {
            kasvataKokoa();
        }
        int i = size;
        while (i > 1 && comparator.compare(keko[parent(i)], node) > 0) {
            keko[i] = keko[parent(i)];
            i = parent(i);
        }
        keko[i] = node;
    }

    private void swap(int i, int pienin) {
        Koordinaatti vali = keko[pienin];
        keko[pienin] = keko[i];
        keko[i] = vali;
    }

    private void kasvataKokoa() {

        Koordinaatti[] uusikeko = new Koordinaatti[size * 2];
        for (int i = 0; i < keko.length; i++) {
            uusikeko[i] = keko[i];
        }
        keko = uusikeko;
    }

    /**
     * Palauttaa keossa ensimmaisena olevan olion
     * @return ensimmainen olio
     */
    public Koordinaatti peak() {
        return keko[1];
    }
    
    public void delKoordinaatti(Koordinaatti koordinaatti){
        
        for(int i = 1; i <= size; i++){           
            if (keko[i] == koordinaatti){
                keko[i] = keko[size];
                size--;
                heapify(i);
                break;
                
            }
        }
    }
}
