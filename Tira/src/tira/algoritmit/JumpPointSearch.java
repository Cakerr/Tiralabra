/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.algoritmit;

import tira.logiikka.Koordinaatti;
import tira.tietorakenteet.Reitti;
import java.*;

/**
 *
 * @author Ari
 */
public class JumpPointSearch extends Hakualgoritmi {

    public JumpPointSearch() {
        super();
    }

    @Override
    public void lisaaSeuraajatKekoon(Koordinaatti kasiteltava) {
        Koordinaatti[] naapurit = tunnistaNaapurit(kasiteltava);

        for (int i = 0; i < naapurit.length; i++) {

            Koordinaatti node = naapurit[i];
            if (node != null) {
                naapurit[i].setKayty(true);

                int dX = laskeSuunta(kasiteltava.getX(), node.getX());
                int dY = laskeSuunta(kasiteltava.getX(), node.getY());

                Koordinaatti jumpPoint = jump(kasiteltava.getY(), kasiteltava.getX(), dY, dX);
                if (jumpPoint != null) {
                    System.out.println(jumpPoint.getX() + ", " + jumpPoint.getY());
                    //jumpPoint.setKeossa(true);
                    jumpPoint.setEdellinen(kasiteltava);
                    getKeko().add(jumpPoint);
                }
            }
        }
    }

    @Override
    public void tulostaReitti(Koordinaatti maali, Reitti reitti) {
        if (maali.getEdellinen() != null) {
            tulostaReitti(maali.getEdellinen(), reitti);
        }
        reitti.lisaaNode(maali);
    }

    private Koordinaatti[] tunnistaNaapurit(Koordinaatti node) {

        int x = node.getX();
        int y = node.getY();
        int i = 0;

        Koordinaatti[] naapurit = new Koordinaatti[5];
        if (node.getEdellinen() != null) {
            int pX = node.getEdellinen().getX();
            int pY = node.getEdellinen().getY();
            int dX = laskeSuunta(x, pX);
            int dY = laskeSuunta(y, pY);

            //Diagonaalinen siirtymä
            if (dY != 0 && dX != 0) {
                if (voikoKulkea(y + dY, x)) {
                    naapurit[i] = getKoordinaatti(y + dY, x);
                    i++;
                }
                if (voikoKulkea(y + dY, x + dX)) {
                    naapurit[i] = getKoordinaatti(y + dY, x + dX);
                    i++;
                }
                if (voikoKulkea(y, x + dX)) {
                    naapurit[i] = getKoordinaatti(y, x + dX);
                    i++;
                }

                if (!voikoKulkea(y, x - dX) && voikoKulkea(y + dY, x)) {
                    naapurit[i] = getKoordinaatti(y + dY, x);
                    i++;
                }

                if (!voikoKulkea(y - dY, x) && voikoKulkea(y, x + dX)) {
                    naapurit[i] = getKoordinaatti(y, x + dX);
                    i++;
                }
            } else {
                //vaakasiirtymä
                if (dY == 0) {
                    if (voikoKulkea(y, x + dX) && !voikoKulkea(y - 1, x)) {
                        naapurit[i] = getKoordinaatti(y - 1, x + dX);
                        i++;
                    }
                    if (voikoKulkea(y, x + dX) && !voikoKulkea(y + 1, x)) {
                        naapurit[i] = getKoordinaatti(y + 1, x + dX);
                        i++;
                    }
                }
                //pystysiirtymä
                if (dX == 0) {
                    if (voikoKulkea(y + dY, x) && !voikoKulkea(y, x - 1)) {
                        naapurit[i] = getKoordinaatti(y + dY, x - 1);
                        i++;
                    }
                    if (voikoKulkea(y + dY, x) && !voikoKulkea(y, x + 1)) {
                        naapurit[i] = getKoordinaatti(y + dY, x + 1);
                        i++;
                    }
                }
            }

        } else {
            naapurit = lisaaKaikki(x, y);
        }
        return naapurit;
    }

    private Koordinaatti jump(int y, int x, int dY, int dX) {

        int nextX = x + dX;
        int nextY = y + dY;
        

        if (!voikoKulkea(nextY, nextX)) {
            
            return null;
        }
        
      

        if (super.onkoMaali(nextY, nextX)) {
            return super.getKoordinaatti(nextY, nextX);
        }
        //Diagonaali
        
        if (dX != 0 && dY != 0) {
            if ((voikoKulkea(y + 1, x + dX) && !voikoKulkea(y + 1, x))
                    || voikoKulkea(y - 1, x + dX) && !voikoKulkea(y - 1, x)) {
                return getKoordinaatti(nextY, nextX);
            }
            if ((voikoKulkea(y + dY, x + 1) && !voikoKulkea(y, x + 1))
                    || voikoKulkea(y + dY, x - 1) && !voikoKulkea(y, x - 1)) {
                return getKoordinaatti(nextY, nextX);
            }
            if (jump(nextY, nextX, dY, 0) != null
                    || jump(nextY, nextX, 0, dX) != null) {
                return getKoordinaatti(nextY, nextX);
            }
            

        } else {
            
            if (dX != 0) {
                if ((voikoKulkea(y + 1, nextX) && !voikoKulkea(y, nextX))
                        || (voikoKulkea(y - 1, nextX) && !voikoKulkea(y, nextX))) {
                    return getKoordinaatti(nextY, nextX);
                }
            } else {
                if ((voikoKulkea(nextY, x + 1) && !voikoKulkea(nextY, x))
                        || (voikoKulkea(nextY, x - 1) && !voikoKulkea(nextY, x))) {
                    return getKoordinaatti(nextY, nextX);

                }
            }
            
            // tarkistaa diagonaalisesti liikuttaessa vertikaaliset ja
            // horisonttaaliset jumppointit.

        } //Vertikaalinen
        return jump(nextY, nextX, dY, dX);

    }

    private Koordinaatti[] lisaaKaikki(int x, int y) {
        Koordinaatti[] naapurit = new Koordinaatti[8];
        int i = 0;
        if (voikoKulkea(y + 1, x)) {
            naapurit[i] = getKoordinaatti(y + 1, x);
            i++;
        }
        if (voikoKulkea(y + 1, x + 1)) {
            naapurit[i] = getKoordinaatti(y + 1, x + 1);
            i++;
        }
        if (voikoKulkea(y + 1, x - 1)) {
            naapurit[i] = getKoordinaatti(y + 1, x - 1);
            i++;
        }
        if (voikoKulkea(y - 1, x)) {
            naapurit[i] = getKoordinaatti(y - 1, x);
            i++;
        }
        if (voikoKulkea(y - 1, x + 1)) {
            naapurit[i] = getKoordinaatti(y - 1, x + 1);
            i++;
        }
        if (voikoKulkea(y - 1, x - 1)) {
            naapurit[i] = getKoordinaatti(y - 1, x - 1);
            i++;
        }
        if (voikoKulkea(y, x - 1)) {
            naapurit[i] = getKoordinaatti(y, x - 1);
            i++;
        }
        if (voikoKulkea(y, x + 1)) {
            naapurit[i] = getKoordinaatti(y, x + 1);
            i++;
        }

        return naapurit;
    }

    private int laskeSuunta(int pX, int cX) {
        int dX = cX - pX;
        if (dX > 0) {
            return 1;
        } else if (dX < 0) {
            return -1;
        }
        return 0;
    }
}
