/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gepnaplo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import panel.Panel;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField txtGepszuro;

    @FXML
    private ComboBox<String> cbxIdo;

    @FXML
    private TextField txtNevszuro;

    @FXML
    private CheckBox chkProb;

    @FXML
    private TableView<Bejelentkezes> tblGepek;

    @FXML
    private TableColumn<Bejelentkezes, String> oGep;

    @FXML
    private TableColumn<Bejelentkezes, String> oIdo;

    @FXML
    private TableColumn<Bejelentkezes, String> oNev;

    @FXML
    private TableColumn<Bejelentkezes, String> oAllapot;

    @FXML
    private TableColumn<Bejelentkezes, String> oOsztaly;

    @FXML
    private TableColumn<Bejelentkezes, String> oIskola;

    @FXML
    void torol() {
        if (Panel.igennem("Törlés",
                "Le szeretnéd törölni a régi bejelentkezéseket?")) {
            int sor = ab.torol();
            Panel.hiba("Törlés: ", sor + " sor törölve");
            ab.beolvas(tblGepek.getItems(), lekerdez());
        }
    }

    DB ab = new DB();

    public String lekerdez() {
        String q = "";
        if (!txtGepszuro.getText().isEmpty()) {
            q += " gepnev LIKE '" + txtGepszuro.getText() + "' AND ";
        }
        switch (cbxIdo.getSelectionModel().getSelectedIndex()) {
            case 0:
                q += " TIMEDIFF(NOW(),ido)<'00:45' AND ";
                break;
            case 1:
                q += " DATE(ido)=DATE(NOW()) AND ";
                break;
            case 2:
                q += " DATEDIFF(NOW(),ido)<=7 AND ";
                break;
            case 3:
                q += " DATEDIFF(NOW(),ido)<=30 AND ";
                break;
            case 4:

                break;
        }
        if (!txtNevszuro.getText().isEmpty()) {
            q += " nev LIKE '" + txtNevszuro.getText() + "' AND ";
        }
        if (chkProb.isSelected()) {
            q += " allapot NOT LIKE 'Rendben%'";
        } else {
            q += " allapot LIKE '%'";
        }
        q = "SELECT gepnev,iskola,osztaly,nev,ido,allapot "
                + "FROM gepek WHERE " + q + " ORDER BY ido DESC;";
        // q = "SELECT * FROM gepek";
        System.out.println(q);
        return q;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxIdo.getItems().addAll("Ezen az órán", "Ma", "7 napja", "30 napja", "Mind");
        cbxIdo.setValue("Mind");

        oGep.setCellValueFactory(new PropertyValueFactory<>("gepnev"));
        oIdo.setCellValueFactory(new PropertyValueFactory<>("ido"));
        oNev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        oAllapot.setCellValueFactory(new PropertyValueFactory<>("allapot"));
        oOsztaly.setCellValueFactory(new PropertyValueFactory<>("osztaly"));
        oIskola.setCellValueFactory(new PropertyValueFactory<>("iskola"));

        ab.beolvas(tblGepek.getItems(), lekerdez());

        txtGepszuro.textProperty().addListener((o, regi, uj)
                -> ab.beolvas(tblGepek.getItems(), lekerdez()));
        cbxIdo.valueProperty().addListener((o, regi, uj)
                -> ab.beolvas(tblGepek.getItems(), lekerdez()));
        txtNevszuro.textProperty().addListener((o, regi, uj)
                -> ab.beolvas(tblGepek.getItems(), lekerdez()));
        chkProb.selectedProperty().addListener((o, regi, uj)
                -> ab.beolvas(tblGepek.getItems(), lekerdez()));
    }
}
