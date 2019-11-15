/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author t1
 */
public class FXMLDocumentController implements Initializable {

    DB ab = new DB();

    @FXML
    private TableView<Koltseg> tblKiadasok;

    @FXML
    private TableColumn<Koltseg, String> oKiadas;

    @FXML
    private TableColumn<Koltseg, Integer> oAr;

    @FXML
    private TableColumn<Koltseg, String> oDatum;

    @FXML
    private TableColumn<Koltseg, Integer> oKm;

    @FXML
    private TableColumn<Koltseg, String> oMegjegyzes;

    @FXML
    private Label lblOsszesen;

    @FXML
    void modosit() throws Exception {
        int i = tblKiadasok.getSelectionModel().getSelectedIndex();
        if (i > -1) {
            ablak("Módosítás", i);
        }
    }

    @FXML
    void torol() {

    }

    @FXML
    void uj() throws Exception {
        ablak("Új költség", -1);
    }

    private void szamol() {
        int osszeg = 0;
        for (Koltseg k : tblKiadasok.getItems()) {
            osszeg += k.getAr();
        }
        lblOsszesen.setText("Összesen:\n" + osszeg + " Ft");
    }

    public void ablak(String cim, int i) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ablak.fxml"));
        Parent root = loader.load();

        AblakController ac = loader.getController();
        ac.setDB(ab);
        if (i > -1) {
            ac.setKoltseg(tblKiadasok.getItems().get(i));
        } else {
            ac.setKoltseg(new Koltseg(
                    0, "tanlolás", 0, LocalDate.now().toString(), 0, ""));
        }
        Scene scene = new Scene(root);
        Stage ablak = new Stage();
        ablak.setResizable(false);
        ablak.initModality(Modality.APPLICATION_MODAL);
        ablak.setScene(scene);
        ablak.setTitle(cim);
        ablak.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oKiadas.setCellValueFactory(new PropertyValueFactory<>("kiadas"));
        oAr.setCellValueFactory(new PropertyValueFactory<>("ar"));
        oDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        oKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        oMegjegyzes.setCellValueFactory(new PropertyValueFactory<>("megjegyzes"));
        ab.betolt(tblKiadasok.getItems());
        szamol();
    }

}
