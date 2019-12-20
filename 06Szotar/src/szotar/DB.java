/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szotar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import panel.Panel;

/**
 *
 * @author kjg
 */
public class DB {

    final String dbUrl0 = "jdbc:mysql://localhost:3306/";
    final String db = "jdbc:mysql://localhost:3306/szotar"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "root";
    final String pass = "";

    public void beolvas(ObservableList<Szo> tabla, String p) {
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(p);
                ResultSet eredmeny = ekp.executeQuery();) {
            System.out.println("starting...");
            tabla.clear();
            while (eredmeny.next()) {
                tabla.add(new Szo(
                        eredmeny.getInt("szoID"),
                        eredmeny.getString("lecke"),
                        eredmeny.getString("angol"),
                        eredmeny.getString("magyar")));
            }
            System.out.println("db loaded...");
        } catch (SQLException ex) {
            Panel.hiba("Hiba: ", ex.getMessage());
            Platform.exit();
        }
    }

}
