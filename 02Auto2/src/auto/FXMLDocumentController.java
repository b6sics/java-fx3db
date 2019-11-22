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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import panel.Panel;

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
            int az = tblKiadasok.getItems().get(i).getAz();
            ablak("Módosítás", i);
            ab.betolt(tblKiadasok.getItems());
            kijelol(az);
            szamol();
        }
        tblKiadasok.requestFocus();
    }

    private void kijelol(int az) {
        for (int i = 0; i < tblKiadasok.getItems().size(); i++) {
            Koltseg k = tblKiadasok.getItems().get(i);
            if (k.getAz() == az) {
                tblKiadasok.getSelectionModel().select(i);
                return;
            }
        }
    }

    @FXML
    void torol() {
        int i = tblKiadasok.getSelectionModel().getSelectedIndex();
        if (i > -1) {
            if (!Panel.igennem("Törlés", "Törölni szeretnéd ezt a sort?")) {
                return;
            }
            int az = tblKiadasok.getItems().get(i).getAz();
            ab.torol(az);
            ab.betolt(tblKiadasok.getItems());
            int utolso = tblKiadasok.getItems().size() - 1;
            if (i < utolso) {
                tblKiadasok.getSelectionModel().select(i);
            } else {
                tblKiadasok.getSelectionModel().select(i - 1);
            }
            szamol();
        }
        tblKiadasok.requestFocus();
    }

    @FXML
    void uj() throws Exception {
        int i = tblKiadasok.getSelectionModel().getSelectedIndex();
        int hossz = tblKiadasok.getItems().size();
        ablak("Új költség", -1);
        ab.betolt(tblKiadasok.getItems());
        if (tblKiadasok.getItems().size() > hossz) {
            int max = 0;
            for (Koltseg k : tblKiadasok.getItems()) {
                if (k.getAz() > max) {
                    max = k.getAz();
                }
            }
            kijelol(max);
            szamol();
        } else {
            tblKiadasok.getSelectionModel().select(i);
        }
        tblKiadasok.requestFocus();
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
                    0, "tankolás", 0, LocalDate.now().toString(), 0, ""));
        }
        Scene scene = new Scene(root);
        Stage ablak = new Stage();
        ablak.setResizable(false);
        ablak.initModality(Modality.APPLICATION_MODAL);
        ablak.setScene(scene);
        ablak.setTitle(cim);
        ablak.getIcons().add(new Image("car.png"));
        ablak.showAndWait();
    }

    @FXML
    void bill(KeyEvent event) throws Exception {
        KeyCode kod = event.getCode();
        if (kod == KeyCode.INSERT) {
            uj();
        }
        if (kod == KeyCode.DELETE) {
            torol();
        }
        if (kod == KeyCode.ENTER) {
            modosit();
        }
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
