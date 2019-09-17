/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMPControllers;

import Controllers.SubHomeController;
import static CustomerControllers.CustSubMenuListController.custListState;
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
 * @author SaKU
 */
public class EmpSubMenuListController implements Initializable {

    @FXML
    private AnchorPane subMenuList;
    @FXML
    private Button manageEmployee;
    @FXML
    private Button Attendance;
    @FXML
    private Button Epoints;
    @FXML
    private Button Erepeots;
    @FXML
    private Button emphome;
    @FXML
    private Button mainhm;

    public static int empListState;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFocusedEmpTab(empListState);
    }
        

    @FXML
    private void ManageEmpbtn(ActionEvent event) throws IOException {
        empListState=1;
        SubHomeController subHomeC = Controllers.MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/Reg_Emp.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/EmpSubMenuList.fxml")));
    }
    @FXML
    private void Attendancebtn(ActionEvent event) throws IOException {
        empListState=2;
        SubHomeController subHomeC = Controllers.MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/payroll.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/EmpSubMenuList.fxml")));
    }

    @FXML
    private void pointsbtn(ActionEvent event) throws IOException {
        empListState=3;
        SubHomeController subHomeC = Controllers.MainHomeController.subHome.getController();
        
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/Attendance.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/EmpSubMenuList.fxml")));
    }

    @FXML
    private void reportsbtn(ActionEvent event) {
    }

    @FXML
    private void Emphomebtn(ActionEvent event) {
    }

    @FXML
    private void mainhomebtn(ActionEvent event) {
    }
    public void setFocusedEmpTab(int i){
        if(i==1){
            manageEmployee.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            manageEmployee.setOnAction(null);
        }
        if(i==2){
            Attendance.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            Attendance.setOnAction(null);
        }
        if(i==3){
            Epoints.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            Epoints.setOnAction(null);
        }
        if(i==4){
            Erepeots.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            Erepeots.setOnAction(null);
        }
        if(i==5){
            emphome.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            emphome.setOnAction(null);
        }
        if(i==6){
            mainhm.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            mainhm.setOnAction(null);
        }
    }    
}
