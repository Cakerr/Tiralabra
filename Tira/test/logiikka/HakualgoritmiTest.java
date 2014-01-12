/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tira.algoritmit.Astar;
import tira.algoritmit.Dijkstra;
import tira.algoritmit.JumpPointSearch;
import tira.logiikka.Kartta;
import tira.tietorakenteet.Reitti;

/**
 *
 * @author Ari
 */
public class HakualgoritmiTest {

    private Astar astar;
    private JumpPointSearch jps;
    private Dijkstra dj;

    public HakualgoritmiTest() {
    }

    @Before
    public void setUp() {
        astar = new Astar();
        jps = new JumpPointSearch();
        dj = new Dijkstra();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void JpstestaaReittienPituuksiaSatunnaisillaKartoilla() {
        for (int i = 0; i < 200; i++) {
            int r = Math.max((int) (Math.random() * 20), 3);

            char[][] charkartta = new char[r][r];
            int rX = (int) Math.min((Math.random() * r), r);
            int rY = (int) Math.min((Math.random() * r), r);


            charkartta[rY][rX] = 'S';
            rX = (int) (Math.random() * r);
            rY = (int) (Math.random() * r);

            charkartta[rY][rX] = 'M';
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < r; x++) {
                    double random = Math.random();
                    if (charkartta[y][x] == 'S' || charkartta[y][x] == 'M') {
                        continue;
                    }
                    if (random < 0.1) {
                        charkartta[y][x] = '#';
                    } else {
                        charkartta[y][x] = ' ';
                    }
                }
            }
            Kartta kartta = new Kartta(charkartta);
            if (kartta.getLahto() != null && kartta.getMaali() != null) {
                Reitti astarin = astar.findPath(kartta);
                double astarKuljettu = kartta.getMaali().getAlkuun();
                Reitti dijkstran = dj.findPath(kartta);
                double dijkstranKuljettu = kartta.getMaali().getAlkuun();
                System.out.println("Dijkstra: " + dijkstranKuljettu + " "+ dj.suoritusaika());
                System.out.println("ASTAR: " + astarKuljettu +" "+astar.suoritusaika());
                System.out.println(kartta.getKorkeus() + " " + kartta.getLeveys());
                assertTrue("ASTAR: " + astarKuljettu + "\n dijkstra: " + dijkstranKuljettu,
                        astarKuljettu - dijkstranKuljettu < 1.0);
                
            }
        }
    }
    @Test
    public void jpstestaaReittienPituuksiaSatunnaisillaKartoilla() {
        for (int i = 0; i < 200; i++) {
            int r = Math.max((int) (Math.random() * 50), 3);

            char[][] charkartta = new char[r][r];
            int rX = (int) Math.min((Math.random() * r), r);
            int rY = (int) Math.min((Math.random() * r), r);


            charkartta[rY][rX] = 'S';
            rX = (int) (Math.random() * r);
            rY = (int) (Math.random() * r);

            charkartta[rY][rX] = 'M';
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < r; x++) {
                    double random = Math.random();
                    if (charkartta[y][x] == 'S' || charkartta[y][x] == 'M') {
                        continue;
                    }
                    if (random < 0.1) {
                        charkartta[y][x] = '#';
                    } else {
                        charkartta[y][x] = ' ';
                    }
                }
            }
            Kartta kartta = new Kartta(charkartta);
            if (kartta.getLahto() != null && kartta.getMaali() != null) {
                Reitti dijkstran = dj.findPath(kartta);
                double dijkstranKuljettu = kartta.getMaali().getAlkuun();
                jps.findPath(kartta);
                double jpsnKuljettu = kartta.getMaali().getAlkuun();

                System.out.println(kartta.getKorkeus() +" " +kartta.getLeveys() );
                System.out.println("Dijkstra: " + dijkstranKuljettu +" " +dj.suoritusaika());
                System.out.println("JPS: " + jpsnKuljettu +" "+jps.suoritusaika());
 
                assertTrue("jps: " + jpsnKuljettu + "\n dijkstra: " + dijkstranKuljettu, jpsnKuljettu - dijkstranKuljettu <= 1.0);
            }
        }
    }
}
