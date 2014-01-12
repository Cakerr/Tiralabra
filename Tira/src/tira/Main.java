package tira;

import tira.algoritmit.Astar;
import tira.algoritmit.Hakualgoritmi;
import tira.logiikka.Solver;

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
        
        Hakualgoritmi jee = new Astar();
    }
}
