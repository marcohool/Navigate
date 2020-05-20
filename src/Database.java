import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Optional;


public class Database {

    private static Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private boolean connectionEstablished;

    public Database() {

    }

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Failed to register MySQL Connector");
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2341471" , "sql2341471" , "hI2%xW7*");
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

    public static ObservableList<People> getPeople() {

        Database database = new Database();
        Connection conn = database.getConnection();
        ObservableList<People> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_people");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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

    public static void addEntry(People person) {
        Database database = new Database();
        Connection conn = database.getConnection();
        String sql = "INSERT INTO tbl_people (name,firmName,practiseArea,speciality,jobTitle,ethnicity,admissionDate,admissionJuristiction,firmProfile,linkedinProfile,approached,phoneNo,email,status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, person.getName());
            ps.setString(2, person.getFirmName());
            ps.setString(3, person.getPractiseArea());
            ps.setString(4, person.getSpeciality());
            ps.setString(5, person.getJobTitle());
            ps.setString(6, person.getEthnicity());
            ps.setString(7, person.getAdmissionDate());
            ps.setString(8, person.getAdmissionJuristiction());
            ps.setString(9, person.getFirmName());
            ps.setString(10, person.getLinkedinProfile());
            ps.setString(11, person.getApproached());
            ps.setString(12, person.getPhoneNo());
            ps.setString(13, person.getEmail());
            ps.setString(14, person.getStatus());
            ps.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Entry successfully added");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void removeEntry(People person) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this record?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Database database = new Database();
            Connection conn = database.getConnection();
            String sql = "DELETE from tbl_people where personID = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps = conn.prepareStatement(sql);
                ps.setInt(1, person.getPersonID());
                ps.execute();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void editRecord(People person) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to edit this record?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();

        Database database = new Database();
        Connection conn = database.getConnection();

        if (result.get() == ButtonType.OK) {

            try {
                int id = person.getPersonID();
                String name = person.getName();
                String firmName = person.getFirmName();
                String practiceArea = person.getPractiseArea();
                String speciality = person.getSpeciality();
                String jobTitle = person.getJobTitle();
                String ethnicity = person.getEthnicity();
                String admissionDate = person.getAdmissionDate();
                String admissionJuristiction = person.getAdmissionJuristiction();
                String firmProfile = person.getFirmProfile();
                String linkedinProfile = person.getLinkedinProfile();
                String approached = person.getApproached();
                String phoneNo = person.getPhoneNo();
                String email = person.getEmail();
                String status = person.getStatus();

                String sql = "UPDATE tbl_people SET name= '" + name + "',firmName= '" +
                        firmName + "',practiseArea= '" + practiceArea + "',speciality= '" + speciality + "', jobTitle= '" + jobTitle + "', ethnicity= '" + ethnicity + "', admissionDate= '" + admissionDate + "', admissionJuristiction= '" + admissionJuristiction + "',firmProfile= '" + firmProfile + "',linkedinProfile= '" + linkedinProfile + "',approached= '" + approached + "',phoneNo='" + phoneNo + "',email='" + email + "', status='" + status + "'where personID='" + id + "' ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();

                Alert updated = new Alert(Alert.AlertType.INFORMATION);
                updated.setTitle(null);
                updated.setHeaderText(null);
                updated.setContentText("Record updated");
                updated.initStyle(StageStyle.UTILITY);
                updated.showAndWait();


            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("An error has occured");
                alert.setContentText(e.toString());
                alert.showAndWait();
                alert.initStyle(StageStyle.UTILITY);

            }
        }
    }

    public static boolean checkDetails(String username, String password){


        Database database = new Database();
        Connection conn = database.getConnection();



        try {
            String sql = "SELECT * FROM tbl_users WHERE username = '"+username+"' AND password = '"+password+"'";
            Statement stmt = conn.createStatement();
            PreparedStatement ps = null;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                return true;
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return false;

    }

    public static ArrayList<String[]> getComments(int personID){

        // index 0 = userID
        // index 1 = comment text
        ArrayList<String[]> comments;

        return null;
    }


}
