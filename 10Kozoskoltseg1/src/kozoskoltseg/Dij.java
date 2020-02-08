package kozoskoltseg;

/**
 *
 * @author Joe
 */
public class Dij {

    private int koltsegid;
    private String datum;
    private int nmdij;

    public Dij(int koltsegid, String datum, int nmdij) {
        this.koltsegid = koltsegid;
        this.datum = datum;
        this.nmdij = nmdij;
    }

    public int getKoltsegid() {
        return koltsegid;
    }

    public void setKoltsegid(int koltsegid) {
        this.koltsegid = koltsegid;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getNmdij() {
        return nmdij;
    }

    public void setNmdij(int nmdij) {
        this.nmdij = nmdij;
    }

}
