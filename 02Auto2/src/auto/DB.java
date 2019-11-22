/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javafx.collections.ObservableList;
import panel.Panel;

/**
 *
 * @author g
 */
public class DB {

    final String dbUrl1 = "jdbc:mysql://localhost:3306/";
    final String dbUrl2 = "jdbc:mysql://localhost:3306/auto"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "root";
    final String pass = "";

    public DB() {
        String s1 = "CREATE DATABASE IF NOT EXISTS auto "
                + "DEFAULT CHARSET=utf8mb4 "
                + "COLLATE=utf8mb4_hungarian_ci;";
        String s2 = "USE auto;";
        String s3 = "CREATE TABLE IF NOT EXISTS kiadasok ("
                + "az int(4) NOT NULL AUTO_INCREMENT,"
                + "kiadas varchar(50),"
                + "ar int(7),"
                + "datum date,"
                + "km int(7),"
                + "megjegyzes varchar(255),"
                + "PRIMARY KEY(az)"
                + ");";
        try (Connection kapcs = DriverManager.getConnection(dbUrl1, user, pass);
                PreparedStatement ekp1 = kapcs.prepareStatement(s1);
                PreparedStatement ekp2 = kapcs.prepareStatement(s2);
                PreparedStatement ekp3 = kapcs.prepareStatement(s3)) {
            ekp1.execute();
            ekp2.execute();
            ekp3.execute();
        } catch (SQLException ex) {
            Panel.hiba("Létrehozás", ex.getMessage());
        }
    }

    public void betolt(ObservableList<Koltseg> lista) {
        String p = "SELECT * FROM kiadasok ORDER BY datum, kiadas";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(p)) {
            ResultSet eredmeny = ekp.executeQuery();
            lista.clear();
            while (eredmeny.next()) {
                lista.add(new Koltseg(
                        eredmeny.getInt("az"),
                        eredmeny.getString("kiadas"),
                        eredmeny.getInt("ar"),
                        eredmeny.getString("datum"),
                        eredmeny.getInt("km"),
                        eredmeny.getString("megjegyzes")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void uj(String kiadas, int ar,
            String datum, int km, String megjegyzes) {
        String s = "INSERT INTO kiadasok (kiadas, ar, datum, km, megjegyzes) "
                + "VALUES (?, ?, ?, ?, ?);";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, kiadas);
            ekp.setInt(2, ar);
            ekp.setString(3, datum);
            ekp.setInt(4, km);
            ekp.setString(5, megjegyzes);
            ekp.executeUpdate();
            System.out.println(megjegyzes + " hozzáadva.");
        } catch (SQLException ex) {
            Panel.hiba("Új esemény", ex.getMessage());
        }
    }

    public void modosit(int az, String kiadas, int ar,
            String datum, int km, String megjegyzes) {
        String s = "UPDATE kiadasok SET kiadas=?, ar=?,"
                + " datum=?, km=?, megjegyzes=? "
                + "WHERE az = ?;";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, kiadas);
            ekp.setInt(2, ar);
            ekp.setString(3, datum);
            ekp.setInt(4, km);
            ekp.setString(5, megjegyzes);
            ekp.setInt(6, az);
            ekp.executeUpdate();
            System.out.println(megjegyzes + " módosítva.");
        } catch (SQLException ex) {
            Panel.hiba("Módosítás", ex.getMessage());
        }
    }

    public void torol(int az) {
        String s = "DELETE FROM kiadasok WHERE az=?;";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, az);
            int sor = ekp.executeUpdate();
            System.out.println(sor + ". sor törölve");
        } catch (SQLException ex) {
            Panel.hiba("Törlés", ex.getMessage());
        }
    }
}
