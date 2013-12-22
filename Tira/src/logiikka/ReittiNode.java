
package logiikka;

/**
 * 
 * @author Ari
 */
public class ReittiNode {
    
    private Koordinaatti koordinaatti;
    private ReittiNode next = null;
    
    /**
     * 
     * @param koordinaatti
     */
    public ReittiNode(Koordinaatti koordinaatti){
        this.koordinaatti = koordinaatti;
    }
    
    /**
     * Asettaa nodelle seuraajan
     * @param next
     */
    public void setNext(ReittiNode next){   
        this.next = next;
    }
    
    /**
     * Palauttaa noden seuraajan
     * @return
     */
    public ReittiNode getNext(){
        return next;
    }
    /**
     * palauttaa nodeen liitetyn koordinaatti-olion.
     * @return
     */
    public Koordinaatti getKoordinaatti(){
        return koordinaatti;
    }
    
}
