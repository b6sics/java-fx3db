/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futas;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import panel.Panel;

/**
 *
 * @author t1
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Edzes> tblEdzesek;

    @FXML
    private TableColumn<Edzes, String> oDatum;

    @FXML
    private TableColumn<Edzes, Integer> oTav;

    @FXML
    private TableColumn<Edzes, Integer> oIdo;

    @FXML
    private DatePicker dpDatum;

    @FXML
    private TextField txtTav;

    @FXML
    private TextField txtIdo;

    @FXML
    private Label lblStat;

    @FXML
    void hozzaad(ActionEvent event) {
        LocalDate d = dpDatum.getValue();
        if (d == null || d.isAfter(LocalDate.now())) {
            Panel.hiba("Hiba!", "Hibás dátum!");
            dpDatum.requestFocus();
            return;
        }
        String s = txtTav.getText();
        int tav;
        try {
            tav = Integer.parseInt(s);
            if (tav < 100 || tav > 50000) {
                Panel.hiba("Hiba!", "Hibás táv!");
                txtTav.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "Hibás táv!");
            txtTav.requestFocus();
            return;
        }
        s = txtIdo.getText();
        int ido;
        try {
            ido = Integer.parseInt(s);
            if (tav < 10) {
                Panel.hiba("Hiba!", "Hibás idő!");
                txtIdo.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "Hibás idő!");
            txtIdo.requestFocus();
            return;
        }
        int sor = ab.hozzaad(d.toString(), tav, ido);
        if (sor > 0) {
            beolvas();
            uj();
        }
    }

    @FXML
    void modosit() {
        int index = tblEdzesek.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        int id = tblEdzesek.getItems().get(index).getFutasID();

        LocalDate d = dpDatum.getValue();
        if (d == null || d.isAfter(LocalDate.now())) {
            Panel.hiba("Hiba!", "Hibás dátum!");
            dpDatum.requestFocus();
            return;
        }
        String s = txtTav.getText();
        int tav;
        try {
            tav = Integer.parseInt(s);
            if (tav < 100 || tav > 50000) {
                Panel.hiba("Hiba!", "Hibás táv!");
                txtTav.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "Hibás táv!");
            txtTav.requestFocus();
            return;
        }
        s = txtIdo.getText();
        int ido;
        try {
            ido = Integer.parseInt(s);
            if (tav < 10) {
                Panel.hiba("Hiba!", "Hibás idő!");
                txtIdo.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "Hibás idő!");
            txtIdo.requestFocus();
            return;
        }
        int sor = ab.modosit(id, d.toString(), tav, ido);
        if (sor > 0) {
            beolvas();
            for (int i = 0; i < tblEdzesek.getItems().size(); i++) {
                if (tblEdzesek.getItems().get(i).getFutasID() == id) {
                    tblEdzesek.getSelectionModel().select(i);
                    break;
                }
            }
        }
    }

    @FXML
    void torol() {
        int index = tblEdzesek.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Biztosan törölni szeretnéd ezt a futást?")) {
            return;
        }
        int id = tblEdzesek.getItems().get(index).getFutasID();
        int sor = ab.torol(id);
        if (sor > 0) {
            beolvas();
        }
    }

    @FXML
    void uj() {
        dpDatum.setValue(LocalDate.now());
        dpDatum.requestFocus();
        txtTav.clear();
        txtIdo.clear();
        tblEdzesek.getSelectionModel().select(null);
    }

    DB ab = new DB();

    private String opm(int t) {
        return String.format("%02d:%02d:%02d", t / 3600, (t % 3600) / 60, t % 60);
    }

    private void beolvas() {
        ab.beolvas(tblEdzesek.getItems());
        int tav = 0;
        int ido = 0;
        int db = 0;
        for (Edzes e : tblEdzesek.getItems()) {
            tav += e.getTav();
            ido += e.getIdo();
            db++;
        }
        lblStat.setText("Futások száma:" + db
                + "\nÖsszes táv: " + tav + " m"
                + "\nÖsszes idő: " + opm(ido));
    }

    private void tablabol(int i) {
        if (i == -1) {
            return;
        }
        Edzes e = tblEdzesek.getItems().get(i);
        dpDatum.setValue(LocalDate.parse(e.getDatum()));
        txtTav.setText("" + e.getTav());
        txtIdo.setText("" + e.getIdo());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        oTav.setCellValueFactory(new PropertyValueFactory<>("tav"));
        oIdo.setCellValueFactory(new PropertyValueFactory<>("ido"));
        beolvas();
        tblEdzesek.getSelectionModel().selectedIndexProperty().addListener(
                (o, regi, uj) -> tablabol(uj.intValue()));
    }

}
