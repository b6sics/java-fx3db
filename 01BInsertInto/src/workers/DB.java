/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workers;

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

/**
 *
 * @author g
 */
public class DB {

    final String dbUrl1 = "jdbc:mysql://localhost:3306/";
    final String dbUrl2 = "jdbc:mysql://localhost:3306/dolgozok"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "root";
    final String pass = "";

    public DB() {
        String s1 = "CREATE DATABASE IF NOT EXISTS dolgozok "
                + "DEFAULT CHARSET=utf8mb4 "
                + "COLLATE=utf8mb4_hungarian_ci;";
        String s2 = "USE dolgozok;";
        String s3 = "CREATE TABLE IF NOT EXISTS adatok ("
                + "id int(4) NOT NULL AUTO_INCREMENT,"
                + "nev varchar(50),"
                + "szulido date,"
                + "fizetes int(7),"
                + "PRIMARY KEY(id)"
                + ");";
        try (Connection kapcs = DriverManager.getConnection(dbUrl1, user, pass);
                PreparedStatement ekp1 = kapcs.prepareStatement(s1);
                PreparedStatement ekp2 = kapcs.prepareStatement(s2);
                PreparedStatement ekp3 = kapcs.prepareStatement(s3)) {
            ekp1.execute();
            ekp2.execute();
            ekp3.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void lista() {
        String s = "SELECT * FROM adatok";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                System.out.printf("%2d %-50s %s %10d\n",
                        eredmeny.getInt("id"),
                        eredmeny.getString("nev"),
                        eredmeny.getString("szulido"),
                        eredmeny.getInt("fizetes"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void uj(String nev, String szulido, int fizetes) {
        String s = "INSERT INTO adatok (nev, szulido, fizetes) "
                + "VALUES (?, ?, ?);";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, nev);
            ekp.setString(2, szulido);
            ekp.setInt(3, fizetes);
            ekp.executeUpdate();
            System.out.println(nev + " hozzáadva.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void beolvas(String fnev) {
        try (Scanner be = new Scanner(new File(fnev))) {
            while (be.hasNextLine()) {
                String[] sor = be.nextLine().split(",");
                uj(sor[0], sor[1], Integer.parseInt(sor[2]));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void kiir(String fnev) {
        try (PrintWriter ki = new PrintWriter(fnev)) {
            try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                    PreparedStatement ekp = kapcs.prepareStatement("SELECT * FROM adatok;")) {
                ResultSet eredmeny = ekp.executeQuery();
                while (eredmeny.next()) {
                    ki.println(eredmeny.getString("nev") + ","
                            + eredmeny.getString("szulido") + ","
                            + eredmeny.getInt("fizetes"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void emel(int szazalek) {
        String s = "UPDATE adatok SET fizetes = (1+?/100)*fizetes;";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, szazalek);
            int sorok = ekp.executeUpdate();
            System.out.println(sorok + " sor módosítva.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void torol() {
        String s = "DELETE FROM adatok;";
        try (Connection kapcs = DriverManager.getConnection(dbUrl2, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            int sorok = ekp.executeUpdate();
            System.out.println(sorok + " sor törölve");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
