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
import savala.easyleave.models.ViewApplication;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManagerPendingApplications implements Initializable {

    @FXML
    private TableColumn<ViewApplication, String> leave_staff;

    @FXML
    private TableColumn<ViewApplication, String> leave_dept;

    @FXML
    private TableView<ViewApplication> leave_table;

    @FXML
    private TableColumn<ViewApplication, String> leave_type;

    @FXML
    private TableColumn<ViewApplication, String> leave_start;

    @FXML
    private TableColumn<ViewApplication, String> leave_end;

    @FXML
    private TableColumn<ViewApplication, Integer> leave_days;

    @FXML
    private TableColumn<ViewApplication, String> leave_status;

    @FXML
    private TableColumn<ViewApplication, String> leave_firstname;

    @FXML
    private TableColumn<ViewApplication, String> leave_lastname;


    @FXML
    private TableColumn<ViewApplication, String> leave_reasons;

    @FXML
    private TableColumn<ViewApplication, String> leave_accept;

    @FXML
    private TableColumn<ViewApplication, String> leave_reject;

    @FXML
    private HBox accepted;

    @FXML
    private Button back;

    @FXML
    private ImageView bonusDaysImage;

    @FXML
    private ImageView enquiries;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView myAccount;

    @FXML
    private ImageView myEmployeesImage;

    @FXML
    private HBox rejected;
    Database database=new Database();
    ObservableList<ViewApplication> apList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String verify;
        verify = "SELECT * FROM `tbl_leavedetails` JOIN tbl_employees " +
        "ON tbl_leavedetails.leave_staff =tbl_employees.staff_id " +
                " WHERE DeptNo= '" + ManagerLogin.DeptNo + "' AND leave_status = 'Pending' ";
        try{
            Connection connection=database.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs=statement.executeQuery(verify);

            while(rs.next()) {
                //labelThis.setText("Congrats");
                apList.add(new ViewApplication(Integer.parseInt(rs.getString("leave_staff")),Integer.parseInt(rs.getString("leave_id")),Integer.parseInt(rs.getString("DeptNo")),
                        rs.getString("first_name"),rs.getString("last_name"),rs.getString("leave_startdate"),
                        rs.getString("leave_enddate"),rs.getString("leave_daysrequested"),
                        rs.getString("leave_type"), rs.getString("leave_comments"),
                        rs.getString("leave_status"),
                        new Button(),
                        new Button(),
                        rs.getDate("leave_startdate")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        if(apList!=null) {
            leave_firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            leave_lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            leave_start.setCellValueFactory(new PropertyValueFactory<>("leaveStart"));
            leave_end.setCellValueFactory(new PropertyValueFactory<>("leaveEnd"));
            leave_days.setCellValueFactory(new PropertyValueFactory<>("leaveDays"));
            leave_type.setCellValueFactory(new PropertyValueFactory<>("leaveType"));
            leave_status.setCellValueFactory(new PropertyValueFactory<>("leaveStatus"));
            leave_reasons.setCellValueFactory(new PropertyValueFactory<>("leaveReason"));
//            leave_accept.setCellValueFactory(new PropertyValueFactory<>("accept"));
//            leave_reject.setCellValueFactory(new PropertyValueFactory<>("reject"));

            Callback<TableColumn<ViewApplication,String>, TableCell<ViewApplication,String>> accept =(param)->{
                final TableCell<ViewApplication,String>cell=new TableCell<ViewApplication,String>(){
                    @Override
                    public void updateItem(String item,boolean empty){
                        super.updateItem(item,empty);
                        if(empty){
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button acceptButton=new Button("Accept");
                            acceptButton.setStyle("-fx-background-color: \n" +
                                    "        #055C94,\n" +
                                    "        linear-gradient(#4DA4DD 0%, #1C7EBE 20%, #055C94 100%),\n" +
                                    "        linear-gradient(#2D88C4, #195D8A),\n" +
                                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                                    "    -fx-background-radius: 30;\n" +
                                    "    -fx-background-insets: 0,1,2,0;\n" +
                                    "    -fx-text-fill: white;\n" +
                                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                                    "    -fx-font-family: \"Arial\";\n" +
                                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                                    "    -fx-font-size: 10px;\n" +
                                    "    -fx-padding: 10 20 10 20;");
                            acceptButton.setOnAction(event ->{
                                ViewApplication viewApp=getTableView().getItems().get(getIndex());
                                Alert alert=new Alert((Alert.AlertType.CONFIRMATION));
                                alert.setTitle("Confirmation Dialog");
                                alert.setHeaderText(viewApp.getFirst_name()+"'s Leave Application.");
                                alert.setContentText("Accept "+ viewApp.getFirst_name()+"'s Application?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                    int remainingdays=findremainingDays(viewApp.getEmployee(), Integer.parseInt(viewApp.getLeaveDays()));
//                                    System.out.println(remainingdays);
                                    int remainingbonus=findremainingBonus(viewApp.getEmployee(), Integer.parseInt(viewApp.getLeaveDays()));
//                                    System.out.println(remainingbonus);
                                    int daystaken= Integer.parseInt(finddaysTaken(viewApp.getEmployee())+viewApp.getLeaveDays());
                                    try {
                                        Connection connection=database.getConnection();
                                        String verify;
                                        verify = "SELECT COUNT(1) FROM `tbl_leavedetails` WHERE leave_id = '" + viewApp.getStaff() +
                                                "' AND leave_staff= '" + viewApp.getEmployee() +
                                                "' AND leave_type= 'Normal' ";
                                        try {
                                            Statement statement = connection.createStatement();
                                            ResultSet resultSet = statement.executeQuery(verify);
                                            while (resultSet.next()) {
                                                if (resultSet.getInt(1) == 1) {
                                                    Connection conn = database.getConnection();
                                                    Statement statement1 = conn.createStatement();
                                                    Statement statement2 = conn.createStatement();
                                                    statement1.executeUpdate("UPDATE `tbl_leavedetails` SET `leave_status`='Accepted' " +
                                                            "WHERE `leave_id` = '" + viewApp.getStaff() + "';");
                                                    statement2.executeUpdate("UPDATE `tbl_employees` SET `leave_daystaken`='"+daystaken+"',`leave_daysremaining`='"+remainingdays+"'" +
                                                            ",`bonus`='"+remainingbonus+"'" +
                                                            "WHERE `staff_id` = '" + viewApp.getEmployee() + "';");

                                                } else {
                                                    Connection conn = database.getConnection();
                                                    Statement statement1 = conn.createStatement();
                                                    statement1.executeUpdate("UPDATE `tbl_leavedetails` SET `leave_status`='Accepted' " +
                                                            "WHERE `leave_id` = '" + viewApp.getStaff() + "';");
                                                }


                                                }
                                        } catch (Exception e){
                                            e.printStackTrace();
                                            e.getCause();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        e.getCause();
                                    }
                                    Alert alert2=new Alert((Alert.AlertType.INFORMATION));
                                    alert2.setTitle("Leave Application.");
                                    alert2.setHeaderText("Success");
                                    alert2.setContentText("Leave Application Accepted.");
                                } else {
                                    alert.close();
                                }
                            });
                            setGraphic(acceptButton);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            Callback<TableColumn<ViewApplication,String>, TableCell<ViewApplication,String>> reject =(param)->{
                final TableCell<ViewApplication,String>cell=new TableCell<ViewApplication,String>(){
                    @Override
                    public void updateItem(String item,boolean empty){
                        super.updateItem(item,empty);
                        if(empty){
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button rejectButton=new Button("Reject");
                            rejectButton.setStyle("-fx-background-color: \n" +
                                    "        #EF264C,\n" +
                                    "        linear-gradient(#EF6AAB 0%, #EF268B 20%, #E92C2F 100%),\n" +
                                    "        linear-gradient(#ED393C, #E91518),\n" +
                                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                                    "    -fx-background-radius: 30;\n" +
                                    "    -fx-background-insets: 0,1,2,0;\n" +
                                    "    -fx-text-fill: white;\n" +
                                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                                    "    -fx-font-family: \"Arial\";\n" +
                                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                                    "    -fx-font-size: 10px;\n" +
                                    "    -fx-padding: 10 20 10 20;");
                            rejectButton.setOnAction(event ->{
                                ViewApplication viewApp=getTableView().getItems().get(getIndex());
                                Alert alert=new Alert((Alert.AlertType.CONFIRMATION));
                                alert.setTitle("Confirmation Dialog");
                                alert.setHeaderText(viewApp.getFirst_name()+"'s Leave Application.");
                                alert.setContentText("Reject "+ viewApp.getFirst_name()+"'s Application?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                    try {
                                        Connection conn = database.getConnection();
                                        Statement statement = conn.createStatement();
                                        statement.executeUpdate("UPDATE `tbl_leavedetails` SET `leave_status`='Rejected' " +
                                                "WHERE `leave_id` = '" + viewApp.getStaff() + "';");

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        e.getCause();
                                    }
                                    Alert alert2=new Alert((Alert.AlertType.INFORMATION));
                                    alert2.setTitle("Leave Application.");
                                    alert2.setHeaderText("Success");
                                    alert2.setContentText("Leave Application Rejected.");
                                } else {
                                  alert.close();
                                }

                            });
                            setGraphic(rejectButton);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            leave_accept.setCellFactory(accept);
            leave_reject.setCellFactory(reject);

            leave_table.setItems(apList);
        }
        else{
//            leave_table.setDisable(true);
//            leave_table.setVisible(false);

        }

    }

    @FXML
    void accepted(MouseEvent event) throws IOException {
        Stage stage = (Stage) accepted.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_accepted_applications.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    private void handleButtonAction(ActionEvent event){

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
    void enquiries(MouseEvent event) throws IOException{
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

    @FXML
    void rejected(MouseEvent event) throws IOException {
        Stage stage = (Stage) rejected.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_rejected_applications.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


    private LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
    private int findremainingDays(int employee, int requested){
        Connection connection=database.getConnection();
        String id;
        String staff_days,bonus;
        int available_days=0,bonus_days=0;
        id="SELECT * FROM `tbl_employees` WHERE staff_id=\"" + employee+"\"";
        staff_days="leave_daysremaining";
        bonus="bonus";
        try{
            Statement statement2= connection.createStatement();
            ResultSet rs= statement2.executeQuery(id);
            while(rs.next()) {
                available_days=rs.getInt(staff_days);
                bonus_days=rs.getInt(bonus);
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        int remaining=available_days-requested;
        if(remaining<0){
            available_days=0;
            return available_days;
        } else {
            available_days=remaining;
            return available_days;
        }

    }
    private int findremainingBonus(int employee, int requested){
        Connection connection=database.getConnection();
        String id;
        String staff_days,bonus;
        int available_days=0,bonus_days=0;
        id="SELECT * FROM `tbl_employees` WHERE staff_id=\"" + employee+"\"";
        staff_days="leave_daysremaining";
        bonus="bonus";
        try{
            Statement statement2= connection.createStatement();
            ResultSet rs= statement2.executeQuery(id);
            while(rs.next()) {
                available_days=rs.getInt(staff_days);
                bonus_days=rs.getInt(bonus);
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        int remaining=available_days-requested;
        if(remaining<0){
            available_days=0;
            bonus_days=bonus_days-remaining;
            return bonus_days;
        } else {
            return bonus_days;
        }

    }
    private int finddaysTaken(int employee){
        Connection connection=database.getConnection();
        String id;
        String staff_days;
        int days_taken=0;
        id="SELECT * FROM `tbl_employees` WHERE staff_id=\"" + employee+"\"";
        staff_days="leave_daystaken";
        try{
            Statement statement2= connection.createStatement();
            ResultSet rs= statement2.executeQuery(id);
            while(rs.next()) {
                days_taken=rs.getInt(staff_days);

            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return days_taken;

    }
}
