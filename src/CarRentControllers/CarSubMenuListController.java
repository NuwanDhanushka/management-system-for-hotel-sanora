/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarRentControllers;

import Accountcontrollers.*;
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
 * @author Anushka_Hapukotuwa
 */
public class CarSubMenuListController implements Initializable {

    public static int carListState;
    public Button vehiBtn,maintainBtn,rentBtn,payBtn,rptBtn;
    @FXML
    public void vehiDetaisEvent(ActionEvent event) throws IOException {
        carListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/vehicle_details.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
    }
    public void maintainEvent(ActionEvent event) throws IOException {
        carListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/vehi_maintenance.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
        
    }
    public void rentDetailsEvent(ActionEvent event) throws IOException {
        carListState=3;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/rent_vehicle.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
    }
    public void paymentEvent(ActionEvent event) throws IOException {
        carListState=4;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/payment_day.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
    }
    public void reportsEvent(ActionEvent event) throws IOException {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFocusedCarTab(carListState);
    } 
    public void setFocusedCarTab(int i){
        if(i==1){
            vehiBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            vehiBtn.setOnAction(null);
        }
        if(i==2){
            maintainBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            maintainBtn.setOnAction(null);
        }
        if(i==3){
            rentBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            rentBtn.setOnAction(null);
        }
        if(i==4){
            payBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            payBtn.setOnAction(null);
        }
        if(i==5){
            rptBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            rptBtn.setOnAction(null);
        }
        
    }
    
}
