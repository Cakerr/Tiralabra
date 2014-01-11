package tira;

import java.io.File;
import tira.algoritmit.Astar;
import tira.algoritmit.Hakualgoritmi;
import tira.algoritmit.JumpPointSearch;
import tira.gui.Karttaruutu;
import tira.logiikka.Kartta;
import tira.logiikka.Koordinaatti;
import tira.logiikka.Solver;
import tira.tietorakenteet.Reitti;

/**
 *
 * @author Ari
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Solver solveri = new Solver();
        solveri.run();
        
        
    }
}
