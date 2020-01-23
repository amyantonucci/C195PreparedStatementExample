/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Version log
// 2: Edited to show setTimestamp 

package c195preparedstatementexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author amy.antonucci
 */
public class C195PreparedStatementExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Connection String
        //Server name:  52.206.157.109
        //Database name:  U03QIu
        //Username:  U03QIu
        //Password:  53688051379
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://52.206.157.109/U03QIu";

        //  Database credentials
        final String DBUSER = "U03QIu";
        final String DBPASS = "53688051379";

        Connection conn;
  

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, DBUSER, DBPASS);

            PreparedStatement ps = null;

        
            try {

                String sql = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                ps = conn.prepareStatement(sql);
                ps.setString(1, "123 Main St");
                ps.setString(2, "Apt 3");
                ps.setInt(3, 1);
                ps.setString(4, "11214");
                ps.setString(5, "555-1212");
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(7, "test");
                ps.setString(8, LocalDateTime.now().toString());
                ps.setString(9, "test");
                int res = ps.executeUpdate();
                if (res == 1) {//one row was affected; namely teh one that was inserted!
                    System.out.println("YAY!");
                } else {
                    System.out.println("BOO!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

           
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
