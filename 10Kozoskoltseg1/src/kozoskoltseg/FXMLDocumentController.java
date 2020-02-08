/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kozoskoltseg;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 *
 * @author t1
 */
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Dij> tblDij;

    @FXML
    private TableColumn<Dij, String> oDatum;

    @FXML
    private TableColumn<Dij, String> oDij;

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
    private TableColumn<Lakas, String> oTerulet;

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
    private TableColumn<Befizetes, String> oOsszeg;

    @FXML
    private ComboBox<String> cbxLakas;

    @FXML
    private DatePicker dpDatum;

    @FXML
    private TextField txtOsszeg;

    @FXML
    void befizet_hozzaad(ActionEvent event) {

    }

    @FXML
    void befizet_torol(ActionEvent event) {

    }

    @FXML
    void dij_hozzaad(ActionEvent event) {

    }

    @FXML
    void dij_torol(ActionEvent event) {

    }

    @FXML
    void export(ActionEvent event) {

    }

    @FXML
    void lakas_hozzaad(ActionEvent event) {

    }

    @FXML
    void lakas_modosit(ActionEvent event) {

    }

    @FXML
    void lakas_torol(ActionEvent event) {

    }

    @FXML
    void lakas_uj(ActionEvent event) {

    }

    DB ab = new DB();

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

    int osszes;

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
                (o, regi, uj) -> lakas_tablabol(uj.intValue()));

        osszes = osszes_dij();
        txtOsszes.setText("" + osszes);
    }
}
