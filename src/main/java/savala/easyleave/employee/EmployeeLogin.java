package savala.easyleave.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class EmployeeLogin {

    public static int loggedUser_id;

    @FXML
    private ImageView back;

    @FXML
    private Label first_name;

    @FXML
    private ImageView close;

    @FXML
    private Button employeeLogin;

    @FXML
    private PasswordField employeePassword;

    @FXML
    private TextField employeeStaffNumber;

    @FXML
    void closeApp(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/savala/easyleave/welcome/login.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    void employee_login(ActionEvent event) throws IOException {

        Database databaseConnection = new Database();
        Connection connection=databaseConnection.getConnection();
        String verify;
        String id;
        String staff_id;
        verify = "SELECT COUNT(1) FROM `tbl_employees` WHERE staff_id= '" + employeeStaffNumber.getText() + "' AND password = '" + employeePassword.getText() + "'";
        id="SELECT staff_id FROM `tbl_employees` WHERE staff_id=\"" +employeeStaffNumber.getText()+"\"";
        staff_id="staff_id";
        try{
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(verify);
            Statement statement2= connection.createStatement();
            ResultSet rs= statement2.executeQuery(id);
            while(rs.next()) {
                loggedUser_id=rs.getInt(staff_id);
            }

            while(resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    //labelThis.setText("Congrats");
                    Stage stage = (Stage) employeeLogin.getScene().getWindow();
                    FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_dashboard.fxml"));
                    Scene scene = new Scene(root.load());
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.show();

                }
                else{
                    javafx.scene.control.Dialog<String> dialog=new javafx.scene.control.Dialog<String>();
                    dialog.setTitle("Credentials Error");
                    dialog.setContentText("Account does not exist. Enter correct username and/or password.");
                    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                    dialog.showAndWait();
                }

            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}
