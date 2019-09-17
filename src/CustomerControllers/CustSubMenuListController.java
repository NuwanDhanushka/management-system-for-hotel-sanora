/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerControllers;

import static Controllers.CustHomeController.custSendNotification;
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

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class CustSubMenuListController implements Initializable {

    public static int custListState;
    
    @FXML
    public Button addCustBtn,viewCustBtn,notifyBtn,addAgentBtn,viewAgentBtn,campBtn,pointsBtn,reportBtn;
    @FXML
    public void addCustTabAction(ActionEvent event) throws IOException {
        custListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustAddCustomer.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
       
    @FXML
    public void viewCustTabAction(ActionEvent event) throws IOException {
        custListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSearchCustomer.fxml"))); 
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
    
    @FXML
    public void notifyCustTabAction(ActionEvent event) throws IOException {
        custListState=3;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        custSendNotification= new FXMLLoader(getClass().getResource("/CustomerGUIs/CustSendNotification.fxml"));
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(custSendNotification.load());
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
    
    @FXML
    public void addAgentCustTabAction(ActionEvent event) throws IOException {
        custListState=4;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustAddAgent.fxml")));  
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
    
    @FXML
    public void viewAgentCustTabAction(ActionEvent event) throws IOException {
        custListState=5;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSearchAgent.fxml")));  
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
    
    @FXML
    public void campaignCustTabAction(ActionEvent event) throws IOException {
        custListState=6;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustManageCampaigns.fxml"))); 
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
    
    @FXML
    public void pointsCustTabAction(ActionEvent event) throws IOException {
        custListState=7;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustPoints.fxml"))); 
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
    
    @FXML
    public void reportsCustTabAction(ActionEvent event) throws IOException {
        custListState=8;
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustAnalysisNReports.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFocusedCustTab(custListState);
    }
    public void setFocusedCustTab(int i){
        if(i==1){
            addCustBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            addCustBtn.setOnAction(null);
        }
        if(i==2){
            viewCustBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            viewCustBtn.setOnAction(null);
        }
        if(i==3){
            notifyBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            notifyBtn.setOnAction(null);
        }
        if(i==4){
            addAgentBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            addAgentBtn.setOnAction(null);
        }
        if(i==5){
            viewAgentBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            viewAgentBtn.setOnAction(null);
        }
        if(i==6){
            campBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            campBtn.setOnAction(null);
        }
        if(i==7){
            pointsBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            pointsBtn.setOnAction(null);
        }
        if(i==8){
            reportBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            reportBtn.setOnAction(null);
        }
    }    
    
}
