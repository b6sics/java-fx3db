/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gepnaplo;

/**
 *
 * @author t1
 */
public class Bejelentkezes {

    private String gepnev;
    private String ido;
    private String nev;
    private String allapot;
    private String osztaly;
    private String iskola;

    public String getGepnev() {
        return gepnev;
    }

    public void setGepnev(String gepnev) {
        this.gepnev = gepnev;
    }

    public String getIdo() {
        return ido;
    }

    public void setIdo(String ido) {
        this.ido = ido;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getAllapot() {
        return allapot;
    }

    public void setAllapot(String allapot) {
        this.allapot = allapot;
    }

    public String getOsztaly() {
        return osztaly;
    }

    public void setOsztaly(String osztaly) {
        this.osztaly = osztaly;
    }

    public String getIskola() {
        return iskola;
    }

    public void setIskola(String iskola) {
        this.iskola = iskola;
    }

    public Bejelentkezes(String gepnev, String ido, String nev,
            String allapot, String osztaly, String iskola) {
        this.gepnev = gepnev;
        this.ido = ido;
        this.nev = nev;
        this.allapot = allapot;
        this.osztaly = osztaly;
        this.iskola = iskola;
    }
}
