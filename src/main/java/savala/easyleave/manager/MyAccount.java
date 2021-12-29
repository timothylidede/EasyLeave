package savala.easyleave.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class MyAccount implements Initializable {

    @FXML
    private ImageView applicationImage;

    @FXML
    private Button back;

    @FXML
    private ImageView bonusDaysImage;

    @FXML
    private Label emailAddress;

    @FXML
    private ImageView enquiries;

    @FXML
    private ImageView femaleProfilePhoto;

    @FXML
    private Label firstName;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView maleProfilePhoto;

    @FXML
    private ImageView myEmployeesImage;

    @FXML
    private Label secondName;

    @FXML
    private Label sex;

    @FXML
    private Label staffId;

    Database database=new Database();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Manager manager=null;
        try{
            Connection conn= database.getConnection();
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM tbl_managers" +
                    " WHERE manager_id = "+ ManagerLogin.loggedUser_id);
            while(rs.next()){
                manager=new Manager(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"),
                        rs.getString("gender"),
                        Integer.parseInt(rs.getString("manager_id"))
                        //funny business here.....change Number to Name
                );
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        firstName.setText(manager.getFname());
        secondName.setText(manager.getLname());
        emailAddress.setText(manager.getEmail());
        staffId.setText(String.valueOf(manager.getManager_id()));
        sex.setText(manager.getGender());



        String actualSex = String.valueOf(sex.getText());
        if(actualSex.equals("Male")){
            maleProfilePhoto.setVisible(true);
            femaleProfilePhoto.setVisible(false);
        }
        if(actualSex.equals("Female")){
            maleProfilePhoto.setVisible(false);
            femaleProfilePhoto.setVisible(true);
        }
    }

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
    void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_dashboard.fxml"));
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
