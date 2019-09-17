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
import DataList.productList;
import databaseConfig.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class ManageSupplierController implements Initializable {
   
 
    @FXML
    private StackPane Content;
    @FXML
    private TableView<productList> tblViewStock;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmProductquantity;
    @FXML
    private TableColumn<Object, Object> tblClmProductSupplier;
    @FXML
    private TableColumn<Object, Object> tblClmProductBrand;
    @FXML
    private TableColumn<Object, Object> tblClmProductSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductdate;
    @FXML
    private TableColumn<Object, Object> tblClmProductAddedBy;
    @FXML
    private TableColumn<Object, Object> tblClmProductdescription;
    
    products products = new products();
    productDataGateway dataGateway = new productDataGateway();
    productsBLL productBLL = new productsBLL();
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    private Button btnRefresh;
    @FXML
    private TableColumn<Object, Object> tblClmProductCategory;
    @FXML
    private TextField tfsearch;
    @FXML
    private TableColumn<Object, Object> tblClmPriority;
    @FXML
    private TableColumn<Object, Object> tblClmMinQtyLevel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAddStockOnClick(ActionEvent event) throws IOException {
         SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/addStocks.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/SuplierSubMenuList.fxml")));   
    }
        public void viewDetails() {
        System.out.println("CLCKED");
        tblViewStock.setItems(products.ProductList);
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmProductdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblClmProductSupplier.setCellValueFactory(new PropertyValueFactory<>("suppliedBy"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblClmProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tblClmProductAddedBy.setCellValueFactory(new PropertyValueFactory<>("user"));
        tblClmProductdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblClmPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        tblClmMinQtyLevel.setCellValueFactory(new PropertyValueFactory<>("minimumQuantityLevel"));
        dataGateway.viewFistTen(products);
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        //controls fx if condition
            String item = tblViewStock.getSelectionModel().getSelectedItem().getProductId();
            System.out.println("Product id" + item);
            products.productId = item;
            productBLL.delete(products);
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws IOException {
     if (!tblViewStock.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
                 Notifications.create().title("Select a row ")
              .text("Please select a row!")
               .position(Pos.CENTER)
              .showWarning();
            System.out.println("EMPTY SELECTION");
        }
        
    }
    private void viewSelected() throws IOException{
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.subContent.getChildren().clear();
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/SuplierSubMenuList.fxml")));  
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Interfaces/updateStocks.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
           UpdateStocksController UpdateSController = fxmlLoader.getController();
           UpdateSController.productId = tblViewStock.getSelectionModel().getSelectedItem().getProductId();
           UpdateSController.viewSelected();
           subHomeC.subContent.getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        products.ProductList.clear();
        tblViewStock.setItems(products.ProductList);
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmProductdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblClmProductSupplier.setCellValueFactory(new PropertyValueFactory<>("suppliedBy"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblClmProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tblClmProductAddedBy.setCellValueFactory(new PropertyValueFactory<>("user"));
        tblClmProductdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblClmPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        tblClmMinQtyLevel.setCellValueFactory(new PropertyValueFactory<>("minimumQuantityLevel"));
        dataGateway.view(products);
    }

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
        products.productName = tfsearch.getText();
        dataGateway.searchView(products);
    }
    
}
