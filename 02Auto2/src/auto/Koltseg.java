/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

/**
 *
 * @author g
 */
public class Koltseg {

    private int az;
    private String kiadas;
    private int ar;
    private String datum;
    private int km;
    private String megjegyzes;

    public int getAz() {
        return az;
    }

    public void setAz(int az) {
        this.az = az;
    }

    public String getKiadas() {
        return kiadas;
    }

    public void setKiadas(String kiadas) {
        this.kiadas = kiadas;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
    }

    public Koltseg(int az, String kiadas, int ar, String datum, int km, String megjegyzes) {
        this.az = az;
        this.kiadas = kiadas;
        this.ar = ar;
        this.datum = datum;
        this.km = km;
        this.megjegyzes = megjegyzes;
    }
}
