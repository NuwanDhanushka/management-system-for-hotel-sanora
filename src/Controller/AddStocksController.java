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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class AddStocksController implements Initializable {

    @FXML
    private Button btnClose;
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
    private Button btnAdd;
    
    private String supplierId;
    private String brandId;
    private String categoryId;
    
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    products products= new products();
    productDataGateway dataGateway = new productDataGateway();
    productsBLL productBLL = new productsBLL();
    SQL sql = new SQL();
    @FXML
    private ComboBox<String> cmbCategory;
    @FXML
    private ComboBox<String> cmbPriority;
    
    ObservableList<String> priority = FXCollections.observableArrayList("HIGH","MEDIUM","LOW");
    @FXML
    private TextField tfMinimumQuantity;
    @FXML
    private Button btnAddSupplier;
    @FXML
    private Button btnAddBrand;
    @FXML
    private Button btnAddCatagory;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbPriority.setItems(priority);
    }    

    @FXML
    private void btnCloseOnAction(ActionEvent event) throws IOException {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/Interfaces/stocksInterface.fxml").openStream());
        StocksInterfaceController stockController = fxmlLoader.getController();
        stockController.viewDetails();
        AnchorPane root = fxmlLoader.getRoot();
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(root);
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/SuplierSubMenuList.fxml")));   
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
          System.out.println("Presesd");
        if (isNotNull()) {
                products.productName = tfProductName.getText().trim();
                products.quantity = tfProductQuantity.getText().trim();
                products.sellPrice = tfProductSellPrice.getText().trim();
                products.description = taProductDescription.getText().trim();
                products.suppliedBy = supplierId;
                products.brand = brandId;
                products.category = categoryId;
                products.user = brandId;
                products.date = dpDate.getValue().toString();
                products.priority=cmbPriority.getValue().toString();
                products.minimumQuantityLevel=tfMinimumQuantity.getText().trim();
                productBLL.Add(products);
               //data saved notify controlfx
            System.out.println("lol data");
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

    @FXML
    private void btnDemoOnAction(ActionEvent event) {
        tfProductName.setText("example2");
        tfProductQuantity.setText("50");
        tfProductSellPrice.setText("5000");
        cmbSupplier.setValue("Nuwan");
        cmbBrand.setValue("Brand1");
        cmbCategory.setValue("food");
        taProductDescription.setText("example");
        dpDate.setValue(LocalDate.now());
        
    }

    @FXML
    private void btnAddSupplierOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Interfaces/addSupplier.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }

    @FXML
    private void btnAddBrandOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Interfaces/addBrand.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddCatagoryOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Interfaces/addCategory.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
