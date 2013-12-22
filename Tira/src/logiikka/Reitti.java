
package logiikka;

public class Reitti{
    
    private ReittiNode lahto;
    private ReittiNode maali;
    private ReittiNode next;
    private int pituus;
    
    public Reitti(Koordinaatti lahto) {
        ReittiNode vali = new ReittiNode(lahto);
        this.lahto = vali;
        this.maali = vali;
        next = this.lahto;
        pituus = 1;
    }
    
    public Reitti(){
        lahto = null;
        maali = null;
        next = null;
        pituus = 0;
    }
    
    public Koordinaatti next(){
        if (next == null){
            return null;
        }
        Koordinaatti palautus = next.getKoordinaatti();
        next = next.getNext();
        return palautus;
    }
    
    public void resetNext(){
        next = lahto;
    }
    
    public int length(){
        return pituus;
    }
    
    public void lisaaNode(Koordinaatti node){
        ReittiNode vali = new ReittiNode(node);
        if (lahto == null && maali == null){
            maali = vali;
            lahto = vali;
            next = lahto;
            pituus++;
            return;
        }
        maali.setNext(vali);
        maali = vali;
        pituus++;
    }

    
}
