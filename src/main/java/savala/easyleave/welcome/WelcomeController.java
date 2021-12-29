package savala.easyleave.welcome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import savala.easyleave.utils.FadeTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeController implements Initializable {

    @FXML
    private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition.applyFadeTransition(parent, Duration.seconds(3), actionEvent -> {
            try{
                Parent fxml = FXMLLoader.load(getClass().getResource("login.fxml"));
                parent.getChildren().removeAll();
                parent.getChildren().setAll(fxml);

            }catch (IOException ex){
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
