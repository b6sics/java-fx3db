/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortenelem;

/**
 *
 * @author b6dmin
 */
public class Evszam {

    private int id;
    private int ev;
    private String esemeny;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEv() {
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public String getEsemeny() {
        return esemeny;
    }

    public void setEsemeny(String esemeny) {
        this.esemeny = esemeny;
    }

    public Evszam(int id, int ev, String esemeny) {
        this.id = id;
        this.ev = ev;
        this.esemeny = esemeny;
    }
}
