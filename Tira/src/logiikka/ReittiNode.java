
package logiikka;

public class ReittiNode {
    
    private Koordinaatti koordinaatti;
    private ReittiNode next = null;
    
    public ReittiNode(Koordinaatti koordinaatti){
        this.koordinaatti = koordinaatti;
    }
    
    public void setNext(ReittiNode next){   
        this.next = next;
    }
    
    public ReittiNode getNext(){
        return next;
    }
    public Koordinaatti getKoordinaatti(){
        return koordinaatti;
    }
    
}
