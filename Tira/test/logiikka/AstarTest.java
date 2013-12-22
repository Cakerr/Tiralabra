/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Ari
 */
public class AstarTest {

    private Astar astar;
    private Koordinaatti[][] kartta1;

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
        kartta1 = new Koordinaatti[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                kartta1[i][j] = new Koordinaatti(i, j);
                kartta1[i][j].setAlkuun(j);
                kartta1[i][j].setMaaliin(12);
            }
        }
        astar = new Astar(kartta1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void relaxAllToimiiOikein() {
        Koordinaatti maali = kartta1[9][9];
        astar.relaxAll(kartta1[0][0], kartta1[9][9]);
        for (int i = 0; i < kartta1.length; i++) {
            for (int j = 0; j < kartta1.length; j++) {
                Koordinaatti node = kartta1[i][j];
                if (i == 0 && j == 0) {
                    assertTrue(node.getAlkuun() == 0);
                } else {
                    assertTrue(node.getAlkuun() == Integer.MAX_VALUE);
                }
            }
        }
    }
    
    @Test
    public void astarPalauttaaOikeanPituisenReitin(){
        
    }
}