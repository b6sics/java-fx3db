/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gepnaplo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import panel.Panel;

/**
 *
 * @author kjg
 */
public class DB {

    String db, user, pass;

    //final String db = "jdbc:mysql://192.168.127.102:3306/gepnaplo"
    //final String db = "jdbc:mysql://localhost:3306/gepnaplo"
    //        + "?useUnicode=true&characterEncoding=UTF-8";
    //final String user = "tanulo";
    //final String pass = "tanulo";
    public DB() {
        try (Scanner be = new Scanner(new File("gepnaplo2.ini"))) {
            db = "jdbc:mysql://" + be.nextLine().split("=")[1]
                    + ":3306/gepnaplo?useUnicode=true&characterEncoding=UTF-8";
            user = be.nextLine().split("=")[1];
            pass = "tanar" + be.nextLine().split("=")[1];
        } catch (IOException ex) {
            Panel.hiba("Beállítások: ", ex.getMessage());
            Platform.exit();
        }
    }

    public int beir(String iskola, String osztaly,
            String nev, String allapot) {
        String p = "INSERT INTO gepek (gepnev,iskola,osztaly,nev,ido,allapot)"
                + " VALUES (?,?,?,?,?,?);";
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(p)) {
            ekp.setString(1, Seged.gepnev());
            ekp.setString(2, iskola);
            ekp.setString(3, osztaly);
            ekp.setString(4, nev);
            ekp.setString(5, Seged.datumido());
            ekp.setString(6, allapot);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Kiiratás: ", ex.getMessage());
            return 0;
        }
    }

    public int torol() {
        final String p = "DELETE FROM gepek WHERE DATEDIFF(NOW(),ido)>30;";
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(p)) {
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Törlés: ", ex.getMessage());
            return 0;
        }
    }

    public void beolvas(ObservableList<Bejelentkezes> lista, String p) {
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(p);
                ResultSet eredmeny = ekp.executeQuery();) {

            lista.clear();
            while (eredmeny.next()) {
                lista.add(new Bejelentkezes(
                        eredmeny.getString("gepnev"),
                        eredmeny.getString("ido"),
                        eredmeny.getString("nev"),
                        eredmeny.getString("allapot"),
                        eredmeny.getString("osztaly"),
                        eredmeny.getString("iskola")));
                System.out.println(eredmeny.getString("gepnev"));
            }
        } catch (SQLException ex) {
            Panel.hiba("Beolvasás: ", ex.getMessage());
        }
    }
}
