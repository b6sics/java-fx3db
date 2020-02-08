package kozoskoltseg;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import panel.Panel;

/**
 *
 * @author Joe
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Dij> tblDij;

    @FXML
    private TableColumn<Dij, String> oDatum;

    @FXML
    private TableColumn<Dij, Integer> oDij;

    @FXML
    private DatePicker dpKezdet;

    @FXML
    private TextField txtHavidij;

    @FXML
    private TextField txtOsszes;

    @FXML
    private TableView<Lakas> tblLakas;

    @FXML
    private TableColumn<Lakas, String> oLakas;

    @FXML
    private TableColumn<Lakas, String> oNev;

    @FXML
    private TableColumn<Lakas, Integer> oTerulet;

    @FXML
    private TextField txtLakas;

    @FXML
    private TextField txtNev;

    @FXML
    private TextField txtTerulet;

    @FXML
    private TextField txtBefizetes;

    @FXML
    private TextField txtEgyenleg;

    @FXML
    private TableView<Befizetes> tblBefizet;

    @FXML
    private TableColumn<Befizetes, String> oLakas2;

    @FXML
    private TableColumn<Befizetes, String> oDatum2;

    @FXML
    private TableColumn<Befizetes, Integer> oOsszeg;

    @FXML
    private ComboBox<String> cbxLakas;

    @FXML
    private DatePicker dpDatum;

    @FXML
    private TextField txtOsszeg;

    @FXML
    void befizet_hozzaad() {
        String lakasid = cbxLakas.getValue();
        if (lakasid == null) {
            Panel.hiba("Hiba!", "Válaszd ki a lakás azonosítóját!");
            cbxLakas.requestFocus();
            return;
        }

        LocalDate datum = dpDatum.getValue();
        if (datum == null) {
            Panel.hiba("Hiba!", "Add meg a dátumot!");
            dpDatum.requestFocus();
            return;
        }
        if (datum.isAfter(LocalDate.now())) {
            Panel.hiba("Hiba!", "A dátum nem lehet a mainál későbbi!");
            dpDatum.requestFocus();
            return;
        }

        int osszeg;
        try {
            osszeg = Integer.parseInt(txtOsszeg.getText());
            if (osszeg < 0) {
                Panel.hiba("Hiba!", "A befizetés nem lehet negatív");
                txtOsszeg.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "A befizetés nem szám");
            return;
        }

        int sor = ab.befizet_hozzaad(lakasid, datum, osszeg);
        if (sor > 0) {
            ab.befizetes_be(tblBefizet.getItems());
            lakas_tablabol(tblLakas.getSelectionModel().getSelectedIndex());
        }
        tblLakas.requestFocus();
    }

    @FXML
    void dij_hozzaad() {
        LocalDate kezdet = dpKezdet.getValue();
        if (kezdet == null) {
            Panel.hiba("Hiba!", "Add meg a kezdő dátumot!");
            dpKezdet.requestFocus();
            return;
        }
        if (kezdet.getDayOfMonth() != 1) {
            Panel.hiba("Hiba!", "A díj csak 1-én változhat!");
            dpKezdet.requestFocus();
            return;
        }

        String s = txtHavidij.getText();
        if (s.isEmpty()) {
            Panel.hiba("Hiba!", "Add meg a díjat!");
            txtHavidij.requestFocus();
            return;
        }

        int dij;
        try {
            dij = Integer.parseInt(txtHavidij.getText());
            if (dij < 0) {
                Panel.hiba("Hiba!", "A díj nem lehet negatív");
                txtHavidij.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "A díj nem szám");
            return;
        }

        int sor = ab.dij_hozzaad(kezdet, dij);
        if (sor > 0) {
            ab.koltseg_be(tblDij.getItems());
            for (int i = 0; i < tblDij.getItems().size(); i++) {
                if (tblDij.getItems().get(i).getDatum().
                        equals(kezdet.toString())) {
                    tblDij.getSelectionModel().select(i);
                    break;
                }
            }
            osszes = osszes_dij();
            txtOsszes.setText("" + osszes);
            lakas_tablabol(tblLakas.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void lakas_hozzaad() {
        String lakasid = txtLakas.getText();
        if (lakasid.length() < 1 || lakasid.length() > 3) {
            Panel.hiba("Hiba!", "A lakásid 1-3 karakter hosszú lehet!");
            txtLakas.requestFocus();
            return;
        }

        String nev = txtNev.getText();
        if (nev.length() < 1 || nev.length() > 50) {
            Panel.hiba("Hiba!", "A név 1-50 karakter hosszú lehet!");
            txtLakas.requestFocus();
            return;
        }

        int terulet;
        try {
            terulet = Integer.parseInt(txtTerulet.getText());
            if (terulet < 20) {
                Panel.hiba("Hiba!", "A terület nem lehet 20-nál kisebb!");
                txtTerulet.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "A terület nem szám");
            return;
        }

        int sor = ab.lakas_hozzaad(lakasid, terulet, nev);
        if (sor > 0) {
            ab.lakas_be(tblLakas.getItems(), cbxLakas.getItems());
            for (int i = 0; i < tblLakas.getItems().size(); i++) {
                if (tblLakas.getItems().get(i).getLakasid().equals(lakasid)) {
                    tblLakas.getSelectionModel().select(i);
                    break;
                }
            }
        }
        tblLakas.requestFocus();
    }

    @FXML
    void lakas_modosit() {
        String lakasid = txtLakas.getText();
        if (lakasid.length() < 1 || lakasid.length() > 3) {
            Panel.hiba("Hiba!", "A lakásid 1-3 karakter hosszú lehet!");
            txtLakas.requestFocus();
            return;
        }

        String nev = txtNev.getText();
        if (nev.length() < 1 || nev.length() > 50) {
            Panel.hiba("Hiba!", "A név 1-50 karakter hosszú lehet!");
            txtLakas.requestFocus();
            return;
        }

        int terulet;
        try {
            terulet = Integer.parseInt(txtTerulet.getText());
            if (terulet < 20) {
                Panel.hiba("Hiba!", "A terület nem lehet 20-nál kisebb!");
                txtTerulet.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "A terület nem szám");
            return;
        }

        int sor = ab.lakas_modosit(lakasid, terulet, nev);
        if (sor > 0) {
            ab.lakas_be(tblLakas.getItems(), cbxLakas.getItems());
            for (int i = 0; i < tblLakas.getItems().size(); i++) {
                if (tblLakas.getItems().get(i).getLakasid().equals(lakasid)) {
                    tblLakas.getSelectionModel().select(i);
                    break;
                }
            }
        }
        tblLakas.requestFocus();
    }

    @FXML
    void dij_torol() {
        int index = tblDij.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Biztosan törölni akarod ezt a sort?")) {
            return;
        }
        int id = tblDij.getItems().get(index).getKoltsegid();
        int sor = ab.dij_torol(id);
        if (sor > 0) {
            ab.koltseg_be(tblDij.getItems());
            osszes = osszes_dij();
            txtOsszes.setText("" + osszes);
            lakas_tablabol(tblLakas.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void lakas_torol() {
        int index = tblLakas.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Biztosan törölni akarod ezt a sort?")) {
            return;
        }
        String lakasid = tblLakas.getItems().get(index).getLakasid();
        int sor = ab.lakas_torol(index);
        if (sor > 0) {
            ab.lakas_be(tblLakas.getItems(), cbxLakas.getItems());
        }
    }

    @FXML
    void befizet_torol() {
        int index = tblBefizet.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Biztosan törölni akarod ezt a sort?")) {
            return;
        }
        int id = tblBefizet.getItems().get(index).getBefizid();
        int sor = ab.befizet_torol(index);
        if (sor > 0) {
            ab.befizetes_be(tblBefizet.getItems());
            lakas_tablabol(tblLakas.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void lakas_uj() {
        txtLakas.clear();;
        txtNev.clear();
        txtTerulet.clear();
        txtLakas.requestFocus();
        tblLakas.getSelectionModel().select(null);
    }

    private String lakas_adatok(int i) {
        String s = "";
        Lakas lak = tblLakas.getItems().get(i);
        s += lak.getLakasid() + ";" + lak.getNev();
        s += ";" + lak.getTerulet() + ";";
        int befizetes = osszes_befizetes(lak.getLakasid());
        s += befizetes + ";";
        s += (befizetes - osszes * lak.getTerulet());
        return s;
    }

    @FXML
    void export() {
        FileChooser fv = new FileChooser();
        fv.setTitle("Exportálás");
        FileChooser.ExtensionFilter szuro
                = new FileChooser.ExtensionFilter("CSV fájl", "*.csv");
        fv.getExtensionFilters().add(szuro);
        fv.setInitialDirectory(new File("."));
        File f = fv.showSaveDialog(null);
        if (f != null) {
            try (PrintWriter ki = new PrintWriter(f.getAbsoluteFile(), "cp1250")) {
                ki.println("LakásId;Név;Terület;Befizetés;Egyenleg");
                ObservableList<Lakas> lista = tblLakas.getItems();
                for (int i = 0; i < lista.size(); i++) {
                    ki.println(lakas_adatok(i));
                }
            } catch (IOException e) {
                Panel.hiba("Hiba!", e.getMessage());
            }
        }
    }

    DB ab = new DB();
    int osszes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        oDij.setCellValueFactory(new PropertyValueFactory<>("nmdij"));

        oLakas.setCellValueFactory(new PropertyValueFactory<>("lakasid"));
        oNev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        oTerulet.setCellValueFactory(new PropertyValueFactory<>("terulet"));

        oLakas2.setCellValueFactory(new PropertyValueFactory<>("lakasid"));
        oDatum2.setCellValueFactory(new PropertyValueFactory<>("datum"));
        oOsszeg.setCellValueFactory(new PropertyValueFactory<>("osszeg"));

        ab.koltseg_be(tblDij.getItems());
        ab.lakas_be(tblLakas.getItems(), cbxLakas.getItems());
        ab.befizetes_be(tblBefizet.getItems());

        tblLakas.getSelectionModel().selectedIndexProperty().addListener(
                (o, regi, uj) -> lakas_tablabol(uj.intValue())
        );

//        System.out.println(dij(LocalDate.of(2017,2,5)));
//        System.out.println(dij(LocalDate.of(2018,2,6)));
//        System.out.println(dij(LocalDate.of(2019,3,5)));
        osszes = osszes_dij();
        txtOsszes.setText("" + osszes);
    }

    private int dij(LocalDate datum) {
        ObservableList<Dij> lista = tblDij.getItems();
        int i = 0;
        while (i < lista.size() - 1
                && !LocalDate.parse(lista.get(i + 1).getDatum()).isAfter(datum)) {
            i++;
        }
        return lista.get(i).getNmdij();
    }

    private int osszes_dij() {
        LocalDate ma = LocalDate.now();
        LocalDate d = LocalDate.parse(tblDij.getItems().get(0).getDatum());
        int osszeg = 0;
        while (d.isBefore(ma)) {
            osszeg += dij(d);
            d = d.plusMonths(1);
        }
        return osszeg;
    }

    private void lakas_tablabol(int i) {
        if (i == -1) {
            return;
        }
        Lakas lak = tblLakas.getItems().get(i);
        txtLakas.setText(lak.getLakasid());
        txtNev.setText(lak.getNev());
        txtTerulet.setText("" + lak.getTerulet());
        int befizetes = osszes_befizetes(lak.getLakasid());
        txtBefizetes.setText("" + befizetes);
        txtEgyenleg.setText("" + (befizetes - osszes * lak.getTerulet()));
    }

    private int osszes_befizetes(String lakasid) {
        ObservableList<Befizetes> lista = tblBefizet.getItems();
        int osszeg = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getLakasid().equals(lakasid)) {
                osszeg += lista.get(i).getOsszeg();
            }
        }
        return osszeg;
    }
}
