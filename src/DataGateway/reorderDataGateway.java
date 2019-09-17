/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataGateway;

import databaseConfig.DBConnection;
import databaseConfig.SQL;
import DataAccessLayer.products;
import DataList.productList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Nuwan
 */
public class reorderDataGateway {
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();   
    
  /*  public void Add(products products) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("insert into products(productName,quantity,sellPrice,brandId,supplierId,categoryId,userId,date,priority,minimumQuantityLevel,description) values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, products.productName);
            pst.setString(2, products.quantity);
            pst.setString(3, products.sellPrice);
            pst.setString(4, products.brand);           
            pst.setString(5, products.suppliedBy);    
            pst.setString(6, products.category);
            pst.setString(7,products.user);
            pst.setString(8,products.date);
            pst.setString(9,products.priority);//priority
            pst.setString(10,products.minimumQuantityLevel);//QuantityLevel
            pst.setString(11, products.description);
            pst.executeUpdate();
            pst.close();
              Notifications.create()
              .title("Success")
              .text("Data added successfully")
              .showWarning();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Too Many Connection");
        }

    }*/
    public void view(products products) {
        products. ProductList.clear();
        connection = dbConnection.getConnection();
        
        try {
            pst = connection.prepareStatement("SELECT SQL_NO_CACHE * FROM products where minimumQuantityLevel>quantity");
            rs = pst.executeQuery();
            while (rs.next()) {
                products.productId = rs.getString(1);
                products.productName = rs.getString(2);
                products.quantity = rs.getString(3);
                products.sellPrice = rs.getString(4);
                products.brand = rs.getString(5);
                products.suppliedBy = rs.getString(6);
                products.category = rs.getString(7);
                products.user = rs.getString(8);
                products.date = rs.getString(9);
                products.priority = rs.getString(10);
                products.minimumQuantityLevel = rs.getString(11);
                products.description = rs.getString(12);
                products.supplierName = sql.getName(products.suppliedBy, products.supplierName, "supplier");
                products.brandName = sql.getName(products.brand, products.brandName, "brands");
                products.categoryName = sql.getName(products.category, products.categoryName, "category");
                products.ProductList.addAll(new productList(products.productId, products.productName, products.quantity, products.sellPrice, products.brandName, products.supplierName, products.categoryName,products.user, products.date,products.priority,products.minimumQuantityLevel,products.description));
            }
            pst.close();
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      public void selectedView(products products) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("select * from products where productId=?");
            pst.setString(1, products.productId);
            rs = pst.executeQuery();
            while (rs.next()) {
                products.productName = rs.getString(2);
                products.quantity = rs.getString(3);
                products.sellPrice = rs.getString(4);
                products.brand = rs.getString(5);
                products.suppliedBy = rs.getString(6);
                products.category = rs.getString(7);
                products.user = rs.getString(8);
                products.date = rs.getString(9);
                products.priority = rs.getString(10);
                products.minimumQuantityLevel = rs.getString(11);
                products.description = rs.getString(12);
                products.supplierName = sql.getName(products.suppliedBy, products.supplierName, "supplier");
                products.brandName = sql.getName(products.brand, products.brandName, "brands");
                products.categoryName = sql.getName(products.category, products.categoryName, "category");
            }
            pst.close();
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void viewFistTen(products products){
        connection = dbConnection.getConnection();

        products.ProductList.clear();
        try {
            pst = connection.prepareStatement("select * from products where minimumQuantityLevel>quantity limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()) {
                products.productId = rs.getString(1);
                products.productName = rs.getString(2);
                products.quantity = rs.getString(3);
                products.sellPrice = rs.getString(4);
                products.brand = rs.getString(5);
                products.suppliedBy = rs.getString(6);
                products.category = rs.getString(7);
                products.user = rs.getString(8);
                products.date = rs.getString(9);
                products.priority = rs.getString(10);
                products.minimumQuantityLevel = rs.getString(11);
                products.description = rs.getString(12);
                products.supplierName = sql.getName(products.suppliedBy, products.supplierName, "supplier");
                products.brandName = sql.getName(products.brand, products.brandName, "brands");
                products.categoryName = sql.getName(products.category, products.categoryName, "category");
                products.ProductList.addAll(new productList(products.productId, products.productName, products.quantity, products.sellPrice, products.brandName, products.supplierName, products.categoryName, products.user, products.date,products.priority,products.minimumQuantityLevel,products.description));
            }
            pst.close();
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    public void update(products products) {
        connection = dbConnection.getConnection();

        try {
            pst = connection.prepareStatement("update products set productName=?, quantity=?, sellPrice=?, "
                    + "brandId=?, supplierId=?, categoryId=?, userId=?,"
                    + "date=?, priority=?, minimumQuantityLevel=?, description=?  where productId=?");
           
            pst.setString(1, products.productName);
            pst.setString(2, products.quantity);
            pst.setString(3, products.sellPrice);
            pst.setString(4, products.brand);           
            pst.setString(5, products.suppliedBy);    
            pst.setString(6, products.category);
            pst.setString(7,products.user);
            pst.setString(8,products.date);
            pst.setString(9, products.priority);
            pst.setString(10, products.minimumQuantityLevel);
            pst.setString(11, products.description);
            pst.setString(12, products.productId);
            pst.executeUpdate();
            pst.close();
            connection.close();
                   Notifications.create()
              .title("Success")
              .text("Data updated successfully")
              .showWarning();
        } catch (SQLException ex) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*
        public void delete(products products) {
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("delete from products where productId=?");
            pst.setString(1, products. productId);
            pst.executeUpdate();
            pst.close();
                    Notifications.create()
              .title("Success")
              .text("Data deleted successfully")
              .showWarning();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
       public void searchView(products products) {
        connection = dbConnection.getConnection();

        products.ProductList.clear();
        try {
            pst = connection.prepareStatement("select * from products where productName like ? and minimumQuantityLevel>quantity ");
            pst.setString(1, "%" + products.productName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                products.productId = rs.getString(1);
                products.productName = rs.getString(2);
                products.quantity = rs.getString(3);
                products.sellPrice = rs.getString(4);
                products.brand = rs.getString(5);
                products.suppliedBy = rs.getString(6);
                products.category = rs.getString(7);
                products.user = rs.getString(8);
                products.date = rs.getString(9);
                products.priority=rs.getString(10);
                products.minimumQuantityLevel=rs.getString(11);
                products.description = rs.getString(12);
                products.supplierName = sql.getName(products.suppliedBy, products.supplierName, "supplier");
                products.brandName = sql.getName(products.brand, products.brandName, "brands");
                products.categoryName = sql.getName(products.category, products.categoryName, "category");
                products.ProductList.addAll(new productList(products.productId, products.productName, products.quantity, products.sellPrice, products.brandName, products.supplierName, products.categoryName, products.user, products.date,products.priority,products.minimumQuantityLevel,products.description));
            }
            pst.close();
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
