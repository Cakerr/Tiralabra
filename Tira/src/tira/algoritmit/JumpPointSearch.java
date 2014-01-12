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

    /**
     *
     */
    public JumpPointSearch() {
        super();
    }

    /**
     *
     * @param kasiteltava
     */
    @Override
    public void lisaaSeuraajatKekoon(Koordinaatti kasiteltava) {
        Koordinaatti[] naapurit = tunnistaNaapurit(kasiteltava);


       


        for (int i = 0; i < naapurit.length; i++) {

            Koordinaatti naapuri = naapurit[i];
            if (naapuri != null && !naapuri.getKayty()) {

                int dX;
                int dY;
                naapurit[i].setKayty(true);


                dX = laskeSuunta(kasiteltava.getX(), naapuri.getX());
                dY = laskeSuunta(kasiteltava.getY(), naapuri.getY());



                Koordinaatti jumpPoint = jump(kasiteltava.getY(), kasiteltava.getX(), dY, dX);
                if (jumpPoint != null) {
                    System.out.println(jumpPoint.getY() + " " + jumpPoint.getX());
                    asetaEtaisyysAlkuun(jumpPoint, kasiteltava);
                    jumpPoint.setKeossa(true);
                    jumpPoint.setEdellinen(kasiteltava);
                    getKeko().add(jumpPoint);
                }
            }
        }
    }

    private Koordinaatti jump(int y, int x, int dY, int dX) {

        if (!voikoKulkea(y, x)) {
            return null;
        }
        int nextX = x + dX;
        int nextY = y + dY;
        if (!voikoKulkea(nextY, nextX)) {
            return null;
        }

        if (onkoMaali(nextY, nextX)) {

            return getKoordinaatti(nextY, nextX);
        }
        //Diagonaali

        if (dX != 0 && dY != 0) {


            if ((voikoKulkea(nextY - dY, nextX + dX) && !voikoKulkea(nextY - dY, nextX))) {
                return getKoordinaatti(nextY, nextX);
            }
            if ((voikoKulkea(nextY + dY, nextX - dX) && !voikoKulkea(nextY, nextX - dX))) {
                return getKoordinaatti(nextY, nextX);
            }
            if (jump(nextY, nextX, 0, dX) != null || jump(nextY, nextX, dY, 0) != null) {
                return getKoordinaatti(nextY, nextX);
            }

        } else {

            if (dX != 0) {
                if ((voikoKulkea(nextY + 1, nextX + dX) && !voikoKulkea(y + 1, nextX))) {
                    return getKoordinaatti(nextY, nextX);
                }
                if (voikoKulkea(nextY - 1, nextX + dX) && !voikoKulkea(nextY - 1, nextX)) {
                    return getKoordinaatti(nextY, nextX);
                }

            } else {
                if ((voikoKulkea(nextY + dY, x + 1) && !voikoKulkea(nextY, x + 1))) {
                    return getKoordinaatti(nextY, nextX);
                }
                if (voikoKulkea(nextY + dY, x - 1) && !voikoKulkea(nextY, x - 1)) {
                    return getKoordinaatti(nextY, nextX);
                }
            }

            // tarkistaa diagonaalisesti liikuttaessa vertikaaliset ja
            // horisonttaaliset jumppointit.

        } //Vertikaalinen
        return jump(nextY, nextX, dY, dX);

    }

    private Koordinaatti[] tunnistaNaapurit(Koordinaatti node) {

        int x = node.getX();
        int y = node.getY();
        int i = 0;

        Koordinaatti[] naapurit = new Koordinaatti[5];
        if (node.getEdellinen() != null) {
            int pX = node.getEdellinen().getX();
            int pY = node.getEdellinen().getY();
            int dX = laskeSuunta(pX, x);
            int dY = laskeSuunta(pY, y);

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

                if (!voikoKulkea(y, x - dX) && voikoKulkea(y + dY, x - dX)) {
                    naapurit[i] = getKoordinaatti(y + dY, x - dX);
                    i++;
                }

                if (!voikoKulkea(y - dY, x) && voikoKulkea(y - dY, x + dX)) {
                    naapurit[i] = getKoordinaatti(y - dY, x + dX);
                    i++;
                }
            } else {
                //vaakasiirtymä
                if (dY == 0) {
                    if (voikoKulkea(y, x + dX)) {
                        naapurit[i] = getKoordinaatti(y, x + dX);
                        i++;
                    }
                    if (!voikoKulkea(y + 1, x) && voikoKulkea(y + 1, x + dX)) {
                        naapurit[i] = getKoordinaatti(y + 1, x + dX);
                        i++;
                    }
                    if (!voikoKulkea(y - 1, x) && voikoKulkea(y - 1, x + dX)) {
                        naapurit[i] = getKoordinaatti(y - 1, x + dX);
                        i++;
                    }
                }
                //pystysiirtymä
                if (dX == 0) {
                    if (voikoKulkea(y + dY, x)) {
                        naapurit[i] = getKoordinaatti(y + dY, x);
                        i++;
                    }
                    if (!voikoKulkea(y, x + 1) && voikoKulkea(y + dY, x + 1)) {
                        naapurit[i] = getKoordinaatti(y + dY, x + 1);
                        i++;
                    }
                    if (!voikoKulkea(y, x - 1) && voikoKulkea(y + dY, x - 1)) {
                        naapurit[i] = getKoordinaatti(y + dY, x - 1);
                        i++;
                    }
                }
            }

        } else {
            naapurit = lisaaKaikki(x, y);
        }
        return naapurit;
    }

    private Koordinaatti[] lisaaKaikki(int x, int y) {
        Koordinaatti[] naapurit = new Koordinaatti[8];
        int i = 0;
        if (voikoKulkea(y + 1, x + 1)) {
            naapurit[i] = getKoordinaatti(y + 1, x + 1);
            i++;
        }
        if (voikoKulkea(y + 1, x - 1)) {
            naapurit[i] = getKoordinaatti(y + 1, x - 1);
            i++;
        }
        if (voikoKulkea(y - 1, x - 1)) {
            naapurit[i] = getKoordinaatti(y - 1, x - 1);
            i++;
        }
        if (voikoKulkea(y - 1, x + 1)) {
            naapurit[i] = getKoordinaatti(y - 1, x + 1);
            i++;
        }
        if (voikoKulkea(y - 1, x)) {
            naapurit[i] = getKoordinaatti(y - 1, x);
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
        if (voikoKulkea(y - 1, x)) {
            naapurit[i] = getKoordinaatti(y - 1, x);
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

    @Override
    public void tulostaReitti(Koordinaatti maali, Reitti reitti) {
        if (maali.getEdellinen() != null) {
            tulostaReitti(maali.getEdellinen(), reitti);

        }
        reitti.lisaaNode(maali);
    }
}
