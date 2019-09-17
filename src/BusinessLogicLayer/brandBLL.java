/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.brands;
import DataGateway.brandDataGateway;
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
public class brandBLL {
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();
    brandDataGateway dataGateway = new brandDataGateway();
    
    public void Add(brands brands) {
        if (isUniqName(brands)) {
            dataGateway.Add(brands);
        }
    }
    
     public void update(brands brands) {
        if (isTrueUpdate(brands)) {
            dataGateway.update(brands);
        } else if (isUniqName(brands)) {
            dataGateway.update(brands);
        }
    }  
    public void delete(brands brands){
        if(dataGateway.isNotUsed(brands)){
            dataGateway.delete(brands);
        }else{
            //noting
        }
    }
    public boolean isTrueUpdate(brands brands) {
        boolean isTreu = false;
        brands.supplierId = sql.getIdNo(brands.supplierName, brands.supplierId, "supplier", "supplierName");
        System.out.println("we are in update");

        try {
            pst = connection.prepareStatement("SELECT * FROM brands WHERE brandName =? AND supplierId =? AND Id =?");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.supplierId);
            pst.setString(3, brands.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isTreu;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTreu;
    }
     public boolean isUniqName(brands brands) {
        boolean uniqSupplier = false;
        try {
            pst = connection.prepareCall("select * from brands where brandName=? and supplierId=?");
            brands.supplierId = sql.getIdNo(brands.supplierName, brands.supplierId, "supplier", "supplierName");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.supplierId);
            rs = pst.executeQuery();
            while (rs.next()) {
             /*   System.out.println("in not uniq");
                Dialogs.create().title("Sucess")
                        .masthead("Warning")
                        .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                        .message("Brand" + "  '" + brands.brandName + "' " + "Already exist")
                        .showWarning(); wronging not uniq*/
                return uniqSupplier;
            }
            uniqSupplier = true;
        } catch (SQLException ex) {
           // Logger.getLogger(Suppl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplier;
    }

}
