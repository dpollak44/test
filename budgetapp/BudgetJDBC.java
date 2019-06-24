package budgetapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author david
 */
public class BudgetJDBC {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/maaser?user=david&password=pass");
        return connection;
    }

    public static void insertMaaserInfo(double amt, String org, String date) {
        try ( Connection con = getConnection()) {
            String sql = "INSERT INTO maaser_info ( amt, org,date) VALUES "
                    + "(?,?,?)";
            PreparedStatement pStatement = con.prepareStatement(sql);

            pStatement.setDouble(1, amt);
            pStatement.setString(2, org);
            pStatement.setString(3, date);

    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(BudgetJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BudgetJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ResultSet getMaaserInfo(String date1, String date2) {
        try ( Connection con = getConnection()) {
            Statement statement = con.createStatement();
            String sql = "Select *"
                    + "FROM maaser_given mg "
                    + "WHERE mg.date BETWEEN >= '" + date1 + "'"
                    + "AND '" + date2 + "'";
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
        return null;

    }
}
