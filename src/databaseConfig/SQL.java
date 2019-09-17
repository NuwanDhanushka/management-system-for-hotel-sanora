/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuwan
 */
public class SQL {
    DBConnection dbConnection = new DBConnection();
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    
     public String getName(String id, String name, String tableName){

        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("select * from "+tableName+" where Id=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(2);
            }connection.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    public String getIdNo( String name,String id, String tableName,String fieldName){

        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("select * from "+tableName+" where "+fieldName+" =?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            while (rs.next()){
                id = rs.getString(1);
            }connection.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
    
    public String getBrandID(String supplyerId,String brandId,String brandName){
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("select * from Brands where SupplyerId=? and BrandName=?");
            pst.setString(1, supplyerId);
            pst.setString(2, brandName);
            rs  = pst.executeQuery();
            while(rs.next()){
                brandId = rs.getString(1);
            }connection.close();
            pst.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brandId;
    }
    
    public String getCatagoryId(String supplyerId,String brandId,String catagoryId,String catagoryName){
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("select * from Catagory where SupplyerId=? and BrandId=? and CatagoryName=?");
            pst.setString(1, supplyerId);
            pst.setString(2, brandId);
            pst.setString(3, catagoryName);
            rs  = pst.executeQuery();
            while(rs.next()){
                catagoryId = rs.getString(1);
            }connection.close();
            pst.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catagoryId;
    }
}
