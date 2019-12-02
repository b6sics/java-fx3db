/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gepnaplo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author t1
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ComboBox<String> cbxIskola;

    @FXML
    private TextField txtOsztaly;

    @FXML
    private TextField txtNev;

    @FXML
    private TextField txtAllapot;

    @FXML
    void kesz(ActionEvent event) {
        String iskola = cbxIskola.getValue().trim();
        if (iskola.length() < 1 || iskola.length() > 40) {
            cbxIskola.requestFocus();
            return;
        }
        String osztaly = txtOsztaly.getText().trim();

        if (osztaly.length() < 1 || osztaly.length() > 8) {
            txtOsztaly.requestFocus();
            return;
        }
        String nev = txtNev.getText().trim();

        if (nev.length() < 1 || nev.length() > 50) {
            txtNev.requestFocus();
            return;
        }
        String allapot = txtAllapot.getText().trim();

        if (allapot.length() < 1 || allapot.length() > 50) {
            txtAllapot.requestFocus();
            return;
        }
        DB ab = new DB();
        if (ab.beir(iskola, osztaly, nev, allapot) > 0) {
            Platform.exit();
        }
    }

    @FXML
    void bill(KeyEvent event) {
        if (event.getCode() == KeyCode.X && event.isAltDown()) {
            Platform.exit();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        cbxIskola.getItems().add("BKSZC Pogány Frigyes Szakgimnáziuma");
        cbxIskola.getItems().add("Vörösmarty Mihály Gimnázium");
        cbxIskola.getItems().add("Weöres Sándor Általános iskola");
        cbxIskola.getSelectionModel().select(0);
    }
}
