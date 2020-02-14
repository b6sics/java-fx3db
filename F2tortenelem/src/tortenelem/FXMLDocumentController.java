/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortenelem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import panel.Panel;

/**
 *
 * @author b6dmin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Evszam> tblEvek;

    @FXML
    private TableColumn<Evszam, String> oEv;

    @FXML
    private TableColumn<Evszam, String> oEsemeny;

    @FXML
    private TextField txtKeres;

    @FXML
    private TextField txtEv;

    @FXML
    private TextField txtEsemeny;

    @FXML
    void szuro_torol() {
        txtKeres.setText("");
        beolvas();
    }

    @FXML
    void hozzaad() {
        String s = txtEv.getText();
        int ev;
        try {
            ev = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "Hibás év!");
            txtEv.requestFocus();
            return;
        }
        String esemeny = txtEsemeny.getText();
        if (esemeny.length() > 80) {
            Panel.hiba("Hiba!",
                    "Az esemény leírása legfeljebb 80 karakter hosszú lehet!");
            txtEsemeny.requestFocus();
            return;
        }
        if (esemeny.isEmpty()) {
            Panel.hiba("Hiba!",
                    "Az esemény leírása hiányzik!");
            txtEsemeny.requestFocus();
            return;
        }
        int sor = ab.hozzaad(ev, esemeny);
        if (sor > 0) {
            beolvas();
            uj();
        }
    }

    @FXML
    void modosit() {
        int index = tblEvek.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        int id = tblEvek.getItems().get(index).getId();

        String s = txtEv.getText();
        int ev;
        try {
            ev = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            Panel.hiba("Hiba!", "Hibás év!");
            txtEv.requestFocus();
            return;
        }
        String esemeny = txtEsemeny.getText();
        if (esemeny.length() > 80) {
            Panel.hiba("Hiba!",
                    "Az esemény leírása legfeljebb 80 karakter hosszú lehet!");
            txtEsemeny.requestFocus();
            return;
        }
        if (esemeny.isEmpty()) {
            Panel.hiba("Hiba!",
                    "Az esemény leírása hiányzik!");
            txtEsemeny.requestFocus();
            return;
        }
        int sor = ab.modosit(id, ev, esemeny);
        if (sor > 0) {
            beolvas();
            for (int i = 0; i < tblEvek.getItems().size(); i++) {
                if (tblEvek.getItems().get(i).getId() == id) {
                    tblEvek.getSelectionModel().select(i);
                    break;
                }
            }
        }
    }

    @FXML
    void torol() {
        int index = tblEvek.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés",
                "Biztosan törölni szeretnéd ezt az eseményt?")) {
            return;
        }
        int id = tblEvek.getItems().get(index).getId();
        int sor = ab.torol(id);
        if (sor > 0) {
            beolvas();
        }
    }

    @FXML
    void uj() {
        txtEv.requestFocus();
        txtEv.clear();
        txtEsemeny.clear();
        tblEvek.getSelectionModel().select(null);
    }

    DB ab = new DB();

    private void beolvas() {
        String s = "SELECT * FROM evszamok "
                + "WHERE esemeny LIKE '%" + txtKeres.getText() + "%' "
                + "ORDER BY ev;";
        ab.beolvas(tblEvek.getItems(), s);
    }

    private void tablabol(int index) {
        if (index == -1) {
            return;
        }
        Evszam e = tblEvek.getItems().get(index);
        txtEv.setText("" + e.getEv());
        txtEsemeny.setText("" + e.getEsemeny());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oEv.setCellValueFactory(new PropertyValueFactory<>("ev"));
        oEsemeny.setCellValueFactory(new PropertyValueFactory<>("esemeny"));
        beolvas();
        txtKeres.textProperty().addListener((o, regi, uj) -> beolvas());
        tblEvek.getSelectionModel().selectedIndexProperty().addListener(
                (o, regi, uj) -> tablabol(uj.intValue()));
    }
}
