/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.algoritmit;

/**
 * 
 * @author Ari
 */
public class Timer {
    private long start = 0;
    private long end = 0;
    /**
     * 
     */
    public Timer(){
        
    }
    
    /**
     * Käynnistää kellon
     */
    public void start(){
        start = System.currentTimeMillis();
    }
    /**
     * Pysäyttää kellon
     */
    public void stop(){
        end = System.currentTimeMillis();
    }
    
    /**
     * palauttaa timerin ajan
     * @return
     */
    public long time(){
        return end - start;
    }
    
    /**
     * Asettaa start ja end arvot nollaan
     */
    public void reset(){
        start = 0;
        end = 0;
    }
    
    
    
}
