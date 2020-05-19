import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.util.Observable;


public class Database {

    private static Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private boolean connectionEstablished;

    public Database() {

    }

    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Failed to register MySQL Connector");
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2341471", "sql2341471","hI2%xW7*");
            System.out.println("Connection received");
            //conn.close();
            connectionEstablished = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection to the database could not be established");
            connectionEstablished = false;
        }

        return conn;

    }

    public boolean getConnectionEstablished() {
        return connectionEstablished;
    }

    public static ObservableList<People> getPeople(){

        Database database = new Database();
        Connection conn = database.getConnection();
        ObservableList<People> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_people");
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()){
                list.add(new People(rs.getInt("personID"), rs.getString("name"), rs.getString("firmName"), rs.getString("practiseArea"),
                        rs.getString("speciality"), rs.getString("jobTitle"), rs.getString("ethnicity"), rs.getString("admissionDate"),
                        rs.getString("admissionJuristiction"), rs.getString("firmProfile"), rs.getString("linkedinProfile"), rs.getString("approached"),
                        rs.getString("phoneNo"), rs.getString("email"), rs.getString("status")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
