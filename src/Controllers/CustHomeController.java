/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CustomerControllers.CustSubMenuListController;
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
public class CustHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public static FXMLLoader custSendNotification; 
    @FXML
    public void addCustButtonAction(ActionEvent event) throws IOException {
        CustSubMenuListController.custListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustAddCustomer.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));   
    }
    
    @FXML
    public void viewCustButtonAction(ActionEvent event) throws IOException {
        CustSubMenuListController.custListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSearchCustomer.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml"))); 
    }
    
    @FXML
    public void notifyCustButtonAction(ActionEvent event) throws IOException {
        CustSubMenuListController.custListState=3;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(custSendNotification.load());
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml"))); 
    }
    
    @FXML
    public void addAgentCustButtonAction(ActionEvent event) throws IOException {
        CustSubMenuListController.custListState=4;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustAddAgent.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml"))); 
    }
    
    @FXML
    public void viewAgentCustButtonAction(ActionEvent event) throws IOException {
        CustSubMenuListController.custListState=5;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSearchAgent.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml"))); 
    }
    
    @FXML
    public void campaignCustButtonAction(ActionEvent event) throws IOException {
        CustSubMenuListController.custListState=6;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustManageCampaigns.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml"))); 
    }
    
    @FXML
    public void pointsCustButtonAction(ActionEvent event) throws IOException {
        CustSubMenuListController.custListState=7;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustPoints.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
    
    @FXML
    public void reportsCustButtonAction(ActionEvent event) throws IOException {
        CustSubMenuListController.custListState=8;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustAnalysisNReports.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml"))); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        custSendNotification= new FXMLLoader(getClass().getResource("/CustomerGUIs/CustSendNotification.fxml"));
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Customer & Campaigns");
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        subHomeC.backBtn.setVisible(false);//set back button desabled
    }    
    
}
