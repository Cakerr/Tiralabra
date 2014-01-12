package tira.gui;

import actionlisteners.AloitusListener;
import actionlisteners.KartanLatausListener;
import actionlisteners.UusiKarttaListener;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import tira.logiikka.Solver;

/**
 *
 * @author Ari
 */
public class Karttaruutu extends JFrame {

    private JPanel tausta;
    private JPanel alapalkki;
    private JPanel ylapalkki;
    private KarttaAlusta ruudukko;
    private JLabel aika;
    private JLabel askeleet;
    private ButtonGroup algoritmi;
    private JRadioButton astar;
    private JRadioButton dijkstra;
    private JRadioButton jps;
    private Solver ohjain;
    private JButton etsi;
    private JButton uusiKartta;
    private JButton lataaKartta;

    /**
     *
     * @param ratkaisija
     */
    public Karttaruutu(Solver ratkaisija) {
        this.ohjain = ratkaisija;

        luoKayttoliittyma();
    }

    private void luoKayttoliittyma() {
        setTitle("Reittiopas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        luoKomponentit();
        setSize(1400, 1100);
        setContentPane(tausta);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void luoKomponentit() {
        tausta = new JPanel();
        alapalkki = new JPanel();
        ylapalkki = new JPanel();
        aika = new JLabel();
        askeleet = new JLabel();

        ruudukko = new KarttaAlusta(ohjain);
        uusiKartta = new JButton("luo uusi kartta");
        lataaKartta = new JButton("Lataa kartta");
        algoritmi = new ButtonGroup();
        astar = new JRadioButton("A*");
        jps = new JRadioButton("Jump Point Search");
        dijkstra = new JRadioButton("Dijkstra");
        etsi = new JButton("Etsi reitti");
        etsi.addMouseListener(new AloitusListener(ohjain, this, astar, jps, dijkstra));
        uusiKartta.addMouseListener(new UusiKarttaListener(ohjain));
        lataaKartta.addMouseListener(new KartanLatausListener(ohjain));

        asetaKomponentit();

    }

    private void asetaKomponentit() {
        tausta.setLayout(new BorderLayout());
        tausta.setSize(1000, 1000);

        algoritmi.add(astar);
        algoritmi.add(jps);
        algoritmi.add(dijkstra);
        astar.setSelected(true);
        ylapalkki.add(astar);
        ylapalkki.add(jps);
        ylapalkki.add(dijkstra);
        ylapalkki.add(etsi);
        ylapalkki.add(uusiKartta);
        ylapalkki.add(lataaKartta);
        alapalkki.add(aika);
        alapalkki.add(askeleet);
        tausta.add(ruudukko, BorderLayout.CENTER);
        tausta.add(alapalkki, BorderLayout.EAST);
        tausta.add(ylapalkki, BorderLayout.NORTH);
        piirraKartta();

    }

    /**
     *
     */
    public void maalaaReitti() {
        ruudukko.paintComponent(ruudukko.getGraphics());
        onkoReittia();
        //if (onkoReittia()) {
//            for (int i = 1; i < ohjain.getKartanKorkeus(); i++) {
//                for (int j = 0; j < ohjain.getKartanLeveys(); j++) {
//                    if (ohjain.onKayty(i, j)) {
//                        ruudukko.varitaKayty(i, j, ruudukko.getGraphics());
//                    }
//
//                }
//            }
            for (int i = 0; i < ohjain.getReitinPituus(); i++) {
                int[] jee = ohjain.getSeuraava();
                int y = jee[0];
                int x = jee[1];
                if (!ohjain.onMaali(y, x) && !ohjain.onLahto(y, x)) {
                    ruudukko.varitaReitti(y, x, ruudukko.getGraphics());
                }
            //}
        }
    }

    private boolean onkoReittia() {
        if (ohjain.getReitinPituus() == 0) {
            JOptionPane.showMessageDialog(null, "Reittiä ei löytynyt!", "OH NOES!", 2);
            return false;
        }
        return true;
    }

    /**
     *
     */
    public void piirraKartta() {

        int korkeus = ohjain.getKartanKorkeus();
        int leveys = ohjain.getKartanLeveys();
        int scale = 0;

        while (korkeus * scale <= tausta.getWidth()
                && leveys * scale <= tausta.getHeight()) {
            scale++;
        }
        ruudukko.setScale(scale - 1);

        ruudukko.paintComponents(ruudukko.getGraphics());
        repaint();


    }

    /**
     *
     */
    public void asetaAika() {
        askeleet.setText("Lahdosta maaliin tarvittiin " + ohjain.getAskeleetMaaliin() + "askelta.");
        aika.setText("Algoritmin suoritukseen meni " + ohjain.getSuoritusaika() + "ms.");
        aika.update(this.getGraphics());
    }
}
