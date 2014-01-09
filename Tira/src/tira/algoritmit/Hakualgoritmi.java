
package tira.algoritmit;

import tira.logiikka.Kartta;
import tira.tietorakenteet.Reitti;

public interface Hakualgoritmi {
    
    public Reitti findPath(Kartta kartta);
    public long suoritusaika();
}
