/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import tira.logiikka.Koordinaatti;
import tira.tietorakenteet.Keko;
import tira.tietorakenteet.AstarKoordinaattiComparator;
import java.util.PriorityQueue;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author Ari
 */
public class KekoTest {

    private Keko keko;
    private AstarKoordinaattiComparator compa;

    public KekoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        compa = new AstarKoordinaattiComparator();
        keko = new Keko(compa);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tyhjastaKeostaPollaaminenPalauttaaNull() {
        assertEquals(null, keko.poll());
    }

    @Test
    public void insertToimiiYhdellaLisayksella() {
        Koordinaatti x = new Koordinaatti(2, 2);
        keko.add(x);
        assertEquals(1, keko.length());
    }

    @Test
    public void pollToimiiYhdellaLisayksella() {
        Koordinaatti x = new Koordinaatti(2, 2);
        keko.add(x);
        assertEquals(x, keko.poll());
        assertEquals(0, keko.length());
        assertTrue(keko.isEmpty());
    }

    @Test
    public void keonKoonKasvattaminenToimii() {
        for (int i = 0; i < 12; i++) {
            keko.add(new Koordinaatti(i, i));
        }
        assertEquals(12, keko.length());
        for (int i = 0; i < 12; i++) {
            assertTrue(keko.poll() != null);
        }
        assertTrue(keko.poll() == null);
    }

    @Test
    public void kahdenLisaysToimii() {
        Koordinaatti k1 = new Koordinaatti(0, 0);
        k1.setAlkuun(3);
        k1.setMaaliin(5);
        Koordinaatti k2 = new Koordinaatti(3, 3);
        k2.setAlkuun(2);
        k2.setAlkuun(3);
        keko.add(k1);
        keko.add(k2);

        assertEquals(k2, keko.poll());
        assertEquals(k1, keko.poll());
    }

    @Test
    public void kahdenLisaysToimii2() {
        Koordinaatti k1 = new Koordinaatti(0, 0);
        k1.setAlkuun(3);
        k1.setMaaliin(5);
        Koordinaatti k2 = new Koordinaatti(3, 3);
        k2.setAlkuun(2);
        k2.setAlkuun(3);
        keko.add(k2);
        keko.add(k1);

        assertEquals(k2, keko.poll());
        assertEquals(k1, keko.poll());

    }
    
    @Test
    public void heapifyToimiiOikeinKunSwapataanOikeaLapsi(){
        Koordinaatti x = new Koordinaatti(0, 0);
        Koordinaatti y = new Koordinaatti(0, 0);
        Koordinaatti z = new Koordinaatti(0, 0);
        Koordinaatti g = new Koordinaatti(0, 0);
        x.setAlkuun(0);
        x.setMaaliin(0);
        y.setAlkuun(1);
        y.setMaaliin(1);  
        z.setAlkuun(2);
        z.setMaaliin(2);
        g.setAlkuun(3);
        g.setMaaliin(3);
        
        keko.add(x);
        keko.add(z);
        keko.add(y);
        keko.add(g);
        
        assertEquals(x, keko.poll());
        assertEquals(y, keko.poll());
        assertEquals(z, keko.poll());
        assertEquals(g, keko.poll());
    }
    
    @Test
    public void heapifyToimiiOikeinKunSwapataanVasenLapsi(){
        Koordinaatti x = new Koordinaatti(0, 0);
        Koordinaatti y = new Koordinaatti(0, 0);
        Koordinaatti z = new Koordinaatti(0, 0);
        Koordinaatti g = new Koordinaatti(0, 0);
        x.setAlkuun(0);
        x.setMaaliin(0);
        y.setAlkuun(1);
        y.setMaaliin(1);  
        z.setAlkuun(2);
        z.setMaaliin(2);
        g.setAlkuun(3);
        g.setMaaliin(3);
        
        keko.add(x);
        keko.add(y);
        keko.add(z);
        keko.add(g);
        
        assertEquals(x, keko.poll());
        assertEquals(y, keko.poll());
        assertEquals(z, keko.poll());
        assertEquals(g, keko.poll());
    }

    @Test
    public void useanLisaysToimii() {
        Koordinaatti x = new Koordinaatti(0, 0);
        Koordinaatti y = new Koordinaatti(0, 0);
        x.setAlkuun(0);
        x.setMaaliin(0);
        y.setAlkuun(1);
        y.setMaaliin(1);

        for (int i = 0; i < 200; i++) {
            if (i == 12) {
                keko.add(y);
            } else if (i == 177) {
                keko.add(x);
            }
            Koordinaatti z = new Koordinaatti(i, i);
            z.setAlkuun(i);
            z.setMaaliin(i + 1);
            keko.add(z);
        }
        assertEquals(x, keko.poll());
        keko.poll();
        assertEquals(y, keko.poll());
        //assertEquals(y, keko.poll());
    }
/*
    @Test
    public void palauttaaAlkiotSamassaJärjestyksessäKuinJavanToteutus() {
        PriorityQueue<Koordinaatti> java = new PriorityQueue<>(1, compa);

        for (int i = 0; i < 20000; i++) {
            Koordinaatti z = new Koordinaatti(i, i);
            z.setAlkuun(i % 9);
            z.setMaaliin(i % 5);
            java.add(z);
            keko.add(z);
        }
        for (int i = 0; i < 20000; i++){
            Koordinaatti javan = java.poll();
            Koordinaatti oman = keko.poll();           
            assertEquals(javan.getAlkuun()+javan.getMaaliin(), 
                    oman.getAlkuun()+oman.getMaaliin());          
        }

    }
*/
    @Test
    public void insertSuhdeJavanPriorityQueueToteutukseenonAlle2() {
        PriorityQueue java = new PriorityQueue<>(8, compa);
        Keko oma = new Keko(compa);

        long javaAlku = System.currentTimeMillis();
        for (int i = 0; i < 600000; i++) {
            Koordinaatti z = new Koordinaatti(i, i);
            z.setAlkuun(i % 9);
            z.setMaaliin(i % 5);
            java.add(z);
        }
        long javaLoppu = System.currentTimeMillis();
        double javaAika = javaLoppu - javaAlku;

        long omaAlku = System.currentTimeMillis();
        for (int i = 0; i < 600000; i++) {
            Koordinaatti z = new Koordinaatti(i, i);
            z.setAlkuun(i % 9);
            z.setMaaliin(i % 5);
            oma.add(z);
        }
        long omaLoppu = System.currentTimeMillis();
        double omaAika = omaLoppu - omaAlku;
        System.out.println(omaAika / javaAika);
        assertTrue(omaAika / javaAika < 2);
    }
    
    @Test
    public void pollSuhdeJavanPriorityQueueToteutukseenAlle2() {
        PriorityQueue java = new PriorityQueue<>(8, compa);
        Keko oma = new Keko(compa);
        
        for (int i = 0; i < 600000; i++) {
            Koordinaatti z = new Koordinaatti(i, i);
            z.setAlkuun(i % 9);
            z.setMaaliin(i % 5);
            java.add(z);
            oma.add(z);
        }
        long javaAlku = System.currentTimeMillis();
        for (int i = 0; i < 600000; i++){
            java.poll();
        }
        long javaLoppu = System.currentTimeMillis();
        double javaAika = javaLoppu - javaAlku;
            
        long omaAlku = System.currentTimeMillis();
        for (int i = 0; i < 600000; i++){
            oma.poll();
        }
        long omaLoppu = System.currentTimeMillis();
        double omaAika = omaLoppu - omaAlku;
        System.out.println(omaAika / javaAika);
        assertTrue(omaAika / javaAika < 2);
        
    }
}
