package savala.easyleave.welcome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button employeeLogin;

    @FXML
    private Button managerLogin;

    @FXML
    void closeApp(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void employee_login(MouseEvent event) throws IOException {
        Stage stage = (Stage) employeeLogin.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/savala/easyleave/employee/employee_login.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    void manager_login(MouseEvent event) throws IOException {
        Stage stage = (Stage) managerLogin.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/savala/easyleave/manager/manager_login.fxml"));
        stage.setScene(new Scene(root));
    }

}
