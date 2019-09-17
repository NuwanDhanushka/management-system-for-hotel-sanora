/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogicLayer.productsBLL;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import DataAccessLayer.products;
import DataGateway.productDataGateway;
import Main.sendMail;
import databaseConfig.DBConnection;
import databaseConfig.SQL;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class reorderProductsController implements Initializable {

    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfProductQuantity;
    @FXML
    private TextField tfProductSellPrice;
    @FXML
    private ComboBox<String> cmbSupplier;
    
    @FXML
    private ComboBox<String> cmbBrand;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextArea taProductDescription;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClose;
    
    public String productId;
    private String supplierId;
    private String brandId;
    private String categoryId;
    private String priority;
    private final String userID="1";
    
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    sendMail mail = new sendMail();
    
    products products= new products();
    productDataGateway dataGateway = new productDataGateway();
    productsBLL productBLL = new productsBLL();
    SQL sql = new SQL();
    @FXML
    private ComboBox<String> cmbCategory;
    @FXML
    private TextField tfMinimumQuantity;
    @FXML
    private ComboBox<String> cmbPriority;
    
    ObservableList<String> priorityList = FXCollections.observableArrayList("HIGH","MEDIUM","LOW");
    @FXML
    private TextField tfNewQuantity;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbPriority.setItems(priorityList);
        
    }    

    @FXML
    private void cmbSupplierOnClick(MouseEvent event) {
        cmbSupplier.getSelectionModel().clearSelection();
        cmbSupplier.getItems().clear();
        cmbBrand.getSelectionModel().clearSelection();
        cmbBrand.getItems().clear();
        cmbBrand.getItems().removeAll();
        try {
            pst = connection.prepareStatement("select * from supplier");
            rs = pst.executeQuery();
            while (rs.next()) {
                cmbSupplier.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddStocksController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @FXML
    private void cmbSupplierOnAction(ActionEvent event) {
        cmbSupplier.getSelectionModel().getSelectedItem();

        try {
            pst = connection.prepareStatement("select * from supplier where supplierName=?");
            pst.setString(1, cmbSupplier.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            while (rs.next()) {
                supplierId = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddStocksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cmbCategoryOnClick(MouseEvent event) {
        cmbCategory.getItems().clear();
        try {
            pst = connection.prepareStatement("select * from category where supplierId=? and brandId=?");
            pst.setString(1, supplierId);
            pst.setString(2, brandId);
            rs = pst.executeQuery();
            while (rs.next()) {
                cmbCategory.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cmbBrandOnClick(MouseEvent event) {
        cmbBrand.getItems().clear();
        cmbCategory.getItems().clear();
        cmbCategory.getItems().removeAll();
        cmbCategory.setPromptText(null);
        try {
            pst = connection.prepareStatement("select * from brands where supplierId=?");
            pst.setString(1, supplierId);
            rs = pst.executeQuery();
            while (rs.next()) {
                cmbBrand.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddStocksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cmbBrandOnAction(ActionEvent event) {
            cmbBrand.getSelectionModel().getSelectedItem();
        try {
            pst = connection.prepareStatement("select * from brands where brandName=? and supplierId=?");
            pst.setString(1, cmbBrand.getSelectionModel().getSelectedItem());
            pst.setString(2, supplierId);
            rs = pst.executeQuery();
            while (rs.next()) {
                brandId = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddStocksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws SQLException {
       System.out.println(cmbSupplier.getSelectionModel().getSelectedItem());
        System.out.println(cmbSupplier.getPromptText());
        
        if (isNotNull()) {
            products.productId = productId;
            products.productName = tfProductName.getText();
            products.quantity = tfNewQuantity.getText();
            products.sellPrice = tfProductSellPrice.getText();
            products.suppliedBy = supplierId;
            products.brand = brandId;
            products.description=taProductDescription.getText();
            products.date=dpDate.getValue().toString();
            products.priority=cmbPriority.getValue().toString();
            products.minimumQuantityLevel=tfMinimumQuantity.getText();
            products.user=userID;
            products.category = categoryId;
            mail.sendReorderMail(supplierId,products.priority,products.quantity);//id nada
            productBLL.update(products);
            
        }


    }
    
    @FXML
    private void btnCloseOnAction(ActionEvent event) throws IOException {
         SubHomeController subHomeC = MainHomeController.subHome.getController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/Interfaces/reorderFromSupplier.fxml").openStream());
        reorderFromSupplierController stockController = fxmlLoader.getController();
        stockController.viewDetails();
        AnchorPane root = fxmlLoader.getRoot();
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(root);
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/SuplierSubMenuList.fxml")));   
    }
    
     public void viewSelected() {
        products.productId = productId;
        dataGateway.selectedView(products);
        tfProductName.setText(products.productName);
        tfProductQuantity.setText(products.quantity);
        tfProductSellPrice.setText(products.sellPrice);
        taProductDescription.setText(products.description);
        dpDate.setValue(LocalDate.parse(products.date));
        supplierId = products.suppliedBy;
        brandId = products.brand;
        categoryId = products.category;
        cmbSupplier.setPromptText(products.supplierName);
        cmbBrand.setPromptText(products.brandName);
        cmbCategory.setPromptText(products.categoryName);
        cmbPriority.setPromptText(products.priority);
        tfMinimumQuantity.setText(products.minimumQuantityLevel);
    }
        
    private boolean isNotNull() {
        boolean insNotNull = false;
        if (cmbSupplier.getSelectionModel().getSelectedItem() == null
                && cmbSupplier.getPromptText().isEmpty()
                || cmbBrand.getSelectionModel().getSelectedItem() == null
                && cmbBrand.getPromptText().isEmpty()
                || cmbCategory.getSelectionModel().isEmpty()
                && cmbCategory.getPromptText().isEmpty()
                || tfProductName.getText().isEmpty()
                || tfProductQuantity.getText().isEmpty()) {

         Notifications.create()
              .title("One of the fields is null")
              .text("Please enter values to the fields!")
              .position(Pos.CENTER)
              .showWarning();
     System.out.println("null");
            insNotNull = false;
        } else {
            insNotNull = true;
        }
        return insNotNull;
    }

    @FXML
    private void cmbCategoryOnAction(ActionEvent event) {
      cmbCategory.getSelectionModel().getSelectedItem();
        try {
            pst = connection.prepareStatement("select * from category where supplierId=? and brandId=?");
            pst.setString(1, supplierId);
            pst.setString(2, brandId);
            rs = pst.executeQuery();
            while (rs.next()) {
                categoryId = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    }



