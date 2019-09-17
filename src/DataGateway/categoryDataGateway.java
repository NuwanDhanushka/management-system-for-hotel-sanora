/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataGateway;

import DataAccessLayer.category;
import DataList.categoryList;
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
public class categoryDataGateway {
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();
   

    public void Add(category category) {
        connection = dbConnection.getConnection();
        category.brandName = sql.getIdNo(category.brandName, category.brandId, "brands", "brandName");
        category.brandId = sql.getIdNo(category.brandName, category.brandId, "brands", "brandName");
        category.supplierId = sql.getIdNo(category.supplierName, category.supplierId, "supplier", "supplierName");
        try {
            pst = connection.prepareStatement("insert into Category(categoryName,description,brandId,supplierId,creatorId,date) values(?,?,?,?,?,?)");
     
            pst.setString(1, category.categoryName);
            pst.setString(2, category.categoryDescription);
            pst.setString(3, category.brandId);
            pst.setString(4, category.supplierId);
            pst.setString(5, category.creatorId);
            pst.setString(6, LocalDate.now().toString());
            pst.executeUpdate();
            pst.close();
            connection.close();
                   Notifications.create()
              .title("Success")
              .text("Data added successfully")
              .showWarning();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(category category) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareCall("select * from category");
            rs = pst.executeQuery();
            while (rs.next()) {
                category.id = rs.getString(1);
                category.categoryName = rs.getString(2);
                category.categoryDescription = rs.getString(3);
                category.brandId = rs.getString(4);
                category.supplierId = rs.getString(5);
                category.creatorId = rs.getString(6);
                category.date = rs.getString(7);
                category.brandName = sql.getName(category.brandId, category.brandName, "brands");
                category.supplierName = sql.getName(category.supplierId, category.supplierName, "supplier");
                category.creatorName = "Nuwan";//sql.getName(category.creatorId, category.categoryName, "user");
                category.categoryList.addAll(new categoryList(category.id, category.categoryName, category.categoryDescription, category.brandName, category.supplierName, category.creatorName, category.date));
            }
            connection.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedView(category category) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("select * from category where Id=?");
            pst.setString(1, category.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                category.id = rs.getString(1);
                category.categoryName = rs.getString(2);
                category.categoryDescription = rs.getString(3);
                category.brandId = rs.getString(4);
                category.supplierId = rs.getString(5);
                category.brandName = sql.getName(category.brandId, category.brandName, "brands");
                category.supplierName = sql.getName(category.supplierId, category.supplierName, "supplier");
            }pst.close();
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void brandView(category category) {
        connection = dbConnection.getConnection();

        try {
            pst = connection.prepareStatement("select * from brands where supplierId=?");
            pst.setString(1, category.supplierId);
            rs = pst.executeQuery();
            while (rs.next()) {
                category.brandName = rs.getString(2);
            }pst.close();
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchView(category category) {
        connection = dbConnection.getConnection();
        category.categoryList.clear();


        try {
            pst = connection.prepareStatement("select * from Category where CategoryName like ? ORDER BY CategoryName");

            pst.setString(1, "%" + category.categoryName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                category.id = rs.getString(1);
                category.categoryName = rs.getString(2);
                category.categoryDescription = rs.getString(3);
                category.brandId = rs.getString(4);
                category.supplierId = rs.getString(5);
                category.creatorId = rs.getString(6);
                category.date = rs.getString(7);
                category.brandName = sql.getName(category.brandId, category.brandName, "brands");
                category.supplierName = sql.getName(category.supplierId, category.supplierName, "supplier");
                category.creatorName = "Nuwan";//sql.getName(category.creatorId, category.categoryName, "user");
                category.categoryList.addAll(new categoryList(category.id, category.categoryName, category.categoryDescription, category.brandName, category.supplierName, category.creatorName, category.date));
            }
            pst.close();
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(category category) {
        connection = dbConnection.getConnection();
        category.brandId = sql.getIdNo(category.brandName, category.brandId, "brands", "brandName");
        category.supplierId = sql.getIdNo(category.supplierName, category.supplierId, "supplier", "supplierName");

        try {
            pst = connection.prepareStatement("update category set categoryName=? , description=?,brandId=?,supplierId=? where Id=?");
            pst.setString(1, category.categoryName);
            pst.setString(2, category.categoryDescription);
            pst.setString(3, category.brandId);
            pst.setString(4, category.supplierId);
            pst.setString(5, category.id);
            pst.executeUpdate();
            pst.close();
            connection.close();
         Notifications.create()
              .title("Success")
              .text("Data updated successfully")
              .showWarning();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(category category) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("delete from category where Id=?");
            pst.setString(1, category.id);
            pst.executeUpdate();
            pst.close();
            connection.close();
                   Notifications.create()
              .title("Success")
              .text("Data deleted successfully")
              .showWarning();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isNotUse(category category){
        connection = dbConnection.getConnection();
        boolean isNotUse = false;
        try {
            pst = connection.prepareCall("select * from products where categoryId=?");
            pst.setString(1, category.id);
            rs = pst.executeQuery();
            while(rs.next()){
                //Dialogs.create().title("").masthead("Error").message("This Catagory already used in '"+ rs.getString(2) +"' Product \n delete Product first").styleClass(Dialog.STYLE_CLASS_UNDECORATED).showError();
                       Notifications.create()
              .title("Error")
              .text("\"This Catagory already used in '\"+ rs.getString(2) +\"' Product \\n delete Product first\"")
              .showWarning();
                return isNotUse;
            }pst.close();
            rs.close();
            connection.close();
            isNotUse = true;
        } catch (SQLException ex) {
           // Logger.getLogger(CatagoryGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }
    
}
