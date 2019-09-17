package BusinessLogicLayer;

import databaseConfig.DBConnection;
import databaseConfig.SQL;
import DataGateway.productDataGateway;
import DataAccessLayer.products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nuwan
 */
public class productsBLL {
   
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();
    productDataGateway dataGateway = new productDataGateway();
    
    public void Add(products products){
         if (isUniqName(products)) {
            dataGateway.Add(products);
        }
        
    }
    public void update(products products) {
      if(isNotNull(products)){
        if (isUpdate(products)) {
            if (checkUpdateCondition(products)) {
                dataGateway.update(products);
            } else if (isUniqName(products)) {
                dataGateway.update(products);
            }
        }
        }
    }
     public boolean isUniqName(products products) {
        boolean isUniqname = false;
        try {
            pst = connection.prepareStatement("select * from products where productName=?");
            pst.setString(1, products.productName);
            rs = pst.executeQuery();
            while (rs.next()) {
              //  Warning msg control fx
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {

        }
        return isUniqname;
    }
     
    public boolean isUpdate(products products){
    boolean isUpdate = false;
        try {
            pst = connection.prepareStatement("select * from products where productId=? and productName=? and quantity=? and sellPrice=? and brandId=? and supplierId=? "
                    + "and categoryId=? and date=? and description=?");
            pst.setString(1, products.productId);
            pst.setString(2, products.productName);
            pst.setString(3, products.quantity);
            pst.setString(4, products.sellPrice);
            pst.setString(5, products.brand);
            pst.setString(6, products.suppliedBy);
            pst.setString(7, products.category);
            pst.setString(8, products.date);
            pst.setString(9, products.description);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException ex) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;   
    }
        public boolean checkUpdateCondition(products products) {
        boolean isTrueUpdate = false;
        if (isUpdate(products)) {
            try {
                pst = connection.prepareStatement("select * from products where productId=?");
                pst.setString(1, products.productId);
                rs = pst.executeQuery();
                while (rs.next()) {

                    return isTrueUpdate = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isTrueUpdate;
    }
    
    
    
    public boolean isNotNull(products products) {
        
       boolean isNotNull = false;
        if (products.sellPrice.isEmpty() || products.quantity.isEmpty()) {
           // control fx
            return isNotNull;
        }
         isNotNull = true;
        return isNotNull;
    }
    
    public Object delete(products products){
       
           dataGateway.delete(products);
     
        return products;
    }
}
