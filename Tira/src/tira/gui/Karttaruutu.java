package tira.gui;

import actionlisteners.AloitusListener;
import actionlisteners.KartanLatausListener;
import actionlisteners.UusiKarttaListener;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import tira.logiikka.Solver;

public class Karttaruutu extends JFrame {

    private JPanel tausta;
    private JPanel alapalkki;
    private JPanel ylapalkki;
    private KarttaAlusta ruudukko;
    private JLabel aika;
    private ButtonGroup algoritmi;
    private JRadioButton astar;
    private JRadioButton jps;
    private Solver ohjain;
    private JButton etsi;
    private JButton uusiKartta;
    private JButton lataaKartta;

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

        ruudukko = new KarttaAlusta(ohjain);
        uusiKartta = new JButton("luo uusi kartta");
        lataaKartta = new JButton("Lataa kartta");
        algoritmi = new ButtonGroup();
        astar = new JRadioButton("A*");
        jps = new JRadioButton("Jump Point Search");
        etsi = new JButton("Etsi reitti");
        etsi.addMouseListener(new AloitusListener(ohjain, this));
        uusiKartta.addMouseListener(new UusiKarttaListener(ohjain));
        lataaKartta.addMouseListener(new KartanLatausListener(ohjain));
        
        asetaKomponentit();

    }

    private void asetaKomponentit() {
        tausta.setLayout(new BorderLayout());
        tausta.setSize(1000, 1000);


        algoritmi.add(astar);
        algoritmi.add(jps);
        astar.setSelected(true);
        ylapalkki.add(astar);
        ylapalkki.add(jps);
        ylapalkki.add(etsi);
        ylapalkki.add(uusiKartta);
        ylapalkki.add(lataaKartta);
        alapalkki.add(aika);
        tausta.add(ruudukko, BorderLayout.CENTER);
        tausta.add(alapalkki, BorderLayout.EAST);
        tausta.add(ylapalkki, BorderLayout.NORTH);
        piirraKartta();

    }

    public void maalaaReitti() {
        onkoReittia();
        for (int i = 1; i < ohjain.getReitinPituus(); i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Karttaruutu.class.getName()).log(Level.SEVERE, null, ex);
            }
            int[] koordinaatti = ohjain.getSeuraava();
            int y = koordinaatti[0];
            int x = koordinaatti[1];
            if (!ohjain.onMaali(y, x) && !ohjain.onLahto(y, x)) {
                ruudukko.varitaNode(y, x, ruudukko.getGraphics());
            }
        }
    }

    private void onkoReittia() {
        if (ohjain.getReitinPituus() == 0) {
            JOptionPane.showMessageDialog(null, "Reittiä ei löytynyt!", "OH NOES!", 2);
        }
    }

    public void piirraKartta() {
        
        int korkeus = ohjain.getKartanKorkeus();
        int leveys = ohjain.getKartanLeveys();
        int scale = 0;

        while (korkeus * scale <= tausta.getWidth()
                && leveys * scale <= tausta.getHeight()) {
            scale++;
        }
        ruudukko.setScale(scale-1);
        
        ruudukko.paintComponents(ruudukko.getGraphics());
        repaint();

    }

    public void asetaAika() {
        aika.setText("Algoritmin suoritukseen meni " + ohjain.getSuoritusaika() + "ms.");
    }
}
