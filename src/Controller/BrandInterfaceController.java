/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogicLayer.brandBLL;
import Controllers.CustHomeController;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import DataAccessLayer.brands;
import DataGateway.brandDataGateway;
import DataList.BrandList;
import databaseConfig.DBConnection;
import databaseConfig.SQL;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class BrandInterfaceController implements Initializable {

    @FXML
    private Button btnRefresh;
    @FXML
    private TableView<BrandList> tblViewBrand;
    @FXML
    private TableColumn<Object,Object> tblClmBrandId;
    @FXML
    private TableColumn<Object,Object> tblClmBrandName;
    @FXML
    private TableColumn<Object,Object> tblClmBrandSupplier;
    @FXML
    private TableColumn<Object,Object> tblClmBranddescription;
    @FXML
    private TableColumn<Object,Object> tblClmBrandAddedBy;
    @FXML
    private TableColumn<Object,Object> tblClmBranddate;
    
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();
    brandDataGateway dataGateway = new brandDataGateway();
    brands brands = new brands();
    brandBLL brandBLL =new brandBLL();
    @FXML
    private TextField tfsearch;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Supplier & Stocks - Brands");
        subHomeC.backBtn.setVisible(true);//set back button
        SubHomeController.backUrl = "/GUIs/SupplierHome.fxml";
    }    

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        brands.brandList.clear();
        tblViewBrand.setItems(brands.brandList);
        tblClmBrandId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClmBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        tblClmBrandSupplier.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tblClmBranddescription.setCellValueFactory(new PropertyValueFactory<>("brandDescription"));
        tblClmBrandAddedBy.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        tblClmBranddate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dataGateway.view(brands);
    }

    @FXML
    private void btnAddBrandOnClick(ActionEvent event) {
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
    private void btnUpdateOnAction(ActionEvent event) {
       if(tblViewBrand.getSelectionModel().getSelectedItem() != null){
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
        BrandList selectedBrand = tblViewBrand.getSelectionModel().getSelectedItem();
  //controlfx yes no if yes below line
            brands.id = selectedBrand.getId();
            System.out.println(brands.id+ "On hear");
            brandBLL.delete(brands);
            tblViewBrand.getItems().clear();
            showDetails();
    }
        
        
    
        public void showDetails(){
        tblViewBrand.setItems(brands.brandList);
        tblClmBrandId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClmBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        tblClmBrandSupplier.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tblClmBranddescription.setCellValueFactory(new PropertyValueFactory<>("brandDescription"));
        tblClmBrandAddedBy.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        tblClmBranddate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dataGateway.view(brands);
    }
        private void viewDetails(){
        BrandList selectedBrand = tblViewBrand.getSelectionModel().getSelectedItem();
        String items = selectedBrand.getId();
        if (!items.equals(null)) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Interfaces/updateBrand.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            UpdateBrandsController UpdateBrandController = fxmlLoader.getController();
            UpdateBrandController.brandId = selectedBrand.getId();
            UpdateBrandController.showDetails();
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
        brands.brandList.clear();
        brands.brandName = tfsearch.getText();
        tblViewBrand.setItems(brands.brandList);
        tblClmBrandId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClmBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        tblClmBrandSupplier.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tblClmBranddescription.setCellValueFactory(new PropertyValueFactory<>("brandDescription"));
        tblClmBrandAddedBy.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        tblClmBranddate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dataGateway.searchView(brands);
    }
}
