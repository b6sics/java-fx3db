package szotar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import static panel.Panel.hiba;

/**
 *
 * @author Joe
 */
public class DB {

    final String db = "jdbc:mysql://localhost:3306/szotar"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "tanulo";
    final String pass = "tanulo";

    public void beolvas(ObservableList<Szo> tabla, String s) {
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            tabla.clear();
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                tabla.add(new Szo(
                        eredmeny.getInt("szoId"),
                        eredmeny.getString("lecke"),
                        eredmeny.getString("angol"),
                        eredmeny.getString("magyar")
                ));
            }
        } catch (SQLException ex) {
            hiba("Hiba!", ex.getMessage());
            Platform.exit();
        }
    }

    public int hozzaad(String lecke, String angol, String magyar) {
        String s = "INSERT INTO szavak (lecke, angol, magyar) "
                + "VALUES1?,?,?);";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, lecke);
            ekp.setString(2, angol);
            ekp.setString(3, magyar);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            hiba("Hozzáadás", ex.getMessage());
            return 0;
        }
    }

    public int modosit(int id, String lecke, String angol, String magyar) {
        String s = "UPDATE szavak SET lecke=?, angol=?, magyar=? "
                + "WHERE szoID=?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, lecke);
            ekp.setString(2, angol);
            ekp.setString(3, magyar);
            ekp.setInt(4, id);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            hiba("Módosítás", ex.getMessage());
            return 0;
        }
    }

    public int torol(int id) {
        String s = "DELETE FROM szavak WHERE szoID=?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, id);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            hiba("Módosítás", ex.getMessage());
            return 0;
        }
    }
}
