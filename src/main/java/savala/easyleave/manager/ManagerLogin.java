package savala.easyleave.manager;

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
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ManagerLogin {

    public static int loggedUser_id,DeptNo;

    @FXML
    private ImageView back;

    @FXML
    private ImageView close;

    @FXML
    private Button managerLogin;

    @FXML
    private TextField managerStaffNo;

    @FXML
    private PasswordField managerPassword;

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
    void manager_login(MouseEvent event) throws IOException {
        Database databaseConnection = new Database();
        Connection connection=databaseConnection.getConnection();
        String verify;
        String id;
        String manager_id,deptNo;
        verify = "SELECT COUNT(1) FROM `tbl_managers` WHERE manager_id= '" + managerStaffNo.getText() + "' AND password = '" + managerPassword.getText() + "'";
        id="SELECT * FROM `tbl_managers` WHERE manager_id=\"" +managerStaffNo.getText()+"\"";
        manager_id="manager_id";
        deptNo="DeptNo";
//        deptNo="SELECT manager_id FROM `tbl_managers` WHERE manager_id=\"" +managerStaffNo.getText()+"\"";

        try{
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(verify);
            Statement statement2= connection.createStatement();
            ResultSet rs= statement2.executeQuery(id);
            while(rs.next()) {
                loggedUser_id=rs.getInt(manager_id);
                DeptNo=rs.getInt(deptNo);

            }

            while(resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    //labelThis.setText("Congrats");
                    Stage stage = (Stage) managerLogin.getScene().getWindow();
                    FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_dashboard.fxml"));
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
