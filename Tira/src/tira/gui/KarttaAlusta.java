package tira.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tira.logiikka.Solver;

public class KarttaAlusta extends JPanel {

    private int ruudunKoko;
    private Solver ratkaisija;


    public KarttaAlusta(Solver ratkaisija) {

        this.ratkaisija = ratkaisija;
        this.ruudunKoko = 1;
        
    }

    public void varitaNode(int y, int x, Graphics g ){
        g.setColor(Color.blue);
        if (!ratkaisija.onLahto(y, x) && !ratkaisija.onMaali(y, x)){
            g.fillRect(x * ruudunKoko+1, y * ruudunKoko+1 , ruudunKoko-1, ruudunKoko-1);
            
        }
        
    }
    
    private void piirraReuna(Graphics g){
        g.setColor(Color.gray);
        for (int i = 0; i < ratkaisija.getKartanKorkeus(); i++){
            g.drawLine(0, i*ruudunKoko, ratkaisija.getKartanLeveys()*ruudunKoko , i*ruudunKoko);
            for (int j = 0; j < ratkaisija.getKartanLeveys(); j++){
                g.drawLine(j*ruudunKoko ,0 ,   j*ruudunKoko, ratkaisija.getKartanKorkeus()*ruudunKoko);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < ratkaisija.getKartanKorkeus(); i++) {
            for (int j = 0; j < ratkaisija.getKartanLeveys(); j++) {
                if (ratkaisija.onLahto(i, j)) {
                    g.setColor(Color.green);
                }
                else if (ratkaisija.onMaali(i, j)) {
                    g.setColor(Color.red);
                }else if (ratkaisija.onSeina(i, j)){
                    g.setColor(Color.black);
                } 
                else {
                    g.setColor(Color.white);
                }
                
                g.fillRect(j * ruudunKoko+1, i * ruudunKoko+1, ruudunKoko-1, ruudunKoko-1);
                
            }
        }
        piirraReuna(g);
    }
    
    public void setScale(int scale){
        this.ruudunKoko = scale;
    }
}
