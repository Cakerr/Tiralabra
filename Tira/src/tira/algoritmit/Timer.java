/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.algoritmit;

public class Timer {
    private long start = 0;
    private long end = 0;
    public Timer(){
        
    }
    
    public void start(){
        start = System.currentTimeMillis();
    }
    public void stop(){
        end = System.currentTimeMillis();
    }
    
    public long time(){
        return end - start;
    }
    
    public void reset(){
        start = 0;
        end = 0;
    }
    
    
    
}
