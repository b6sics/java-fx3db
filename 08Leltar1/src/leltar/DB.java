/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leltar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import panel.Panel;

/**
 *
 * @author t1
 */
public class DB {

    final String db = "jdbc:mysql://localhost:3306/nyilvantartas"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "raktaros";
    final String pass = "raktaros";

    public DB() {
    }

    public void terem_be(ObservableList<Terem> tabla,
            ObservableList<String> lista) {
        String s = "SELECT * FROM termek ORDER BY terem";
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            tabla.clear();
            lista.clear();
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                tabla.add(new Terem(
                        eredmeny.getInt("teremid"),
                        eredmeny.getString("terem"),
                        eredmeny.getString("felhasznalas")
                ));
                lista.add(eredmeny.getString("terem"));
            }
        } catch (SQLException ex) {
            Panel.hiba("Hiba: Terem beolvasás.", ex.getMessage());
        }
    }

    public void eszkoz_be(ObservableList<Eszkoz> tabla,
            ObservableList<String> lista) {
        String s = "SELECT * FROM eszkozok ORDER BY nev";
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            tabla.clear();
            lista.clear();
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                tabla.add(new Eszkoz(
                        eredmeny.getInt("eszkozid"),
                        eredmeny.getString("nev"),
                        eredmeny.getString("jellemzok")
                ));
                lista.add(eredmeny.getString("nev"));
            }
        } catch (SQLException ex) {
            Panel.hiba("Hiba: Eszköz beolvasás.", ex.getMessage());
        }
    }

    public void leltar_be(ObservableList<Tetel> tabla) {
        String s = "SELECT leltarid,terem,nev,ar,ev,megjegyzes "
                + "FROM leltar "
                + "JOIN eszkozok ON leltar.eszkozid=eszkozok.eszkozid "
                + "JOIN termek ON leltar.teremid=termek.teremid "
                + "ORDER BY terem, nev, leltarid;";
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            tabla.clear();
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                Tetel t = new Tetel();
                t.setID(eredmeny.getInt("leltarid"));
                t.setTerem(eredmeny.getString("terem"));
                t.setEszkoz(eredmeny.getString("nev"));
                int ar = eredmeny.getInt("ar");
                if (!eredmeny.wasNull()) {
                    t.setAr(ar);
                }
                int ev = eredmeny.getInt("ev");
                if (!eredmeny.wasNull()) {
                    t.setEv(ev);
                }
                t.setMegjegyzes(eredmeny.getString("megjegyzes"));
                tabla.add(t);
            }
        } catch (SQLException ex) {
            Panel.hiba("Hiba: Leltár beolvasás.", ex.getMessage());
        }
    }
}
