/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortenelem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import panel.Panel;

/**
 *
 * @author b6dmin
 */
public class DB {

    final String db = "jdbc:mysql://localhost:3306/tortenelem"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "tanulo";
    final String pass = "tanulo";

    public void beolvas(ObservableList<Evszam> tabla, String s) {

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ResultSet eredmeny = ekp.executeQuery();
            tabla.clear();
            while (eredmeny.next()) {
                Evszam e = new Evszam(
                        eredmeny.getInt("evID"),
                        eredmeny.getInt("ev"),
                        eredmeny.getString("esemeny"));
                tabla.add(e);
                System.out.println("> " + e.getEv() + ": " + e.getEsemeny());
            }
        } catch (SQLException ex) {
            Panel.hiba("Hiba!", ex.getMessage());
        }
    }

    public int hozzaad(int ev, String esemeny) {
        String s = "INSERT INTO evszamok (ev,esemeny) "
                + "VALUES(?,?);";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, ev);
            ekp.setString(2, esemeny);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Hiba!", ex.getMessage());
            return 0;
        }
    }

    public int modosit(int id, int ev, String esemeny) {
        String s = "UPDATE evszamok SET ev=?, esemeny=? "
                + "WHERE evID=?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, ev);
            ekp.setString(2, esemeny);
            ekp.setInt(3, id);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Hiba!", ex.getMessage());
            return 0;
        }
    }

    public int torol(int id) {
        String s = "DELETE FROM evszamok WHERE evID=?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, id);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Hiba!", ex.getMessage());
            return 0;
        }
    }
}
