package kozoskoltseg;

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
 * @author Joe
 */
public class DB {

    final String db = "jdbc:mysql://localhost:3306/tarsashaz"
            + "?useUnicode=true&characterEncoding=UTF-8";
    final String user = "kepviselo";
    final String pass = "kepviselo";

    public void koltseg_be(ObservableList<Dij> tabla) {
        String s = "SELECT * FROM koltseg ORDER BY datum;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ResultSet eredmeny = ekp.executeQuery();
            tabla.clear();
            while (eredmeny.next()) {
                tabla.add(new Dij(
                        eredmeny.getInt("koltsegid"),
                        eredmeny.getString("datum"),
                        eredmeny.getInt("nmdij")
                ));
            }
        } catch (SQLException ex) {
            Panel.hiba("Költség beolvasás", ex.getMessage());
        }
    }

    public void lakas_be(ObservableList<Lakas> tabla,
            ObservableList<String> lista) {
        String s = "SELECT * FROM lakasok ORDER BY lakasid;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ResultSet eredmeny = ekp.executeQuery();
            tabla.clear();
            lista.clear();
            while (eredmeny.next()) {
                tabla.add(new Lakas(
                        eredmeny.getString("lakasid"),
                        eredmeny.getInt("terulet"),
                        eredmeny.getString("nev")
                ));
                lista.add(eredmeny.getString("lakasid"));
            }
        } catch (SQLException ex) {
            Panel.hiba("Lakás beolvasás", ex.getMessage());
        }
    }

    public void befizetes_be(ObservableList<Befizetes> tabla) {
        String s = "SELECT * FROM befizetesek "
                + "ORDER BY datum, lakasid;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ResultSet eredmeny = ekp.executeQuery();
            tabla.clear();
            while (eredmeny.next()) {
                tabla.add(new Befizetes(
                        eredmeny.getInt("befizid"),
                        eredmeny.getString("lakasid"),
                        eredmeny.getString("datum"),
                        eredmeny.getInt("osszeg")
                ));
            }
        } catch (SQLException ex) {
            Panel.hiba("Befizetés beolvasás", ex.getMessage());
        }
    }

    public int dij_hozzaad(LocalDate datum, int nmdij) {
        String s = "INSERT INTO `koltseg` (`datum`,`nmdij`) "
                + "VALUES(?,?);";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, datum.toString());
            ekp.setInt(2, nmdij);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Díj hozzáadás: ", ex.getMessage());
            return 0;
        }
    }

    public int lakas_hozzaad(String lakasid, int terulet, String nev) {
        String s = "INSERT INTO `lakasok` (`lakasid`,`terulet`,`nev`) "
                + "VALUES(?,?,?);";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, lakasid);
            ekp.setInt(2, terulet);
            ekp.setString(3, nev);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Lakás hozzáadás: ", ex.getMessage());
            return 0;
        }
    }

    public int befizet_hozzaad(String lakasid, LocalDate datum, int osszeg) {
        String s = "INSERT INTO `befizetesek` (`lakasid`,`datum`,`osszeg`) "
                + "VALUES(?,?,?);";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, lakasid);
            ekp.setString(2, datum.toString());
            ekp.setInt(3, osszeg);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Befizetés hozzáadás: ", ex.getMessage());
            return 0;
        }
    }

    public int lakas_modosit(String lakasid, int terulet, String nev) {
        String s = "UPDATE `lakasok` SET `terulet` = ?, `nev` = ? "
                + "WHERE `lakasid` = ?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, terulet);
            ekp.setString(2, nev);
            ekp.setString(3, lakasid);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Lakás módosítás: ", ex.getMessage());
            return 0;
        }
    }

    public int dij_torol(int koltsegid) {
        String s = "DELETE FROM `koltseg` WHERE `koltsegid` = ?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, koltsegid);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Díj törlés: ", ex.getMessage());
            return 0;
        }
    }

    public int lakas_torol(int lakasid) {
        String s = "DELETE FROM `lakasok` WHERE `lakasid` = ?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, lakasid);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Lakás törlés: ", ex.getMessage());
            return 0;
        }
    }

    public int befizet_torol(int befizid) {
        String s = "DELETE FROM `befizetesek` WHERE `befizid` = ?;";

        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setInt(1, befizid);
            return ekp.executeUpdate();
        } catch (SQLException ex) {
            Panel.hiba("Díj törlés: ", ex.getMessage());
            return 0;
        }
    }
}
