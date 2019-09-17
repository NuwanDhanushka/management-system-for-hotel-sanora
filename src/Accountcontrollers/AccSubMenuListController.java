/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accountcontrollers;

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
public class AccSubMenuListController implements Initializable {

    public static int accListState;
    public Button cashflow;
    public Button editCustTilebtn,viewCustTilebtn,senNotifTilebtn,mngAgentTilebtn,mngCampTilebtn;
    @FXML

    public void CashFlowEvent(ActionEvent event) throws IOException {
        accListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts Management - Reports and Analysis");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/cashflowacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
    }
    public void BalanceSheetEvent(ActionEvent event) throws IOException {
        accListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts Management - Profit and Loss");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/balancesheetacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        
    }
    public void GraphicalEvent(ActionEvent event) throws IOException {
        accListState=3;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts Management - Graphical Representaion");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/graphicalacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
    }
    public void ViewTransEvent(ActionEvent event) throws IOException {
        accListState=4;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts Management - Utility Bills");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/utilitybill.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
    }
    public void LoanEvent(ActionEvent event) throws IOException {
        accListState=5;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts Management - Loan Management");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/loanmainacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
    }
    public void TaxEvent(ActionEvent event) throws IOException {
        accListState=6;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts Management - Tax Management");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/taxacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFocusedAccTab(accListState);
    } 
    public void setFocusedAccTab(int i){
        if(i==1){
            cashflow.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            cashflow.setOnAction(null);
        }
        if(i==2){
            editCustTilebtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            editCustTilebtn.setOnAction(null);
        }
        if(i==3){
            viewCustTilebtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            viewCustTilebtn.setOnAction(null);
        }
        if(i==4){
            senNotifTilebtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            senNotifTilebtn.setOnAction(null);
        }
        if(i==5){
            mngAgentTilebtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            mngAgentTilebtn.setOnAction(null);
        }
        if(i==6){
            mngCampTilebtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            mngCampTilebtn.setOnAction(null);
        }
    }
    
}
