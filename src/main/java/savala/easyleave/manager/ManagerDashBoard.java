package savala.easyleave.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savala.easyleave.models.Manager;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ManagerDashBoard implements Initializable {

    Database database =new Database();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Manager manager=null;
        try{
            Connection conn= database.getConnection();
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM tbl_managers WHERE manager_id = "+ ManagerLogin.loggedUser_id);
            while(rs.next()){
                manager=new Manager(rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("email"), rs.getString("gender"),
                        Integer.parseInt(rs.getString("manager_id"))
                );
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        first_name.setText(manager.getFname());
    }

    @FXML
    private ImageView applicationImage;

    @FXML
    private HBox bonusDays;

    @FXML
    private ImageView bonusDaysImage;

    @FXML
    private ImageView close;

    @FXML
    private Button employeeLogout;

    @FXML
    private HBox enquire;

    @FXML
    private ImageView enquiries;

    @FXML
    private Label first_name;

    @FXML
    private HBox hBox;

    @FXML
    private HBox managerApplications;

    @FXML
    private ImageView myAccount;

    @FXML
    private HBox myEmployees;

    @FXML
    private ImageView myEmployeesImage;

    @FXML
    void applicationImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) applicationImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_pending_applications.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void bonusDays(MouseEvent event) throws IOException {
        Stage stage = (Stage) bonusDays.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/bonus_days.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void bonusDaysImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) bonusDaysImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/bonus_days.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void employee_logout(MouseEvent event) throws IOException {
        Stage stage = (Stage) employeeLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/savala/easyleave/welcome/login.fxml"));
        stage.setScene(new Scene(root));

    }

    @FXML
    void enquire(MouseEvent event) throws IOException {
        Stage stage = (Stage) enquire.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/enquiries.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void enquiries(MouseEvent event) throws IOException {
        Stage stage = (Stage) enquiries.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/enquiries.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void managerApplications(MouseEvent event) throws IOException {
        Stage stage = (Stage) managerApplications.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_pending_applications.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myAccount(MouseEvent event) throws IOException {
        Stage stage = (Stage) myAccount.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/my_account.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myEmployees(MouseEvent event) throws IOException {
        Stage stage = (Stage) myEmployees.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/my_employees.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myEmployeesImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) myEmployeesImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/my_employees.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

}
