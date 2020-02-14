/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futas;

/**
 *
 * @author t1
 */
public class Edzes {

    private int futasID;
    private String datum;
    private int tav, ido;

    public int getFutasID() {
        return futasID;
    }

    public void setFutasID(int futasID) {
        this.futasID = futasID;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getTav() {
        return tav;
    }

    public void setTav(int tav) {
        this.tav = tav;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public Edzes(int futasID, String datum, int tav, int ido) {
        this.futasID = futasID;
        this.datum = datum;
        this.tav = tav;
        this.ido = ido;
    }
}
