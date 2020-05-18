import java.sql.*;


public class Database {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public Database() {

    }

    public void getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Failed to register MySQL Connector");
            return;
        }

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2341471", "sql2341471","hI2%xW7*");
            System.out.println("COMEEE ONNNNN");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return;
        }

    }

}
