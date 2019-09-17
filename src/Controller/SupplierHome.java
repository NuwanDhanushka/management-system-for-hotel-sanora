/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controllers.MainHomeController;
import Controllers.SubHomeController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nuwan
 */
public class SupplierHome implements Initializable {

    @FXML
    private Button btnSupplier;
    @FXML
    private Button btnStocks;
    @FXML
    private Button btnBrand;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnManageStocks;
    @FXML
    private Button btnManageSupplier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSupplierOnClick(ActionEvent event) throws IOException {
       
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/Interfaces/supplierInterface.fxml").openStream());
        SupplierInterfaceController supplierController = fxmlLoader.getController();;
        supplierController.showDetails();
        AnchorPane root = fxmlLoader.getRoot();//take content to root anchorpane of fxml that is loaded
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(root);
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/SuplierSubMenuList.fxml")));   
    }

    @FXML
    private void btnStocksOnClick(ActionEvent event) throws IOException {
     
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
    private void btnBrandOnClick(ActionEvent event) throws IOException {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/Interfaces/brandInterface.fxml").openStream());
        BrandInterfaceController brandController = fxmlLoader.getController();
        brandController.showDetails();
        AnchorPane root = fxmlLoader.getRoot();
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(root);
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/SuplierSubMenuList.fxml")));   
    }

    @FXML
    private void btnCategoryOnClick(ActionEvent event) throws IOException {
        
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/Interfaces/categoryInterface.fxml").openStream());
        CategoryInterfaceController categoryController = fxmlLoader.getController();
        categoryController.showDetails();
        AnchorPane root = fxmlLoader.getRoot();
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(root);
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/SuplierSubMenuList.fxml")));   
    }

    @FXML
    private void btnManageStocksOnClick(ActionEvent event) throws IOException {
     
             /*
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/Interfaces/reorderFromSupplier.fxml").openStream());
        StocksInterfaceController stockController = fxmlLoader.getController();
        stockController.viewDetails();
        AnchorPane root = fxmlLoader.getRoot();
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(root);
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/Interfaces/SuplierSubMenuList.fxml")));  */
    }

    @FXML
    private void btnManageSupplierOnClick(ActionEvent event) throws IOException {
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
    
}
