/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boox;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author g
 */
public class DB {

    final String dbUrl = "jdbc:mysql://localhost:3306/konyvek";
    final String user = "root";
    final String pass = "";

    public void kiir(int ev) {
        String p = "SELECT * FROM adatok WHERE ev = ?";
        try (Connection kapcs
                = (Connection) DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement ekp
                = (PreparedStatement) kapcs.prepareStatement(p)) {
            ekp.setInt(1, ev);
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                System.out.printf("%2d %-30s %-35s %-35s %d\n",
                        eredmeny.getInt("id"),
                        eredmeny.getString("szerzo"),
                        eredmeny.getString("cim"),
                        eredmeny.getString("eredeti"),
                        eredmeny.getInt("ev"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void uj(String szerzo, String cim, String eredeti, int ev) {
        String p = "INSERT INTO adatok (szerzo,cim,eredeti,ev) VALUES (?,?,?,?);";
        try (Connection kapcs = (Connection) DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement ekp = (PreparedStatement) kapcs.prepareStatement(p)) {
            ekp.setString(1, szerzo);
            ekp.setString(2, cim);
            ekp.setString(3, eredeti);
            ekp.setInt(4, ev);
            int sorok = ekp.executeUpdate();
            System.out.println(sorok + " sor hozzáadva.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void torol(int id) {
        String p = "DELETE FROM adatok WHERE id= ?;";
        try (Connection kapcs
                = (Connection) DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement ekp
                = (PreparedStatement) kapcs.prepareStatement(p)) {
            ekp.setInt(1, id);
            int sorok = ekp.executeUpdate();
            System.out.println(sorok + " sor törölve");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
