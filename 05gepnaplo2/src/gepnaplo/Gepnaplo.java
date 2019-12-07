package gepnaplo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author t1
 */
public class Gepnaplo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Gépnap1ó 2");
        stage.setResizable(false);
        //stage.setAlwaysOnTop(true);
        //stage.initStyle(StageStyle.UNDECORATED);
        //stage.setOnCloseRequest(e -> e.consume());
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
