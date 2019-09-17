/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nuwan
 */
public class DBConnection {
    public Connection connection;

    String url ="jdbc:mysql://localhost:3306/";
    String user = "root";
    String pass = "";
    String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
   
       public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url+"itpproject"+unicode  , user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
      System.out.println("somthing wrong with connection"+ex);
            
        }
        
        return connection;
    }
}