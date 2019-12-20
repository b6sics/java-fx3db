package szotar;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
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
import panel.Panel;
import static panel.Panel.hiba;

/**
 *
 * @author Joe
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Tab tabSzotar;

    @FXML
    private TextField txtLeckeSzuro;

    @FXML
    private TextField txtAngolSzuro;

    @FXML
    private TextField txtMagyarSzuro;

    @FXML
    private TableView<Szo> tblSzavak;

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
    void hozzaad() {
        String lecke = txtLecke.getText();
        if (lecke.length() < 1 || lecke.length() > 10) {
            hiba("Hozzáadás", "A lecke hossza 1-10 karakter lehet!");
            txtLecke.requestFocus();
            return;
        }
        String angol = txtAngol.getText();
        if (lecke.length() < 1 || lecke.length() > 60) {
            hiba("Hozzáadás", "Az angol mező hossza 1-60 karakter lehet!");
            txtLecke.requestFocus();
            return;
        }
        String magyar = txtMagyar.getText();
        if (lecke.length() < 1 || lecke.length() > 60) {
            hiba("Hozzáadás", "A magyar mező hossza 1-60 karakter lehet!");
            txtLecke.requestFocus();
            return;
        }

        int sor = ab.hozzaad(lecke, angol, magyar);
        if (sor > 0) {
            beolvas();
            uj();
        }
    }

    @FXML
    void modosit() {
        int index = tblSzavak.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        int id = tblSzavak.getItems().get(index).getSzoID();

        String lecke = txtLecke.getText();
        if (lecke.length() < 1 || lecke.length() > 10) {
            hiba("Hozzáadás", "A lecke hossza 1-10 karakter lehet!");
            txtLecke.requestFocus();
            return;
        }
        String angol = txtAngol.getText();
        if (lecke.length() < 1 || lecke.length() > 60) {
            hiba("Hozzáadás", "Az angol mező hossza 1-60 karakter lehet!");
            txtLecke.requestFocus();
            return;
        }
        String magyar = txtMagyar.getText();
        if (lecke.length() < 1 || lecke.length() > 60) {
            hiba("Hozzáadás", "A magyar mező hossza 1-60 karakter lehet!");
            txtLecke.requestFocus();
            return;
        }

        int sor = ab.modosit(id, lecke, angol, magyar);
        if (sor > 0) {
            beolvas();
            for (int i = 0; i < tblSzavak.getItems().size(); i++) {
                if (tblSzavak.getItems().get(i).getSzoID() == id) {
                    tblSzavak.getSelectionModel().select(i);
                    break;
                }
            }
        }
    }

    @FXML
    void szuro_torol() {
        txtLeckeSzuro.clear();
        txtAngolSzuro.clear();
        txtMagyarSzuro.clear();
        tblSzavak.requestFocus();
    }

    @FXML
    void torol() {
        int index = tblSzavak.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Biztosan törölni szeretnéd ezt a szót?")) {
            return;
        }
        int id = tblSzavak.getItems().get(index).getSzoID();
        int sor = ab.torol(id);
        if (sor > 0) {
            beolvas();
        }
    }

    @FXML
    void google() throws Exception {
        String s = "httgs://translate.google.com/"
                + "#view=home&op=translate&s1=en&t1=hu&text=";
        s += txtAngol.getText().replace(" ", "%20");
        Desktop.getDesktop().browse(new URI(s));
        txtMagyar.requestFocus();
    }

    @FXML
    void uj() {
        txtAngol.clear();
        txtMagyar.clear();
        txtLecke.requestFocus();
        tblSzavak.getSelectionModel().select(null);
    }

    private void beolvas() {
        String szuro1 = "'%" + txtLeckeSzuro.getText() + "%'";
        String szuro2 = "'%" + txtAngolSzuro.getText() + "%'";
        String szuro3 = "'%" + txtMagyarSzuro.getText() + "%'";
        String s = "SELECT * FROM szavak "
                + "WHERE lecke LIKE " + szuro1
                + "AND   angol LIKE " + szuro2
                + "AND  magyar LIKE " + szuro3
                + " ORDER BY angol;";
        ab.beolvas(tblSzavak.getItems(), s);
    }

    private void tablabol(int i) {
        if (i == -1) {
            return;
        }
        Szo sz = tblSzavak.getItems().get(i);
        txtLecke.setText("" + sz.getLecke());
        txtAngol.setText("" + sz.getAngol());
        txtMagyar.setText("" + sz.getMagyar());
    }

    DB ab = new DB();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oLecke.setCellValueFactory(new PropertyValueFactory<>("lecke"));
        oAngol.setCellValueFactory(new PropertyValueFactory<>("angol"));
        oMagyar.setCellValueFactory(new PropertyValueFactory<>("magyar"));

        beolvas();

        txtLeckeSzuro.textProperty().addListener((o, regi, uj) -> beolvas());
        txtAngolSzuro.textProperty().addListener((o, regi, uj) -> beolvas());
        txtMagyarSzuro.textProperty().addListener((o, regi, uj) -> beolvas());

        tblSzavak.getSelectionModel().selectedIndexProperty().addListener(
                (o, regi, uj) -> tablabol(uj.intValue())
        );

        cbxValaszt.getItems().addAll("Angol -> Magyar", "Magyar -> Angol");
        cbxValaszt.setValue("Angol -> Magyar");
    }

// tanulás
    private ArrayList<Szo> lista;
    private Random vg = new Random();
    private int index;

    @FXML
    private ComboBox<String> cbxValaszt;

    @FXML
    private Button btnIndit;

    @FXML
    private Button btnTudtam;

    @FXML
    private Button btnNemTudtam;

    @FXML
    private Label lblFelso;

    @FXML
    private Label lblAlso;

    @FXML
    private Label lblHanyvan;

    @FXML
    void indit() {
        if (btnIndit.getText().equals("Indít")) {
            tabSzotar.setDisable(true);
            cbxValaszt.setDisable(true);
            btnIndit.setText("Megállít");
            lista = new ArrayList<>(tblSzavak.getItems());
            nem_tudtam();
        } else {
            tabSzotar.setDisable(false);
            cbxValaszt.setDisable(false);
            btnIndit.setText("Indít");
            lblFelso.setText("?");
            lblAlso.setText("?");
            lblHanyvan.setText("");
            btnTudtam.setDisable(true);
            btnNemTudtam.setDisable(true);
        }
    }

    @FXML
    void mutat() {
        if (lblFelso.getText().equals("?")) {
            return;
        }
        if (cbxValaszt.getSelectionModel().getSelectedIndex() == 0) {
            lblAlso.setText(lista.get(index).getMagyar());
        } else {
            lblAlso.setText(lista.get(index).getAngol());
        }
        btnTudtam.setDisable(false);
        btnNemTudtam.setDisable(false);
    }

    @FXML
    void nem_tudtam() {
        lblHanyvan.setText("Még " + lista.size() + " szó.");
        index = vg.nextInt(lista.size());
        if (cbxValaszt.getSelectionModel().getSelectedIndex() == 0) {
            lblFelso.setText(lista.get(index).getAngol());
        } else {
            lblFelso.setText(lista.get(index).getMagyar());
        }
        lblAlso.setText("?");
        btnTudtam.setDisable(true);
        btnNemTudtam.setDisable(true);
    }

    @FXML
    void tudtam() {
        lista.remove(index);
        if (lista.isEmpty()) {
            hiba("Gratulálok!", "Minden szót megtanultál!");
            indit();
            return;
        }
        nem_tudtam();
    }

}
