/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.MainHomeController.subHome;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class MainMenuListController implements Initializable {
    
    public static int mainState;

    @FXML
    public Button eventBtn,custBtn,dilBtn,carRentBtn,billBtn,supBtn,empBtn,accBtn;
    @FXML
    private void custTabButtonAction(ActionEvent event) throws IOException {
        mainState=2;
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Customer & Campaigns");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/CustHome.fxml")));  
        subHomeC.subMenuList.getChildren().clear();//remove all items
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));//reload main manu list
    }
    
    @FXML
    private void eventTabButtonAction(ActionEvent event) throws IOException {
        mainState=1;
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Events & Reservation");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/EventHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();//remove all items
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));//reload main manu list
    }
    
    @FXML
    private void deliverTabButtonAction(ActionEvent event) throws IOException {
        mainState=3;
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Delivery System");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/DeliverHome.fxml")));
        subHomeC.subMenuList.getChildren().clear();//remove all items
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));//reload main manu list
    }
    
    @FXML
    private void carTabButtonAction(ActionEvent event) throws IOException {
        mainState=4;
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Car Rental System");
        subHomeC.subContent.getChildren().clear();//remove all items
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/CarRentHome.fxml")));//set root objects to content 
        subHomeC.subMenuList.getChildren().clear();//remove all items
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));//reload main manu list
    }
    
    @FXML
    private void billTabButtonAction(ActionEvent event) throws IOException {
        mainState=5;
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Billing System");
        subHomeC.subContent.getChildren().clear();//remove all items
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/BillingHome.fxml")));//set root objects to content 
        subHomeC.subMenuList.getChildren().clear();//remove all items
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));//reload main manu list
    }
    
    @FXML
    private void suppTabButtonAction(ActionEvent event) throws IOException {
        mainState=6;
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Supplier & Stock");
        subHomeC.subContent.getChildren().clear();//remove all items
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/SupplierHome.fxml")));//set root objects to content 
        subHomeC.subMenuList.getChildren().clear();//remove all items
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));//reload main manu list
    }
    
    @FXML
    private void empTabButtonAction(ActionEvent event) throws IOException {
        mainState=7;
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Employee");
        subHomeC.subContent.getChildren().clear();//remove all items
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/EmpHome.fxml")));//set root objects to content 
        subHomeC.subMenuList.getChildren().clear();//remove all items
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));//reload main manu list
    }
    
    @FXML
    private void accTabButtonAction(ActionEvent event) throws IOException {
        mainState=8;
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts");
        subHomeC.subContent.getChildren().clear();//remove all items
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/AccountsHome.fxml")));//set root objects to content 
        subHomeC.subMenuList.getChildren().clear();//remove all items
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));//reload main manu list
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFocusedTab(mainState);
    }    
    
    public void setFocusedTab(int i){
        if(i==1){
            eventBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
        }
        if(i==2){
            custBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
        }
        if(i==3){
            dilBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
        }
        if(i==4){
            carRentBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
        }
        if(i==5){
            billBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
        }
        if(i==6){
            supBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
        }
        if(i==7){
            empBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
        }
        if(i==8){
            accBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
        }
    }
}
