/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import billing_system.billingSubMenuListController;
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
public class BillingHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Billing System");
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        subHomeC.backBtn.setVisible(false);//set back button desabled
    } 
    
    @FXML
    public void pubButtonAction(ActionEvent event) throws IOException {
        billingSubMenuListController.billingListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/billing_system/calBillPub.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/billing_system/billingSubMenuList.fxml")));   
    }
    @FXML
    public void takeAwayButtonAction(ActionEvent event) throws IOException {
        billingSubMenuListController.billingListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/billing_system/calBillTakeAway.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/billing_system/billingSubMenuList.fxml")));   
    }
    
}
