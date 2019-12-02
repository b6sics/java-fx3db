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
import javafx.stage.Window;
import panel.Panel;

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

    @FXML
    private void megse() {
        Window ablak = txtKm.getScene().getWindow();
        ablak.hide();
    }

    @FXML
    private void ok() {
        int ar = 0, km;
        try {
            ar = Integer.parseInt(txtAr.getText());
            if (ar < 0) {
                Panel.hiba("Számformátum", "Az ár nem lehet negatív");
                txtAr.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            Panel.hiba("Számformátum", ex.getMessage());
            txtAr.requestFocus();
            return;
        }

        try {
            km = Integer.parseInt(txtKm.getText());
            if (km < 0) {
                Panel.hiba("Számformátum", "A km nem lehet negatív");
                txtKm.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            Panel.hiba("Számformátum", ex.getMessage());
            txtKm.requestFocus();
            return;
        }

        if (txtMegjegyzes.getText().length() > 255) {
            Panel.hiba("Megjegyzés", "A megjegyzés túl hosszú");
            txtMegjegyzes.requestFocus();
            return;
        }
        if (az < 1) {
            ab.uj(cbxKiadas.getValue(), ar,
                    dpDatum.getValue().toString(),
                    km, txtMegjegyzes.getText());
        } else {
            ab.modosit(az, cbxKiadas.getValue(), ar,
                    dpDatum.getValue().toString(),
                    km, txtMegjegyzes.getText());
        }
        megse();
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
