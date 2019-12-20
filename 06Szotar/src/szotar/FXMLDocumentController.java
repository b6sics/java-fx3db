/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szotar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author kjg
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Tab tabSzotar;

    @FXML
    private TextField txtLeckeSzuro;

    @FXML
    private TextField txtAngolszuro;

    @FXML
    private TextField txtMagyarSzuro;

    @FXML
    private TableView<Szo> tblszavak;

    @FXML
    private TableColumn<Szo, String> oLecke;

    @FXML
    private TableColumn<Szo, String> oAngol;

    @FXML
    private TableColumn<Szo, String> oMagyar;

    @FXML
    private TextField txtLecke;

    @FXML
    private TextField txtAngol;

    @FXML
    private TextField txtMagyar;

    @FXML
    private ComboBox<String> cbxValaszt;

    @FXML
    private Button btnIndit;

    @FXML
    private Label lblFelso;

    @FXML
    private Label lblAlso;

    @FXML
    private Button btntudtam;

    @FXML
    private Button btnNemTudtam;

    @FXML
    void indit(ActionEvent event) {

    }

    @FXML
    void modosit(ActionEvent event) {

    }

    @FXML
    void mutat(MouseEvent event) {

    }

    @FXML
    void nem_tudtam(ActionEvent event) {

    }

    @FXML
    void szuro_torol(ActionEvent event) {
        txtLeckeSzuro.clear();
        txtAngolszuro.clear();
        txtMagyarSzuro.clear();
        tblszavak.requestFocus();
    }

    @FXML
    void torol(ActionEvent event) {

    }

    @FXML
    void tudtam(ActionEvent event) {

    }

    @FXML
    void uj(ActionEvent event) {

    }

    @FXML
    void hozzaad(ActionEvent event) {

    }

    DB ab = new DB();

    private void beolvas() {
        String szuro1 = "'%" + txtLeckeSzuro.getText() + "%'";
        String szuro2 = "'%" + txtAngolszuro.getText() + "%'";
        String szuro3 = "'%" + txtMagyarSzuro.getText() + "%'";

        String s = "SELECT * FROM szavak "
                + "WHERE lecke LIKE " + szuro1
                + "AND angol LIKE " + szuro2
                + "AND magyar LIKE " + szuro3
                + " ORDER BY angol;";
        ab.beolvas(tblszavak.getItems(), s);
    }

    private void tablabol(int i) {
        if (i == -1) {
            return;
        }
        Szo sz = tblszavak.getItems().get(i);
        txtLecke.setText("" + sz.getLecke());
        txtAngol.setText("" + sz.getAngol());
        txtMagyar.setText("" + sz.getMagyar());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        beolvas();
        oLecke.setCellValueFactory(new PropertyValueFactory<>("lecke"));
        oAngol.setCellValueFactory(new PropertyValueFactory<>("angol"));
        oMagyar.setCellValueFactory(new PropertyValueFactory<>("magyar"));

        txtLeckeSzuro.textProperty().addListener((o, regi, uj) -> beolvas());
        txtAngolszuro.textProperty().addListener((o, regi, uj) -> beolvas());
        txtMagyarSzuro.textProperty().addListener((o, regi, uj) -> beolvas());

        tblszavak.getSelectionModel().selectedIndexProperty().
                addListener((o, regi, uj)
                        -> tablabol(uj.intValue()));
    }
}
