/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Accountcontrollers.AccSubMenuListController;
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
public class AccountsHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void cashFlowtButtonAction(ActionEvent event) throws IOException {
        AccSubMenuListController.accListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts - REPORTS AND ANALYSIS");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/cashflowacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));

    }
     @FXML
    public void balanceSheetButtonAction(ActionEvent event) throws IOException {
        AccSubMenuListController.accListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts - PROFIT AND LOSS");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/balancesheetacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        

    }
     @FXML
    public void GraphicalButtonAction(ActionEvent event) throws IOException {
        AccSubMenuListController.accListState=3;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts - Graphical Representaion");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/graphicalacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        

    }
     @FXML
    public void ViewTransButtonAction(ActionEvent event) throws IOException {
        AccSubMenuListController.accListState=4;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts - UTILITY BILLS");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/utilitybill.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        

    }
     @FXML
    public void TaxDetailsButtonAction(ActionEvent event) throws IOException {
        AccSubMenuListController.accListState=5;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts - Tax Details");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/taxacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        

    }
     @FXML
    public void LoanButtonAction(ActionEvent event) throws IOException {
        AccSubMenuListController.accListState=6;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Accounts - Loan Maintainance");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/loanmainacc.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Accounts");
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
        } catch (IOException ex) {
            
        }
        subHomeC.backBtn.setVisible(false);
    }    
    
}
