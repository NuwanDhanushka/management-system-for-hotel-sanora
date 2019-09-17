/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing_system;

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
public class billingSubMenuListController implements Initializable {

    public static int billingListState;
    
    @FXML
    public Button pubBtn,takeAwayBtn;
    @FXML
    public void pubTabAction(ActionEvent event) throws IOException {
        billingListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/billing_system/calBillPub.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/billing_system/billingSubMenuList.fxml"))); 
    }
    @FXML
    public void takeAwayTabAction(ActionEvent event) throws IOException {
        billingListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/billing_system/calBillTakeAway.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/billing_system/billingSubMenuList.fxml")));
    }
       
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFocusedBilTab(billingListState);
        
    }
    public void setFocusedBilTab(int i){
        if(i==1){
            pubBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            pubBtn.setOnAction(null);
        }
        if(i==2){
            takeAwayBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            takeAwayBtn.setOnAction(null);
        }
    }    
    
}
