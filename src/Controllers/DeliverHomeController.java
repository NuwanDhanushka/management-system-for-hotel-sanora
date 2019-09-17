/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CarRentControllers.CarSubMenuListController;
import delivercontrollers.DelSubMenuListController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class DeliverHomeController implements Initializable {

    @FXML
    public void vehiDetailsButtonAction(ActionEvent event) throws IOException {
        DelSubMenuListController.delListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/adddelivery.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/DelSubMenuList.fxml")));
    }
    
     @FXML
    public void maintainButtonAction(ActionEvent event) throws IOException {
        DelSubMenuListController.delListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/addcatering.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/DelSubMenuList.fxml")));
    }
    public void rentButtonAction(ActionEvent event) throws IOException {
        DelSubMenuListController.delListState=3;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/Del_drivers.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/DelSubMenuList.fxml")));
    }
    public void payButtonAction(ActionEvent event) throws IOException {
        DelSubMenuListController.delListState=4;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/reports.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/DelSubMenuList.fxml")));
    }
    public void reportButtonAction(ActionEvent event) throws IOException {
        DelSubMenuListController.delListState=5;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/reports.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/DelSubMenuList.fxml")));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Delivery System");
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        subHomeC.backBtn.setVisible(false);//set back button desabled
    }    
    
}
