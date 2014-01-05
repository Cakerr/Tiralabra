package tira;

import logiikka.Astar;
import logiikka.Kartta;
import logiikka.Koordinaatti;
import logiikka.Reitti;

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
       /* Koordinaatti[][] kartta = new Koordinaatti[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Koordinaatti jee = new Koordinaatti(i, j);
                kartta[i][j] = jee;
                if (j == 5 && i != 9) {
                    jee.setMerkki('#');
                }
            }
        }

        Astar stara = new Astar(kartta);
        Reitti reitti = stara.findPath(kartta[0][0], kartta[6][9]);
        */
       
        
        Kartta kartta = new Kartta("EnsimmainenKartta.png");
        Astar star = new Astar(kartta);
        Reitti reitti = star.findPath();
        Koordinaatti jepa = reitti.getNext();
        System.out.println(reitti.length());
        while (jepa != null) {
            System.out.println("y: " +jepa.getY() +" X: " +jepa.getX());
            jepa = reitti.getNext();
        }
    }
}
