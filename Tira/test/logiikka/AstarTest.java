/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.io.File;
import tira.logiikka.Koordinaatti;
import tira.logiikka.Kartta;
import tira.algoritmit.Astar;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Ari
 */
public class AstarTest {

    private Astar astar;
    private Kartta kartta1;
    private Kartta kartta2;
    private Koordinaatti[][] taulukkoKartta1;

    public AstarTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
       char[][] taulukko = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 9 && j == 9){
                    taulukko[i][j] = 'M';
                } else if (i == 0 && j == 0){
                    taulukko[i][j] = 'S';
                } else {
                    taulukko[i][j] = ' ';
                }
            }
        }
        kartta1 = new Kartta(taulukko);
        taulukkoKartta1 = kartta1.getKartta();
        astar = new Astar();
        kartta2 = new Kartta(new File("EiRatkaisua.png"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void relaxAllToimiiOikein() {
        astar.relaxAll();
        for (int i = 0; i < kartta1.getKorkeus(); i++) {
            for (int j = 0; j < kartta1.getLeveys(); j++) {
                Koordinaatti node = kartta1.getKoordinaatti(i, j);
                if (i == 0 && j == 0) {
                    assertTrue(node.getAlkuun() == 0);
                } else {
                    assertTrue(node.getAlkuun() == Integer.MAX_VALUE);
                }
            }
        }
    }
    
    @Test
    public void palauttaaTyhjanReitinJosReittiaEiloydy(){
        astar = new Astar();
        assertEquals(null, astar.findPath(kartta2).getNext());
    }
}