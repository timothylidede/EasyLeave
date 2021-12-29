package savala.easyleave.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import savala.easyleave.models.Employee;
import savala.easyleave.models.ViewApplication;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class BonusDays implements Initializable {

    @FXML
    private TableView<Employee> employee_table;

    @FXML
    private TableColumn<Employee, String> staff_id;

    @FXML
    private TableColumn<Employee, String> last_name;

    @FXML
    private TableColumn<Employee, String> first_name;

    @FXML
    private TableColumn<Employee, String> deptName;

    @FXML
    private TableColumn<Employee, String> bonus;

    @FXML
    private TableColumn<Employee, String> leave_add;

    @FXML
    private ImageView applicationImage;

    @FXML
    private Button back;

    @FXML
    private ImageView enquiries;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView myAccount;

    @FXML
    private ImageView myEmployeesImage;


    Database database=new Database();
    ObservableList<Employee> apList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String verify;
        verify = "SELECT * FROM `tbl_employees` JOIN tbl_departments " +
                "ON tbl_employees.DeptNo =tbl_departments.DepartmentNo " +
                " WHERE DeptNo= '" + ManagerLogin.DeptNo + "' ";
        try{
            Connection connection=database.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs=statement.executeQuery(verify);

            while(rs.next()) {
                //labelThis.setText("Congrats");
                apList.add(new Employee(
                        rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("gender"),rs.getString("email"),
                        Integer.parseInt(rs.getString("bonus")),Integer.parseInt(rs.getString("staff_id")),
                        rs.getString("DeptName")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        if(apList!=null) {
            first_name.setCellValueFactory(new PropertyValueFactory<>("fname"));
            last_name.setCellValueFactory(new PropertyValueFactory<>("lname"));
            staff_id.setCellValueFactory(new PropertyValueFactory<>("staff_id"));
            bonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
            deptName.setCellValueFactory(new PropertyValueFactory<>("deptName"));

            Callback<TableColumn<Employee,String>, TableCell<Employee,String>> addBonus =(param)->{
                final TableCell<Employee,String>cell=new TableCell<Employee,String>(){
                    @Override
                    public void updateItem(String item,boolean empty){
                        super.updateItem(item,empty);
                        if(empty){
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button addButton=new Button("Add");
                            addButton.setStyle("-fx-background-color: \n" +
                                    "        #3DC620,\n" +
                                    "        linear-gradient(#66CA51 0%, #57D43D 20%, #3DC620 100%),\n" +
                                    "        linear-gradient(#4AAF34, #4AAF34),\n" +
                                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                                    "    -fx-background-radius: 30;\n" +
                                    "    -fx-background-insets: 0,1,2,0;\n" +
                                    "    -fx-text-fill: white;\n" +
                                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                                    "    -fx-font-family: \"Arial\";\n" +
                                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                                    "    -fx-font-size: 10px;\n" +
                                    "    -fx-padding: 10 20 10 20;");
                            addButton.setOnAction(event ->{
                                Employee viewApp=getTableView().getItems().get(getIndex());
                                TextInputDialog dialog = new TextInputDialog();
                                dialog.setTitle("Bonus Days");
                                dialog.setHeaderText("Add Bonus Days to "+ viewApp.getFname()+" ?");
                                dialog.setContentText("Enter Bonus Days:");
                                Optional<String> result = dialog.showAndWait();
                                if (result.isPresent()){
                                    System.out.println("Your name: " + result.get());
                                    String id,bonus;
                                    int bonusDay=0;
                                    int input= Integer.parseInt(result.get());
                                    id="SELECT bonus FROM `tbl_employees` WHERE staff_id=\"" +viewApp.getStaff_id()+"\"";
                                    bonus="bonus";
                                    try {
                                        Connection conn = database.getConnection();
                                        Statement statement2= conn.createStatement();
                                        ResultSet rs= statement2.executeQuery(id);
                                        while(rs.next()) {
                                            bonusDay = rs.getInt(bonus);
                                        }
                                        Statement statement = conn.createStatement();
                                        bonusDay+=input;
                                        statement.executeUpdate("UPDATE `tbl_employees` SET `bonus`='"+bonusDay+"' " +
                                                "WHERE `staff_id` = '" + viewApp.getStaff_id() + "';");


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        e.getCause();
                                    }

                                    Alert alert=new Alert((Alert.AlertType.INFORMATION));
                                    alert.setTitle("Bonus Days.");
                                    alert.setHeaderText("Success");
                                    alert.setContentText("Bonus Days Added Successfully.");
                                }
                                else {
                                    dialog.close();
                                }

                            });
                            setGraphic(addButton);
                            setText(null);
                        }
                    }
                };
                return cell;
            };

            leave_add.setCellFactory(addBonus);
            employee_table.setItems(apList);
        }
        else{
//            leave_table.setDisable(true);
//            leave_table.setVisible(false);

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
