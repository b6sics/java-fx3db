/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

/**
 *
 * @author tg
 */
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AblakController implements Initializable {

    @FXML
    private TextField txtAr;

    @FXML
    private TextField txtKm;

    @FXML
    private TextField txtMegjegyzes;

    @FXML
    private ComboBox<String> cbxKiadas;

    @FXML
    private DatePicker dpDatum;

    private int az;
    private DB ab;

    public void setDB(DB ab) {
        this.ab = ab;
    }

    public void setKoltseg(Koltseg k) {
        this.az = k.getAz();
        cbxKiadas.setValue(k.getKiadas());
        txtAr.setText("" + k.getAr());
        dpDatum.setValue(LocalDate.parse(k.getDatum()));
        txtKm.setText("" + k.getKm());
        txtMegjegyzes.setText(k.getMegjegyzes());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxKiadas.getItems().addAll(
                "tankolás",
                "biztosítás",
                "javítás",
                "parkolás",
                "egyéb");
    }
}
