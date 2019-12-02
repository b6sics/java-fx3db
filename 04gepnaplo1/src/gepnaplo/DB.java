/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gepnaplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author t1
 */
public class DB {

    //final String db = "jdbc:mysql://192.168.127.101:3306/gepnaplo"
    final String db = "jdbc:mysql://localhost:3306/gepnaplo"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "tanulo";
    final String pass = "tanulo";

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
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
