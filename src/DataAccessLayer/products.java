/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;
import DataList.productList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Nuwan
 */
public class products {
    
    public String productId;
    public String productName;
    public String quantity;
    public String sellPrice;
    public String brand;
    public String suppliedBy;
    public String category;
    public String user;
    public String date;
    public String description;
    public String supplierName;
    public String brandName;
    public String categoryName;
    public String supplyerList;
    public String priority;
    public String minimumQuantityLevel;
    public ObservableList<productList> ProductList = FXCollections.observableArrayList();
}
    

