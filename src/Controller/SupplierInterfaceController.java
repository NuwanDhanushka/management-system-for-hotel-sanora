/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogicLayer.supplierBLL;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import DataAccessLayer.supplier;
import DataGateway.supplierDataGateway;
import DataList.supplierList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import databaseConfig.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class SupplierInterfaceController implements Initializable {

    @FXML
    private Button btnRefresh;
    @FXML
    private TableView<supplierList> tblViewSupplier;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierId;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierName;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierPhoneNumber;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierAddress;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierJoiningDate;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierDescription;


    supplier supplier = new supplier();
    supplierDataGateway dataGateway = new supplierDataGateway();
    supplierBLL supplierBLL = new supplierBLL(); 
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private TextField tfsearch;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Supplier & Stocks - Suppliers");
        subHomeC.backBtn.setVisible(true);//set back button
        SubHomeController.backUrl = "/GUIs/SupplierHome.fxml";
    }    

    @FXML
    private void btnAddSupplierOnClick(ActionEvent event) {
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
    private void btnUpdateOnAction(ActionEvent event) {
    if(tblViewSupplier.getSelectionModel().getSelectedItem() != null){
            viewDetails();
        }else {
            Notifications.create().title("Select a row ")
              .text("Please select a row!")
               .position(Pos.CENTER)
              .showWarning();
            System.out.println("EMPTY SELECTION");
            
        }

    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        supplier.supplierList.clear();
        tblClmSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblClmSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tblClmSupplierPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("supplierPhoneNumber"));
        tblClmSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        tblClmSupplierDescription.setCellValueFactory(new PropertyValueFactory<>("supplierDescription"));
        tblClmSupplierJoiningDate.setCellValueFactory(new PropertyValueFactory<>("dataOfjoining"));
        dataGateway.view(supplier);
        
    }
    public void showDetails() {
        tblViewSupplier.setItems(supplier.supplierList);
        tblClmSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblClmSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tblClmSupplierPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("supplierPhoneNumber"));
        tblClmSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        tblClmSupplierDescription.setCellValueFactory(new PropertyValueFactory<>("supplierDescription"));
        tblClmSupplierJoiningDate.setCellValueFactory(new PropertyValueFactory<>("dataOfjoining"));
        dataGateway.view(supplier);
    }
         private void viewDetails(){
        supplierList selectedSupplier = tblViewSupplier.getSelectionModel().getSelectedItem();
        String items = selectedSupplier.getSupplierId();;
        if (!items.equals(null)) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Interfaces/updateSupplier.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            UpdateSupplierController supplierController = fxmlLoader.getController();
            supplierController.supplierId = selectedSupplier.getSupplierId();
            supplierController.showDetails();
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

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
        supplier.supplierList.clear();
        supplier.supplierName = tfsearch.getText().trim();

        tblViewSupplier.setItems(supplier.supplierList);
        tblClmSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblClmSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tblClmSupplierPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("supplierPhoneNumber"));
        tblClmSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        tblClmSupplierDescription.setCellValueFactory(new PropertyValueFactory<>("supplierDescription"));
        tblClmSupplierJoiningDate.setCellValueFactory(new PropertyValueFactory<>("dataOfjoining"));
        dataGateway.searchView(supplier);
    }
         
}
