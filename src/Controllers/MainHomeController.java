/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class MainHomeController implements Initializable {

    @FXML
    public StackPane mainContent;
    
    @FXML
    private Button custMainBtn;
    
    @FXML
    public static FXMLLoader subHome; 
    
    @FXML
    private void custMainButtonAction(ActionEvent event) throws IOException {
        MainMenuListController.mainState=2;
        mainContent.getChildren().clear();//remove all items
        mainContent.getChildren().add(subHome.load());
        
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Customer & Campaigns");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/CustHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
    }
    
    @FXML
    private void eventMainButtonAction(ActionEvent event) throws IOException {
        MainMenuListController.mainState=1;
        mainContent.getChildren().clear();//remove all items
        mainContent.getChildren().add(subHome.load());
        
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Events & Reservation");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/EventHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
    }
    
    @FXML
    private void deliverMainButtonAction(ActionEvent event) throws IOException {
        MainMenuListController.mainState=3;
        mainContent.getChildren().clear();//remove all items
        mainContent.getChildren().add(subHome.load());
        
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Delivery System");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/DeliverHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
    }
    
    @FXML
    private void carRentMainButtonAction(ActionEvent event) throws IOException {
        MainMenuListController.mainState=4;
        mainContent.getChildren().clear();//remove all items
        mainContent.getChildren().add(subHome.load());
        
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Car Rental System");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/CarRentHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
    }
    
    @FXML
    private void billingMainButtonAction(ActionEvent event) throws IOException {
        MainMenuListController.mainState=5;
        mainContent.getChildren().clear();//remove all items
        mainContent.getChildren().add(subHome.load());
        
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Billing System");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/BillingHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
    }
    
    @FXML
    private void supplierMainButtonAction(ActionEvent event) throws IOException {
        MainMenuListController.mainState=6;
        mainContent.getChildren().clear();//remove all items
        mainContent.getChildren().add(subHome.load());
        
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Supplier & Stock");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/SupplierHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
    }
    
    @FXML
    private void empMainButtonAction(ActionEvent event) throws IOException {
        MainMenuListController.mainState=7;
        mainContent.getChildren().clear();//remove all items
        mainContent.getChildren().add(subHome.load());
        
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Employee");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/EmpHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
    }
    
    @FXML
    private void accMainButtonAction(ActionEvent event) throws IOException {
        MainMenuListController.mainState=8;
        mainContent.getChildren().clear();//remove all items
        mainContent.getChildren().add(subHome.load());
        
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/AccountsHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
    }
    @FXML
    public void logoutButtonAction(ActionEvent event) throws IOException { 
        Parent root = FXMLLoader.load(getClass().getResource("/GUIs/Login.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.centerOnScreen();
        appStage.setMaximized(true);
        //appStage.setMaxWidth(580);
        //appStage.setMaxHeight(350);
        appStage.show();  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subHome= new FXMLLoader(getClass().getResource("/GUIs/SubHome.fxml"));
    }    
}
