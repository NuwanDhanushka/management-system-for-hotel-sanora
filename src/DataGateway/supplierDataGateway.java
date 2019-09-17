/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataGateway;

import databaseConfig.DBConnection;
import databaseConfig.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


import DataAccessLayer.supplier;
import DataList.supplierList;
import org.controlsfx.control.Notifications;
/**
 *
 * @author Nuwan
 */
public class supplierDataGateway {
   
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL(); 
    
    public void Add(supplier supplier) {
        connection = dbConnection.getConnection();
        if (isUniqSupplyerName(supplier)) {
            try {
                connection = dbConnection.getConnection();
                pst = connection.prepareCall("insert into supplier(supplierName,supplierPhoneNumber,supplierAddress,description,creatorId,date) values(?,?,?,?,?,?)");
                pst.setString(1, supplier.supplierName);
                pst.setString(2, supplier.supplierContactNumber);
                pst.setString(3, supplier.supplierAddress);
                pst.setString(4, supplier.supplierDescription);
                pst.setString(5, supplier.creatorId);
                pst.setString(6, LocalDate.now().toString());
                pst.executeUpdate();
                connection.close();
                pst.close();
                      Notifications.create()
              .title("Success")
              .text("Data added successfully")
              .showWarning();
            } catch (SQLException ex) {
                Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void view(supplier supplier) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareCall("select * from supplier");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplier.id = rs.getString(1);
                supplier.supplierName = rs.getString(2);
                supplier.supplierContactNumber = rs.getString(3);
                supplier.supplierAddress = rs.getString(4);
                supplier.supplierDescription = rs.getString(5);
                supplier.creatorId = rs.getString(6);
                supplier.date = rs.getString(7);

                supplier.supplierList.addAll(new supplierList(supplier.id, supplier.supplierName, supplier.supplierContactNumber, supplier.supplierAddress, supplier.supplierDescription, supplier.date));
            }
            connection.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
        //   Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception");
        }
    }

    public void searchView(supplier supplier) {
        supplier.supplierList.clear();
        connection = dbConnection.getConnection();
        try {
            connection = dbConnection.getConnection();
            pst = connection.prepareCall("select * from supplier where supplierName like ? or supplierPhoneNumber like ? ORDER BY supplierName");
            pst.setString(1, "%" + supplier.supplierName + "%");
            pst.setString(2, "%" + supplier.supplierName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplier.id = rs.getString(1);
                supplier.supplierName = rs.getString(2);
                supplier.supplierContactNumber = rs.getString(3);
                supplier.supplierAddress = rs.getString(4);
                supplier.supplierDescription = rs.getString(5);
                supplier.creatorId = rs.getString(6);
                supplier.date = rs.getString(7);
                supplier.supplierList.addAll(new supplierList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7)));
            }rs.close();connection.close();pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(supplier supplier) {
        System.out.println("name :" + supplier.supplierName);
        connection = dbConnection.getConnection();
        try {
            connection = dbConnection.getConnection();
            pst = connection.prepareCall("select * from supplier where id=?");
            pst.setString(1, supplier.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                supplier.id = rs.getString(1);
                supplier.supplierName = rs.getString(2);
                supplier.supplierContactNumber = rs.getString(3);
                supplier.supplierAddress = rs.getString(4);
                supplier.supplierDescription = rs.getString(5);
                supplier.creatorId = rs.getString(6);
                supplier.date = rs.getString(7);
            }rs.close();connection.close();pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(supplier supplier) {
        System.out.println("we are in update");
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("select * from supplier where Id=? and supplierName=?");
            pst.setString(1, supplier.id);
            pst.setString(2, supplier.supplierName);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Into the loop");
                updateNow(supplier);
                rs.close();
                pst.close();
                        Notifications.create()
              .title("Success")
              .text("Data updated successfully")
              .showWarning();
                connection.close();
                return;
            }rs.close();connection.close();pst.close();
            if (isUniqSupplyerName(supplier)) {
                System.out.println("Out of the loop");
                updateNow(supplier);
                rs.close();connection.close();pst.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(supplier supplier) {
       connection = dbConnection.getConnection();
        try {

            connection = dbConnection.getConnection();
            pst = connection.prepareCall("SELECT * FROM brands WHERE supplierId=?");
            pst.setString(1, supplier.id);
            rs = pst.executeQuery();
            while (rs.next()) {
               Notifications.create()
              .title("Success")
              .text("Data deleted successfully")
              .showWarning();
                return;
            }rs.close();connection.close();pst.close();
            deleteParmanently(supplier);
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isUniqSupplyerName(supplier supplier) {
        connection = dbConnection.getConnection();
        boolean uniqSupplyer = false;
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareCall("select supplierName from supplier where supplierName=?");
            pst.setString(1, supplier.supplierName);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");
                       Notifications.create()
              .title("Success")
              .text("Supplyer" + "  '" + supplier.supplierName+ "' " + "Already exist")
              .showWarning();
                return uniqSupplyer;
            }rs.close();connection.close();pst.close();
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }

    public void updateNow(supplier supplier) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("update supplier set supplierName=? , supplierPhoneNumber=?,supplierAddress=? ,description=? where Id=?");
            pst.setString(1, supplier.supplierName);
            pst.setString(2, supplier.supplierContactNumber);
            pst.setString(3, supplier.supplierAddress);
            pst.setString(4, supplier.supplierDescription);
            pst.setString(5, supplier.id);
            pst.executeUpdate();
            connection.close();pst.close();
                   Notifications.create()
              .title("Success")
              .text("Supplier" + "  '" + supplier.supplierName + "' " + "Updated Sucessfuly")
              .showWarning();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParmanently(supplier supplyer) {
        connection = dbConnection.getConnection();
        try {
            System.out.println("and i am hear");
            connection = dbConnection.getConnection();
            pst = connection.prepareCall("delete from supplier where Id=?");
            pst.setString(1, supplyer.id);
            pst.executeUpdate();
            connection.close();pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isUpdate(supplier supplier) {
        connection = dbConnection.getConnection();
        boolean isUpdate = false;
        try {
            pst = connection.prepareStatement("select * from supplier where Id=?");
            pst.setString(1, supplier.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }
    
    public boolean isNotUse(supplier supplier){
        connection = dbConnection.getConnection();
        boolean isNotUse = false;
        try {
            pst = connection.prepareStatement("select * from brands where supplierId=?");
            pst.setString(1, supplier.id);
            rs = pst.executeQuery();
            while(rs.next()){
             /*  Dialogs.create().title("").masthead("Error").message("This Supplyer supplyed  '"+ rs.getString(2) +"' brand \n delete brand first").styleClass(Dialog.STYLE_CLASS_UNDECORATED).showError();
*/          Notifications.create()
              .title("Success")
              .text("This Supplier supplied  '"+ rs.getString(2) +"' brand \n delete brand first")
              .showWarning();
                return isNotUse;
            }rs.close();
            pst.close();
            connection.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(supplierDataGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    } 
}
