/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import tira.tietorakenteet.Reitti;
import tira.logiikka.Koordinaatti;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Ari
 */
public class ReittiTest {
    
    private Reitti reitti;
    
    public ReittiTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        reitti = new Reitti();
    }
    
    @After
    public void tearDown() {
    }
   
    @Test
    public void nextPalauttaaNullJosReittiOnTyhja(){
        assertEquals(null, reitti.getNext());
    }
    
    @Test
    public void nextPalauttaaSeuraavan(){
        Koordinaatti kor1 = new Koordinaatti(0, 0);
        Koordinaatti kor2 = new Koordinaatti(0, 0);
        reitti.lisaaNode(kor1);
        reitti.lisaaNode(kor2);
        
        Koordinaatti testi = reitti.getNext();
        assertEquals(kor1, testi);
        testi = reitti.getNext();
        assertEquals(kor2, testi);
        testi = reitti.getNext();
        assertEquals(null, testi);
        
    }
    
    @Test
    public void pituusOnOikein(){
         Koordinaatti kor1 = new Koordinaatti(0, 0);
        Koordinaatti kor2 = new Koordinaatti(0, 0);
        reitti.lisaaNode(kor1);
        reitti.lisaaNode(kor2);
        assertEquals(2, reitti.length());
    }
}
