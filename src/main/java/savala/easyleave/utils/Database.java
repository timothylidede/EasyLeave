package savala.easyleave.utils;

import java.sql.Connection;
import java.sql.DriverManager;

//Code for Connecting to database.
public class Database {
    public Connection database_link;
    public Connection getConnection(){
        String dbase_name= "easy_leave";
        String dbase_user="root";
        String dbase_password="";
        String url="jdbc:mysql://localhost/" + dbase_name;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            database_link=DriverManager.getConnection(url,dbase_user,dbase_password);
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return database_link;
    }
}
