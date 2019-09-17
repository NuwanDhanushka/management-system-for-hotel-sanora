/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataGateway;

import DataAccessLayer.brands;
import DataList.BrandList;
import databaseConfig.DBConnection;
import databaseConfig.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.controlsfx.control.Notifications;


/**
 *
 * @author Nuwan
 */
public class brandDataGateway {
    
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();

    public void Add(brands brands) {
        connection = dbConnection.getConnection();
        brands.supplierId = sql.getIdNo(brands.supplierId, brands.supplierId, "supplier", "supplierName");
        try {
            pst = connection.prepareStatement("insert into brands(brandName,Description,supplierId,creatorId,date) values(?,?,?,?,?)");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.brandDescription);
            pst.setString(3, brands.supplierId);
            pst.setString(4, brands.creatorId);
            pst.setString(5, LocalDate.now().toString());
            pst.executeUpdate();
            connection.close();
            pst.close();
               Notifications.create()
              .title("Success")
              .text("Data added successfully")
              .showWarning();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(brands brands) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareCall("select * from brands");
            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandDescription = rs.getString(3);
                brands.supplierId = rs.getString(4);
                brands.creatorId = rs.getString(5);
                brands.date = rs.getString(6);
                brands.supplierName =sql.getName(brands.supplierId, brands.supplierName, "supplier");
                brands.creatorName ="Nuwan";//sql.getName(brands.creatorId, brands.creatorName, "user");
                brands.brandList.addAll(new BrandList(brands.id, brands.brandName, brands.brandDescription, brands.supplierName, brands.creatorName, brands.date));
            }
            connection.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Su.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void selectedView(brands brands) {
        connection = dbConnection.getConnection();

        try {
            connection = dbConnection.getConnection();
            pst = connection.prepareCall("select * from brands where id=?");
            pst.setString(1, brands.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandDescription = rs.getString(3);
                brands.supplierId = rs.getString(4);
                brands.supplierName = sql.getName(brands.supplierId, brands.supplierName, "Supplier");
            }
            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
          // Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(brands brands) {
        connection = dbConnection.getConnection();

        try {
            pst = connection.prepareStatement("delete from brands where Id=?");
            pst.setString(1, brands.id);
            pst.executeUpdate();
            connection.close();
                     Notifications.create()
              .title("Success")
              .text("Data deleted successfully")
              .showWarning();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(brands brands) {
        connection = dbConnection.getConnection();

        try {
            pst = connection.prepareStatement("update brands set brandName=? , description=?,supplierId=? where Id=?");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.brandDescription);
            pst.setString(3, brands.supplierId);
            pst.setString(4, brands.id);
            pst.executeUpdate();
            connection.close();
            pst.close();
                  Notifications.create()
              .title("Success")
              .text("Data updated successfully")
              .showWarning();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isNotUsed(brands brands){
        connection = dbConnection.getConnection();
        boolean inNotUse = false;
        try {
            pst = connection.prepareStatement("select * from category where brandId=?");
            pst.setString(1, brands.id);
            rs = pst.executeQuery();
            while(rs.next()){
              //  Dialogs.create().title("").masthead("Error").message("This brand already used in '"+ rs.getString(2) +"' catagory \n delete catagory first").styleClass(Dialog.STYLE_CLASS_UNDECORATED).showError();
                         Notifications.create()
              .title("Error")
              .text("This brand already used in '"+ rs.getString(2) +"' catagory \n delete catagory first")
              .showWarning();
              return inNotUse;
            }rs.close();
            pst.close();
            connection.close();
            inNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(brandDataGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inNotUse;
    }
 public void searchView(brands brands) {
        connection = dbConnection.getConnection();

        brands.brandList.clear();
        System.out.println("name :" + brands.brandName);

        try {
            connection = dbConnection.getConnection();
            pst = connection.prepareCall("select * from brands where brandName like ? ORDER BY brandName");
            System.out.println("Brand name in Brand Object");
            pst.setString(1, "%" + brands.brandName + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandDescription = rs.getString(3);
                brands.supplierId = rs.getString(4);
                brands.creatorId = rs.getString(5);
                brands.date = rs.getString(6);
                brands.supplierName = sql.getName(brands.supplierId, brands.supplierName, "Supplier");
                brands.creatorName ="Nuwan";
                 brands.brandList.addAll(new BrandList(brands.id, brands.brandName, brands.brandDescription, brands.supplierName, brands.creatorName, brands.date));
            }
            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
           // Logger.getLogger(Suppl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
