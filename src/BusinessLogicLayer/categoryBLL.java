/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.category;
import DataGateway.categoryDataGateway;
import databaseConfig.DBConnection;
import databaseConfig.SQL;
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
public class categoryBLL {
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();
    categoryDataGateway dataGateway = new categoryDataGateway();
    


    public void Add(category catagory){
        if (isUniqName(catagory)){
            dataGateway.Add(catagory);
        }
    }

    public void update(category catagory){
        if(checkUpdate(catagory)){
            dataGateway.update(catagory);
        }else if(isUniqName(catagory)){
            dataGateway.update(catagory);
        }
    }
    
    public void delete(category catagory){
        if(dataGateway.isNotUse(catagory)){
            dataGateway.delete(catagory);
        }else{
            //noting
        }
    }

    public boolean checkUpdate(category catagory){
        boolean isTrueUpdate = false;
        catagory.brandId = sql.getIdNo(catagory.brandName, catagory.brandId, "brands", "brandName");
        catagory.supplierId = sql.getIdNo(catagory.supplierName, catagory.supplierId, "supplier", "supplierName");

        try {
            pst = connection.prepareStatement("select * from category where categoryName=? and brandId=? and supplierId=? and Id=?");
            pst.setString(1, catagory.categoryName);
            pst.setString(2, catagory.brandId);
            pst.setString(3, catagory.supplierId);
            pst.setString(4, catagory.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isTrueUpdate = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTrueUpdate;
    }

    public boolean isUniqName(category catagory) {

        boolean uniqSupplyer = false;
        catagory.brandId = sql.getIdNo(catagory.brandName, catagory.brandId, "brands", "brandName");
        catagory.supplierId = sql.getIdNo(catagory.supplierName, catagory.supplierId, "supplier", "supplierName");
        try {
            pst = connection.prepareCall("select * from category where categoryName=? and brandId=? and supplierId=?");
            pst.setString(1, catagory.categoryName);
            pst.setString(2, catagory.brandId);
            pst.setString(3, catagory.supplierId);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");
            /*    Dialogs.create().title("Sucess")
                        .masthead("Warning")
                        .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                        .message("Catagory" + "  '" + catagory.catagoryName + "' " + "Already exist")
                        .showWarning();*/
                return uniqSupplyer;
            }
            uniqSupplyer = true;
        } catch (SQLException ex) {
         //   Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }
    
}
