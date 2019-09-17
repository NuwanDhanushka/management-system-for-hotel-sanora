/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.MainHomeController.subHome;
import CustomerControllers.CustSubMenuListController;
import EMPControllers.EmpSubMenuListController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class EmpHomeController implements Initializable {

    @FXML
    private Button Register_Emp;
    @FXML
    private ImageView Emp_Reg;
    @FXML
    private Button Update_emp;
    @FXML
    private ImageView PayrollEmp;
    @FXML
    private Button viewCustTilebtn;
    @FXML
    private ImageView AttEmp;
    @FXML
    private Button senNotifTilebtn;
    @FXML
    private ImageView PointsEmp;
    @FXML
    private Button mngAgentTilebtn;
    @FXML
    private ImageView ReportsEmp;
    @FXML
    private Button custPointTilebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Employee");
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        subHomeC.backBtn.setVisible(false);//set back button desabled
         
    } 
    
    @FXML
    private void onClickreg(ActionEvent event) throws IOException {
        EmpSubMenuListController.empListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/Reg_Emp.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/EmpSubMenuList.fxml"))); 
    }
    @FXML
    private void onClickpayroll(ActionEvent event) throws IOException {
        EmpSubMenuListController.empListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/payroll.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/EmpSubMenuList.fxml"))); 
    }
   @FXML
    private void onclickAteendance(ActionEvent event) throws Exception  {
        EmpSubMenuListController.empListState=2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/Attendance.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/EmpSubMenuList.fxml"))); 
    }
    
}