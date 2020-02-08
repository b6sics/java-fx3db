package kozoskoltseg;

/**
 *
 * @author Joe
 */
public class Befizetes {
    private int befizid;
    private String lakasid;
    private String datum;
    private int osszeg;

    public Befizetes(int befizid, String lakasid, String datum, int osszeg) {
        this.befizid = befizid;
        this.lakasid = lakasid;
        this.datum = datum;
        this.osszeg = osszeg;
    }

    public int getBefizid() {
        return befizid;
    }

    public void setBefizid(int befizid) {
        this.befizid = befizid;
    }

    public String getLakasid() {
        return lakasid;
    }

    public void setLakasid(String lakasid) {
        this.lakasid = lakasid;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg = osszeg;
    }

}
