import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                        rs.getString("phoneNo"), rs.getString("email")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void addEntry(People person) {
        Database database = new Database();
        Connection conn = database.getConnection();
        String sql = "INSERT INTO tbl_people (name,firmName,practiseArea,speciality,jobTitle,ethnicity,admissionDate,admissionJuristiction,firmProfile,linkedinProfile,approached,phoneNo,email)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            ps.setString(9, person.getFirmProfile());
            ps.setString(10, person.getLinkedinProfile());
            ps.setString(11, person.getApproached());
            ps.setString(12, person.getPhoneNo());
            ps.setString(13, person.getEmail());
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

                String sql = "UPDATE tbl_people SET name= '" + name + "',firmName= '" +
                        firmName + "',practiseArea= '" + practiceArea + "',speciality= '" + speciality + "', jobTitle= '" + jobTitle + "', ethnicity= '" + ethnicity + "', admissionDate= '" + admissionDate + "', admissionJuristiction= '" + admissionJuristiction + "',firmProfile= '" + firmProfile + "',linkedinProfile= '" + linkedinProfile + "',approached= '" + approached + "',phoneNo='" + phoneNo + "',email='" + email + "'WHERE personID = '" + id + "'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();

                Alert updated = new Alert(Alert.AlertType.INFORMATION);
                updated.setTitle(null);
                updated.setHeaderText(null);
                updated.setContentText("Record updated");
                updated.initStyle(StageStyle.UTILITY);
                updated.showAndWait();


            } catch (Exception e) {
                System.out.println(e);
                Alert error = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("An error has occured");
                alert.setContentText(e.toString());
                alert.showAndWait();
                alert.initStyle(StageStyle.UTILITY);

            }
        }
    }

    public static String checkDetails(String username, String password) {


        Database database = new Database();
        Connection conn = database.getConnection();


        try {
            String sql = "SELECT * FROM tbl_users WHERE username = '" + username + "' AND password = '" + password + "'";
            Statement stmt = conn.createStatement();
            PreparedStatement ps = null;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    public static void addComment(People person, String text){

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        java.util.Date date = new Date();

        String textToAdd = Controller.loggedInUser + " @ " + formatter.format(date) + ": " + text;

        Database database = new Database();
        Connection conn = database.getConnection();
        String sql = "INSERT INTO tbl_comments (text,username) values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, textToAdd);
            ps.setString(2, Controller.loggedInUser);
            ps.execute();

            sql = "SELECT * FROM tbl_comments";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            int commentID = rs.getInt("commentID");

            int personID = person.getPersonID();
            sql = "INSERT INTO tbl_commentsPage (personID,commentID) values(?,?)";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ps2.setInt(1, personID);
            ps2.setInt(2, commentID);
            ps2.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Comment added");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println(e);
        }



    }

    public static List<Comment> getComments(int personID) {

        // index 0 = username
        // index 1 = comment text
        List<Comment> comments = new ArrayList<Comment>();

        Database database = new Database();
        Connection conn = database.getConnection();

        String sql = "SELECT commentID FROM tbl_commentsPage WHERE personID = '" + personID + "'";
        try {

            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql);


            while (rs1.next()) {
                int commentID = rs1.getInt("commentID");
                sql = "SELECT username, text FROM tbl_comments WHERE commentID ='" + commentID + "'";

                Statement st2 = conn.createStatement();
                ResultSet rs2 = st2.executeQuery(sql);

                while (rs2.next()) {
                    rs2.getString("username");
                    comments.add(new Comment(commentID, rs2.getString("username"), rs2.getString("text")));
                    //comments.add(new String[]{rs2.getString("username"), rs2.getString("text")});
                }
            }

        } catch (Exception e) {
            System.out.println("ok" + e);
        }

        return comments;
    }

    public static void removeComment(int commentID){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this comment?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Database database = new Database();
            Connection conn = database.getConnection();
            String sql = "DELETE from tbl_comments where commentID = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps = conn.prepareStatement(sql);
                ps.setInt(1, commentID);
                ps.execute();

                sql = "DELETE from tbl_commentsPage where commentID = ?";
                PreparedStatement ps2 = conn.prepareStatement(sql);
                ps2 = conn.prepareStatement(sql);
                ps2.setInt(1, commentID);
                ps2.execute();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }


}
