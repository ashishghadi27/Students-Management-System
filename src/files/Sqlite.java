package files;
import java.sql.*;
import javax.swing.*;
public class Sqlite{

Connection conn = null;
public static Connection dbConnector() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite::resource:database/Database.sqlite");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
