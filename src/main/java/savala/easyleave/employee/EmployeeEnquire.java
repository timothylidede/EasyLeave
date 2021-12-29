package savala.easyleave.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class EmployeeEnquire {

    @FXML
    private Button enquireButton;

    @FXML
    private TextArea enquireText;

    @FXML
    private ImageView applicationImage;

    @FXML
    private Button back;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView myAccount;

    @FXML
    private ImageView myLeaveDays;

    @FXML
    private ImageView status;
    Database database=new Database();


    @FXML
    void applicationImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) applicationImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_normal_application.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_dashboard.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myAccount(MouseEvent event) throws IOException {
        Stage stage = (Stage) myAccount.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/my_account.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myLeaveDays(MouseEvent event) throws IOException {
        Stage stage = (Stage) myLeaveDays.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/my_leave_days.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void status(MouseEvent event) throws IOException {
        Stage stage = (Stage) status.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_status_accepted.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void enquireButton(MouseEvent event) throws IOException {
        //take data from enquireText and send to db
        String eMessage=enquireText.getText();
        String insertFields;
        String insertValues;
        String inserttoRegister;

        insertFields = "INSERT INTO `tbl_enquiries` (`enquiry_id`, `enquiry_message`,`enquiry_madeby`) VALUES (";
        insertValues = "NULL, '" + eMessage+ "', '" + EmployeeLogin.loggedUser_id +"');";
        inserttoRegister = insertFields + insertValues;

        try {
            Connection connection = database.getConnection();
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate(inserttoRegister);
            Alert alert=new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Enquiries");
            alert.setHeaderText("Success.");
            alert.setContentText("Enquiry made successfully.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

}
