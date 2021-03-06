    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.io.File;
import tira.logiikka.Kartta;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Ari
 */
public class KarttaTest {

    private Kartta kartta1 = new Kartta(new File("EnsimmainenKartta.png"));
    private Kartta kartta2;
    private Kartta kartta3;
    private char[][] charKartta = {
        {'S', ' ', ' ', ' '},
        {' ', ' ', '#', ' '},
        {' ', ' ', '#', ' '},
        {' ', ' ', '#', 'M'}};

    public KarttaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        kartta3  = new Kartta(charKartta);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kartanLuominenPngKuvastaOnnistuu() {

        for (int i = 0; i < kartta1.getKorkeus(); i++) {
            for (int j = 0; j < kartta1.getLeveys(); j++) {
                if (j == 5 && i < 9) {
                    assertEquals('#', kartta1.getKoordinaatti(i, j).getMerkki());
                } else if (j == 6 && i == 9) {
                    assertEquals('#', kartta1.getKoordinaatti(i, j).getMerkki());
                } else if (i == 0 && j == 0) {
                    assertEquals('S', kartta1.getKoordinaatti(i, j).getMerkki());
                } else if (i == 0 && j == 9) {
                    assertEquals('M', kartta1.getKoordinaatti(i, j).getMerkki());
                } else {
                    assertEquals(' ', kartta1.getKoordinaatti(i, j).getMerkki());
                }
            }
        }
    }

    @Test
    public void kartanLuominenCharTaulukostaToimii() {
        kartta2 = new Kartta(charKartta);

        for (int i = 0; i < charKartta.length; i++) {
            for (int j = 0; j < charKartta[i].length; j++) {
                assertEquals(charKartta[i][j],
                        kartta2.getKoordinaatti(i, j).getMerkki());
            }
        }
    }
    
    @Test
    public void voikoKulkeaPalauttaaFalsekunYNegatiivinen(){
        assertEquals(false, kartta3.voikoKulkea(-1, 1));
    }
    
    @Test
    public void voikoKulkeaPalauttaaFalsekunXNegatiivinen(){
        assertEquals(false, kartta3.voikoKulkea(1, -1));
    }
    
    @Test
    public void voikoKulkeaPalauttaaFalsekunYLiianSuuri(){
        assertEquals(false, kartta3.voikoKulkea(kartta3.getKorkeus(), 1));
    }
    
    @Test
    public void voikoKulkeaPalauttaaFalsekunXLiianSuuri(){
        assertEquals(false, kartta3.voikoKulkea(kartta3.getLeveys(), 1));
    }
    
    @Test
    public void voikoKulkeaPalauttaaFalsekunKoordinaatissaSeina(){
        assertEquals(false, kartta3.voikoKulkea(9, 6));
    }
}
