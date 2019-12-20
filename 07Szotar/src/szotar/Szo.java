package szotar;

/**
 *
 * @author Joe
 */
public class Szo {
    private int szoID;
    private String lecke;
    private String angol;
    private String magyar;

    public Szo(int szoID, String lecke, String angol, String magyar) {
        this.szoID = szoID;
        this.lecke = lecke;
        this.angol = angol;
        this.magyar = magyar;
    }

    public int getSzoID() {
        return szoID;
    }

    public void setSzoID(int szoID) {
        this.szoID = szoID;
    }

    public String getLecke() {
        return lecke;
    }

    public void setLecke(String lecke) {
        this.lecke = lecke;
    }

    public String getAngol() {
        return angol;
    }

    public void setAngol(String angol) {
        this.angol = angol;
    }

    public String getMagyar() {
        return magyar;
    }

    public void setMagyar(String magyar) {
        this.magyar = magyar;
    }
    
}

