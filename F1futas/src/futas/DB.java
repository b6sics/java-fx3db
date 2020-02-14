/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import panel.Panel;

/**
 *
 * @author t1
 */
public class DB {

    final String db = "jdbc:mysql://localhost:3306/futas"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "futo";
    final String pass = "futo";

    public void beolvas(ObservableList<Edzes> tabla) {
        String s = "SELECT * FROM naplo ORDER BY datum;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ResultSet eredmeny = ekp.executeQuery();
            tabla.clear();
            while (eredmeny.next()) {
                tabla.add(new Edzes(
                        eredmeny.getInt("futasID"),
                        eredmeny.getString("datum"),
                        eredmeny.getInt("tav"),
                        eredmeny.getInt("ido")
                ));
            }
        } catch (SQLException ex) {
            Panel.hiba("Hiba!", ex.getMessage());
        }
    }

    public int hozzaad(String datum, int tav, int ido) {
        String s = "INSERT INTO naplo (datum,tav,ido) "
                + "VALUES(?,?,?);";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, datum);
            ekp.setInt(2, tav);
            ekp.setInt(3, ido);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Hiba!", ex.getMessage());
            return 0;
        }
    }

    public int modosit(int id, String datum, int tav, int ido) {
        String s = "UPDATE naplo SET datum=?, tav=?, ido=? "
                + "WHERE futasID=?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, datum);
            ekp.setInt(2, tav);
            ekp.setInt(3, ido);
            ekp.setInt(4, id);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Hiba!", ex.getMessage());
            return 0;
        }
    }

    public int torol(int id) {
        String s = "DELETE FROM naplo WHERE futasID=?;";

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
