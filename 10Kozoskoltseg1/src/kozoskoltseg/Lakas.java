package kozoskoltseg;

/**
 *
 * @author Joe
 */
public class Lakas {

    private String lakasid;
    private int terulet;
    private String nev;

    public Lakas(String lakasid, int terulet, String nev) {
        this.lakasid = lakasid;
        this.terulet = terulet;
        this.nev = nev;
    }

    public String getLakasid() {
        return lakasid;
    }

    public void setLakasid(String lakasid) {
        this.lakasid = lakasid;
    }

    public int getTerulet() {
        return terulet;
    }

    public void setTerulet(int terulet) {
        this.terulet = terulet;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

}
