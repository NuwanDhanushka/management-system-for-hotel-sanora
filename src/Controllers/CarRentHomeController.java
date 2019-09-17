/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CarRentControllers.CarSubMenuListController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class CarRentHomeController implements Initializable {

     @FXML
    public void vehiDetailsButtonAction(ActionEvent event) throws IOException {
        CarSubMenuListController.carListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/vehicle_details.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
    }
    
     @FXML
    public void maintainButtonAction(ActionEvent event) throws IOException {
        CarSubMenuListController.carListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/vehi_maintenance.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
    }
    public void rentButtonAction(ActionEvent event) throws IOException {
        CarSubMenuListController.carListState=3;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/rent_vehicle.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
    }
    public void payButtonAction(ActionEvent event) throws IOException {
        CarSubMenuListController.carListState=4;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/payment_day.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
    }
    public void reportButtonAction(ActionEvent event) throws IOException {
        CarSubMenuListController.carListState=5;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Car Rental System");
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
        } catch (IOException ex) {
            
        }
        subHomeC.backBtn.setVisible(false);
    }    
    
}
